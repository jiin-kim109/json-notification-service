package com.jiin.httpqueue.mongo;

import com.jiin.httpqueue.mongo.document.QueueEntity;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

import static java.lang.String.format;

@Configuration
@EnableMongoRepositories(basePackages = "com.jiin.httpqueue.storage")
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final MongoProperties props;

    public MongoConfig(MongoProperties props) {
        this.props = props;
    }

    @Bean
    @Lazy
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(format("mongodb://%s/%s", props.getHost(), props.getDatabase()));

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    private MongoCredential createCredential() {
        var username = props.getUsername();
        var password = props.getPassword();
        var authenticationDb = props.getAuthenticationDatabase();
        return MongoCredential.createCredential(username, authenticationDb, password);
    }

    @Override
    protected String getDatabaseName() {
        return props.getDatabase();
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton(QueueEntity.class.getPackageName());
    }
}
