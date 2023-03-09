package com.jiin.httpqueue.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
