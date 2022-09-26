package com.symphony.api.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class ResposeHandler {

    public static Object getSingleValueFromResponse(Response response, String key) {
        JsonPath jsonPathEvaluator = response.jsonPath();

        Object toReturn = jsonPathEvaluator.get(key);

        return toReturn;
    }

    public static List<Object> getArrayFromResponse(Response response, String key) {
        JsonPath jsonPathEvaluator = response.jsonPath();

        List<Object> toReturn = jsonPathEvaluator.getList(key);

        return toReturn;
    }

    public static Object getStatusCodeFromResponse(Response response, String key) {
        JsonPath jsonPathEvaluator = response.jsonPath();

        Object toReturn = jsonPathEvaluator.get(key);

        return toReturn;
    }

    public static String getToken(Response response) {
        JsonPath jsonPathEvaluator = response.jsonPath();

        String toReturn = jsonPathEvaluator.get("token");

        return toReturn;
    }

}
