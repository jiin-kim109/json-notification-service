package com.jiin.httpqueue.action;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JacksonXmlRootElement(localName = "ErrorResponse")
public final class ErrorResponse extends ActionResponse {

    @JacksonXmlElementWrapper(useWrapping = true, localName = "Error")
    private ErrorResponse.Error error;
    @JacksonXmlProperty(localName = "RequestId")
    private String requestId;

    @Data
    @Builder
    public static class Error {
        private ErrorType type;

        private ErrorCode code;

        private String message;

        public enum ErrorType { Sender, Receiver }
        public enum ErrorCode {
            InvalidParameterValue,
            InvalidActionName
        }
    }
}
