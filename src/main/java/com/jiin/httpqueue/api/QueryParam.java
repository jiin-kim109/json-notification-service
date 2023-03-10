package com.jiin.httpqueue.api;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiin.httpqueue.validator.ValidAction;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class QueryParam {

    @NotNull
    @ValidAction
    @JsonProperty("Action")
    private String action;

    private Map<String, String> parameters = new HashMap<>();

    @JsonAnySetter
    public void setParameters(String key, String value) {
        parameters.put(key, value);
    }
}
