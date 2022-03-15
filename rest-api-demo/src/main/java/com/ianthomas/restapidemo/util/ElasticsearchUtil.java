package com.ianthomas.restapidemo.util;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.model.Employee;
import com.ianthomas.restapidemo.persistence.model.PersistentEntity;
import com.ianthomas.restapidemo.persistence.repository.CustomerRepository;
import com.ianthomas.restapidemo.persistence.repository.EmployeeRepository;
import com.ianthomas.restapidemo.service.CustomerService;
import com.ianthomas.restapidemo.service.EmployeeService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class ElasticsearchUtil {

    private static TransportClient transportClient;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public ElasticsearchUtil(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    private static String HOST;
    private static int REST_PORT;
    private static int TCP_PORT;
    private static String SCHEME;

    @Value("${elasticsearch.host}") private void setHost( String host){
        HOST = host;
    }
    @Value("${elasticsearch.rest_port}") private void setRestPort( int rest_port){
        REST_PORT = rest_port;
    }
    @Value("${elasticsearch.tcp_port}") private void setTcpPort( int tcp_port){
        TCP_PORT = tcp_port;
    }
    @Value("${elasticsearch.scheme}") private void setScheme( String scheme){
        SCHEME = scheme;
    }

    private static Map<String, Class> indicesMap = Map.ofEntries(Map.entry("customer", Customer.class),Map.entry( "employee", Employee.class));

    // Construct transportClient using application.properties info
    public static TransportClient getTransportClient()  {
        if (transportClient == null) {
            try{
                System.setProperty("es.set.netty.runtime.available.processors", "false");   //(Ian) https://github.com/elastic/elasticsearch/issues/25741#:~:text=Okay%2C%20what%20I%20think%20is%20happening%20is%20this%3A%20you%20are%20using%20Netty%20elsewhere%20in%20your%20application.
                transportClient = new PreBuiltTransportClient(Settings.EMPTY);
                transportClient.addTransportAddress(new TransportAddress( InetAddress.getByName(Optional.ofNullable(HOST).orElse("localhost")), Optional.ofNullable(TCP_PORT).orElse(9300)));
            }catch (UnknownHostException e){
                e.printStackTrace();
            }
        }
        return transportClient;
    }

    // Indexes JPA values in ES
    public void mergeDB() {
        TransportClient client = ElasticsearchUtil.getTransportClient();
        try{
            for (String index : indicesMap.keySet()){
                LOG.info("Deleting previous index: {}", index);
                client.admin().indices().prepareDelete(index).get();
            }
        } catch (IndexNotFoundException e) {
            LOG.info(e.getMessage());
        }
        List<Customer> customers = customerRepository.findAll();
        Customer.index((Customer[]) customers.toArray(new Customer[0]));

        List<Employee> employees = employeeRepository.findAll();
        Employee.index((Employee[]) employees.toArray(new Employee[0]));

    }
}
