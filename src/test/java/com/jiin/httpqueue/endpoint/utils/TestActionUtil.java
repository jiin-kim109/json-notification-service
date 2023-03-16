package com.jiin.httpqueue.endpoint.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.common.base.Joiner;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class TestActionUtil {

    private ObjectMapper mapper;

    @Autowired
    public TestActionUtil(ObjectMapper mapper) {
        this.mapper = mapper;

        this.mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
    }

    @Builder
    @Data
    public static class CreateQueue {

        private final static String ACTION_NAME = "CreateQueue";

        private final static String QUEUE_NAME = "testQueue";

        public static MultiValueMap<String, String> withMandatoryFieldOnly() {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("Action", ACTION_NAME);
            params.add("QueueName", QUEUE_NAME);
            return params;
        }
    }

    public static String convertToQueryString(MultiValueMap<String, String> params) {
        return Joiner.on("&").withKeyValueSeparator("=").join(params);
    }
}
