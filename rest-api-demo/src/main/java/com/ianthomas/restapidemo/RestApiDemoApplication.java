package com.ianthomas.restapidemo;

import com.ianthomas.restapidemo.util.ElasticsearchUtil;
import org.elasticsearch.action.get.GetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApiDemoApplication.class, args);
    }
}