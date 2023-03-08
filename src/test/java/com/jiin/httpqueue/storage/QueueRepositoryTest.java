package com.jiin.httpqueue.storage;

import com.jiin.httpqueue.mongo.MongoTestConfig;
import com.jiin.httpqueue.mongo.document.QueueEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MongoTestConfig.class)
public class QueueRepositoryTest {

    @Autowired
    private QueueRepository repository;

    @Autowired
    private MongoTemplate template;

    @Nested
    class SaveTests {

        @Test
        void shouldSaveInTheQueueCollection() {
            QueueEntity entity = givenQueueEntityBuilder().build();

            QueueEntity res = repository.save(entity);
            assertThat(res).isEqualTo(entity);

            Query query = new Query();
            query.limit(1);
            query.with(Sort.by(Sort.Direction.DESC, "_id"));

            QueueEntity actual = template.findOne(query, QueueEntity.class, "queues");
            assertThat(actual).isEqualTo(entity);
        }
    }

    private QueueEntity.QueueEntityBuilder givenQueueEntityBuilder() {
        return QueueEntity.builder();
    }
}
