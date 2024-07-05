package com.parking.service.login.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class MapperUtil {

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
    }

    public static String getStringFromObject(Object value) {
        try {
            String jsonString = MAPPER.writeValueAsString(value);
            System.out.println(jsonString);
            return jsonString;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
