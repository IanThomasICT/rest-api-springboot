package com.ianthomas.restapidemo.service;

import com.ianthomas.restapidemo.persistence.repository.CustomerRepository;
import com.ianthomas.restapidemo.persistence.repository.EmployeeRepository;
import com.ianthomas.restapidemo.rest.dto.HitDto;
import com.ianthomas.restapidemo.rest.dto.SearchDto;
import com.ianthomas.restapidemo.util.ElasticsearchUtil;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    // Connects to ES and retrieves SearchRequestBuilder for index (index/_search)
    private SearchRequestBuilder getSearchRequestBuilder(String indexName) {
        TransportClient client = ElasticsearchUtil.getTransportClient();
        return client.prepareSearch(indexName);
    }

    private SearchRequestBuilder buildMatchQuery(String index, String field, String matchParam) {
        SearchRequestBuilder searchRequestBuilder = getSearchRequestBuilder(index);
        return searchRequestBuilder.setQuery(QueryBuilders.matchQuery(field, matchParam));
    }

    public SearchDto queryMatchCustomers(String index, String field, String matchParam) {
        SearchResponse response = buildMatchQuery(index,field,matchParam).get();
        List<HitDto> results = new ArrayList<>();
        TotalHits totalHits = response.getHits().getTotalHits();
        for (SearchHit hit : response.getHits()){
            Map<String, Object> source = hit.getSourceAsMap();
            HitDto search = new HitDto(hit.getId(),source.get("customerId").toString(), source.get("companyName").toString(), matchParam);
            search.setSearchScore(hit.getScore());
            results.add(search);
        }

        return new SearchDto(totalHits.value, results);
    }


    public Object queryMatchEmployees(String index, String field, String matchParam) {
        SearchResponse response = buildMatchQuery(index,field,matchParam).get();
        List<HitDto> results = new ArrayList<>();
        TotalHits totalHits = response.getHits().getTotalHits();
        for (SearchHit hit : response.getHits()){
            Map<String, Object> source = hit.getSourceAsMap();
            HitDto search = new HitDto(hit.getId(),source.get("id").toString(), source.get("firstName").toString() + source.get("lastName").toString(), matchParam);
            search.setSearchScore(hit.getScore());
            results.add(search);
        }

        return new SearchDto(totalHits.value, results);
    }
}
