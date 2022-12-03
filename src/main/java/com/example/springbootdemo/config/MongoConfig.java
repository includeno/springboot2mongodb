package com.example.springbootdemo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * @Description mongo配置类
 * @Version 1.0
 */
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private Integer port;
    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.password}")
    private String password;
    @Value("${spring.data.mongodb.authentication-database}")
    private String authenticationDatabase;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public MongoClient getMongoClient() {
        //https://www.baeldung.com/spring-data-mongodb-connection
        MongoCredential credentials = MongoCredential.createCredential(
                username, authenticationDatabase, password.toCharArray());
        String connections = String.format("mongodb://%s:%s@%s:%d/?authSource=admin", username, password, host, port);
        MongoClientSettings mcs = MongoClientSettings.builder()
                .applicationName("Sample-Service")
                .credential(credentials)
                .applyToSslSettings(sslb ->
                        sslb.enabled(false)
                                .invalidHostNameAllowed(false))
                .applyConnectionString(new ConnectionString(connections))
                .build();
        return MongoClients.create(mcs);
    }

    /**
     * 自定义mongo连接池
     *
     * @param mongoClient 客户端
     * @return MongoDbFactory对象
     */
    @Bean
    public SimpleMongoClientDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, database);
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(SimpleMongoClientDatabaseFactory factory) {
        return new MongoTemplate(factory);
    }
}