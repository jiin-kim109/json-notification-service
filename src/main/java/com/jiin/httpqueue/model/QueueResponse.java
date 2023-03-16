package com.jiin.httpqueue.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class QueueResponse {

    private String queueId;

    private String queueName;

    private int delaySeconds;

    private int maximumMessageSize;

    private int messageRetensionPeriod;

    private int receiveMessageWaitTimeSeconds;

    @JsonFormat(pattern = "dd-mm-yyyy hh:mm:ss")
    private Date createdOn;
}
