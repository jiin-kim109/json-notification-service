package com.jiin.httpqueue.action;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@JacksonXmlRootElement(localName = "CreateQueueResponse")
public final class CreateQueue extends ActionResponse {

    @JacksonXmlElementWrapper(useWrapping = true, localName = "CreateQueueResult")
    private CreateQueueResult createQueueResult;

    @JacksonXmlElementWrapper(useWrapping = true, localName = "ResponseMetadata")
    private ResponseMetadata responseMetadata;


    @Data
    @Builder
    public static class CreateQueueResult {

        public String queueUrl;
    }

    @Data
    @Builder
    public static class ResponseMetadata {

        public String requestId;
    }
}