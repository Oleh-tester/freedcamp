package com.freedcamp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;

public class FormDataSpecHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static RequestSpecification applyJsonFormData(RequestSpecification spec, Object dto) {
        try {
            String json = mapper.writeValueAsString(dto);
            return spec.multiPart("data", json);
        } catch (Exception e) {
            throw new RuntimeException("Can't serialize request body to JSON", e);
        }
    }
}