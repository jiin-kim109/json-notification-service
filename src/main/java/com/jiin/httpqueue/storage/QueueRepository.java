package com.jiin.httpqueue.storage;

import com.jiin.httpqueue.mongo.document.QueueEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends MongoRepository<QueueEntity, String> {

}
