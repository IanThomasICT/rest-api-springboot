package com.ianthomas.restapidemo.persistence.model;

import com.ianthomas.restapidemo.persistence.annotation.Indexable;
import com.ianthomas.restapidemo.util.ElasticsearchUtil;
import com.sun.istack.NotNull;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(value = {PersistentEntityListener.class})
public class PersistentEntity {
    protected String entityId;
    @NotNull protected Boolean isDeleted = false;

    private final static Logger LOG = LoggerFactory.getLogger(PersistentEntity.class);

    public PersistentEntity() {
        this.entityId = UUID.randomUUID().toString();
    }

    // JPA/Elasticsearch operations on a single object
    public <T> T save(JpaRepository<T, ?> repo) {
        T entity = repo.save((T) this);
        LOG.info("Added {} to repository", getClass().getSimpleName());
        if (this.getClass().isAnnotationPresent(Indexable.class))
            index();
        return (T) entity;
    }

    // Add document to Elasticsearch records
    public <T> T index() {
        TransportClient client = ElasticsearchUtil.getTransportClient();
        String type = getClass().getName().substring(getClass().getName().lastIndexOf(".") + 1).toLowerCase();
        client.prepareIndex(type, "doc", entityId).setSource(constructJsonDocument(), XContentType.JSON).setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
        LOG.info("Indexed {} as {}",getClass().getSimpleName(), entityId);
        return (T) this;
    }


    // Delete entity from Elasticsearch records
    public <T> void delete(JpaRepository<T, ?> repo) {
        isDeleted = true;
        repo.delete((T) this);
        LOG.info("Deleted {} from repository", getClass().getSimpleName());

        if (getClass().isAnnotationPresent(Indexable.class)) {
            String type = getClass().getSimpleName().toLowerCase();
            ElasticsearchUtil.getTransportClient().prepareDelete(type, "doc", entityId).setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
            LOG.info("Deleted {} from {} index",entityId, type);
        }
    }


    public static <T> List<T> index(T... objects) {
        TransportClient client = ElasticsearchUtil.getTransportClient();
        BulkRequestBuilder builder = client.prepareBulk();
        int objectCount = 0;
        Instant t1 = Instant.now();
        String objectType = "unknown";
        for (T obj : objects){
            String type = obj.getClass().getSimpleName().toLowerCase();
            objectType = type;
            objectCount++;
            if (((PersistentEntity) obj).isDeleted)
                builder.add(client.prepareDelete(type, "doc", ((PersistentEntity) obj).entityId));
            else
                builder.add(client.prepareIndex(type, "doc", ((PersistentEntity) obj).entityId).setSource(((PersistentEntity) obj).constructJsonDocument(), XContentType.JSON));
        }
        if (objectCount > 0) {
            BulkResponse response = builder.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
            if (response.hasFailures()) {
                LOG.warn("Bulk Indexing has failures");
                for (BulkItemResponse r : response.getItems()) {
                    if (r.isFailed())
                        LOG.error("Failure to index record id {}, {}", r.getFailure().getId(), r.getFailureMessage());
                }
            } else {
                long millis = Duration.between(t1, Instant.now()).toMillis();
                LOG.info("Transaction committed. ReIndexed " + objectCount + " " + objectType + "(s). Took: {}ms", millis);
            }
        }
        else
            LOG.warn("Trying to index 0 objects! Ignoring...");

        return Arrays.asList(objects);
    }



    // Build JSON Document from object
    public String constructJsonDocument() {
        XContentBuilder builder;
        try {
            builder = XContentFactory.jsonBuilder().startObject();      // Starts building the object
            updateBuilderFields(builder).endObject();                   // Updates builder fields by super function or overridden child functions
            return Strings.toString(builder);                           // Returns a viewable JSON String object
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Construct JSON document
    protected XContentBuilder updateBuilderFields(XContentBuilder builder) throws IOException {
        return builder
                .field("id", entityId)
                .field("isDeleted", isDeleted);
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
