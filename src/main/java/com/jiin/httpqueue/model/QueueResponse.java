package com.jiin.httpqueue.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;

@Data
public class QueueResponse {

    private String queueId;

    @JsonFormat(pattern = "dd-mm-yyyy hh:mm:ss")
    private Date createdOn;

    @JsonFormat(pattern = "dd-mm-yyyy hh:mm:ss")
    private Date deletedOn;
}
