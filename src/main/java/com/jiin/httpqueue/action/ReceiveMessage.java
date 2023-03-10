package com.jiin.httpqueue.action;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public final class ReceiveMessage extends ActionResponse {


    @Builder.Default
    @Min(value = 1)
    @Max(value = 10)
    private int maxNumberOfMessage = 1;

    @NotBlank
    private String queueUrl;

    private int visibilityTimeout;

    private int waitTimeSeconds;

    private List<Message> messages;

    @Attribute
    private String approximateFirstReceiveTimestamp;

    @Attribute
    private int approximateReceiveCount;

    @Attribute
    private String sentTimestamp;

    @Attribute
    private int sequenceNumber;
}
