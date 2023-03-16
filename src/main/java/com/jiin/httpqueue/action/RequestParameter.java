package com.jiin.httpqueue.action;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class RequestParameter {

    private Actions actionName;

    private Map<String, String> parameters;

    private Map<Integer, String> attributeNames;

    private Map<Integer, String> attributeValues;

    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new HashMap<>();
        attributeNames.forEach(
                (seq, name) -> {
                    if(attributeValues.containsKey(seq)) attributes.put(name, attributeValues.get(seq));
                });
        return attributes;
    }
}
