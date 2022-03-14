package com.ianthomas.restapidemo;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.model.Employee;
import com.ianthomas.restapidemo.persistence.repository.CustomerRepository;
import com.ianthomas.restapidemo.persistence.repository.EmployeeRepository;
import com.ianthomas.restapidemo.util.ElasticsearchUtil;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class Initialize implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired private ElasticsearchUtil elasticsearchUtil;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private Environment environment;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Elasticsearch reIndex
        if (Objects.equals(environment.getProperty("elasticsearch-reset"),"true")){
            System.out.println("Resetting elasticsearch indices");
            // Load JPA into ES
            elasticsearchUtil.mergeDB();
        }

    }

}
