package com.ianthomas.restapidemo.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

public class ElasticsearchUtil {

    private static TransportClient transportClient;

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
}
