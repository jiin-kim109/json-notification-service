package com.jiin.jsondirectory.api.utils;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

public class MockApiRequestBuilder {

    private static final String API_BASE_URL = "/api/v1";

    public static MockHttpServletRequestBuilder buildGetRequet(String endpoint) {
        return MockMvcRequestBuilders.get(API_BASE_URL + endpoint);
    }

    public static MockHttpServletRequestBuilder buildGetRequet(String endpoint, MultiValueMap<String, String> params) {
        return MockMvcRequestBuilders.get(API_BASE_URL + endpoint).params(params);
    }
}
