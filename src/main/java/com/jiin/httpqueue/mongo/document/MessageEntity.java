package com.jiin.httpqueue.mongo.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "messages")
public class MessageEntity {

    @Id
    private String messageId;

    private String queueId;

    private String messageReceipt;
}
