package com.jiin.httpqueue.mongo.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "queues")
public class QueueEntity {
    @Id
    private String queueId;
}