package com.jean.auto.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object) throws Exception {
        if (object == null) return "null";
        return mapper.writeValueAsString(object);
    }

    public static Map toMap(String json) throws Exception {
        if (json == null || json.isEmpty() || json.equals("{}")) {
            return new HashMap();
        }
        return mapper.readValue(json, Map.class);
    }

}