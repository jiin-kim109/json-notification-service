package com.jiin.httpqueue.mongo;

import com.jiin.httpqueue.mongo.document.QueueEntity;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
@EnableMongoRepositories(basePackages = "com.jiin.httpqueue.storage")
public class MongoTestConfig extends AbstractMongoClientConfiguration {

    private static final String DATABASE = "test-db";
    private MongoServer fakeMongoServer;
    private ServerAddress mongoServerAddress;

    public MongoTestConfig() {
        this.fakeMongoServer = new MongoServer(new MemoryBackend());
        this.mongoServerAddress = new ServerAddress(this.fakeMongoServer.bind());
    }

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(format("mongodb://%s/%s", mongoServerAddress, DATABASE));

        MongoClientSettings.Builder settingsBuilder = MongoClientSettings.builder()
                .applyConnectionString(connectionString);

        return MongoClients.create(settingsBuilder.build());
    }

    @Override
    protected String getDatabaseName() {
        return DATABASE;
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton(QueueEntity.class.getPackageName());
    }
}
