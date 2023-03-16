package com.jiin.httpqueue.endpoint;

import com.jiin.httpqueue.action.ErrorResponse;
import org.springframework.web.context.request.ServletWebRequest;

public class ErrorResponseBuilder {

    public static ErrorResponse buildNotValidActionResponse(ServletWebRequest request) {
        ErrorResponse.Error errorBody = ErrorResponse.Error.builder()
                .type(ErrorResponse.Error.ErrorType.Sender)
                .code(ErrorResponse.Error.ErrorCode.InvalidActionName)
                .message("Value for parameter Action is not valid \r" +
                                "Must be a valid action name"
                )
                .build();
        ErrorResponse errorResponse = ErrorResponse.builder().requestId("").error(errorBody).build();
        return errorResponse;
    }
}
