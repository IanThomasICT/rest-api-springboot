package com.ianthomas.restapidemo.persistence.model;

import com.ianthomas.restapidemo.persistence.annotation.Indexable;
import com.ianthomas.restapidemo.util.ElasticsearchUtil;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
@Indexable
public class PersistentEntity {
    @Id private String id;
    Boolean isDeleted = false;

    public PersistentEntity() {
        this.id = UUID.randomUUID().toString();
    }

    // JPA/Elasticsearch operations on a single object
    public <T> T save(JpaRepository<T, String> repo) {
        T entity = repo.save((T) this);
        if (this.getClass().isAnnotationPresent(Indexable.class))
            index();
        return (T) entity;
    }

    // Add document to Elasticsearch records
    public <T> T index() {
        TransportClient client = ElasticsearchUtil.getTransportClient();
        String type = getClass().getName().substring(getClass().getName().lastIndexOf(".") + 1).toLowerCase();
        client.prepareIndex(type, "doc", id).setSource(constructJsonDocument(), XContentType.JSON).setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
        return (T) this;
    }


    // Delete entity from Elasticsearch records
    public <T> void delete(JpaRepository<T, ?> repo) {
        isDeleted = true;
        repo.save((T) this);

        if (getClass().isAnnotationPresent(Indexable.class)) {
            String type = getClass().getSimpleName().toLowerCase();
            ElasticsearchUtil.getTransportClient().prepareDelete(type, "doc", id).setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
        }
    }


//    public <T> List<T> index(T... objects) {
//        TransportClient client = ElasticsearchUtil.getTransportClient();
//        BulkRequestBuilder builder = client.prepareBulk();
//        for (T obj : objects){
//            String type = obj.getClass().getSimpleName().toLowerCase();
//            if (((PersistentEntity) obj).isDeleted)
//                builder.add(client.prepareDelete(type, "doc", ((PersistentEntity) obj).id));
//            else
//                builder.add(client.prepareIndex(type, "doc", ((PersistentEntity) obj).id).setSource(((PersistentEntity) obj).constructJsonDocument(), XContentType.JSON));
//        }
//
//
//        return
//    }



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
                .field("id", id)
                .field("isDeleted", isDeleted);
    }
}
