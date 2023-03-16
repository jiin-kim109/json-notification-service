package com.jiin.httpqueue.mapper;

import com.jiin.httpqueue.action.Attribute;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;

public interface AttributeMapper {

    static <T> T mapAttributeToObjectFields(T obj, Map<String, String> mappingValues){
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Attribute.class) && mappingValues.containsKey(field.getName())) {
                    field.setAccessible(true);
                    field.set(obj, mappingValues.get(field.getName()));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
