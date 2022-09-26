package com.symphony.api.rest.sections;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class LoginSection extends BaseSection {

    public LoginSection(RequestSpecification requestSpec) {
        super(requestSpec);
    }

    public Response login(String username, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("password", password);
        requestParams.put("username", username);

        requestSpec.contentType("application/json").body(requestParams.toJSONString());

        Response responseTmp = RestAssured.given(requestSpec).post("/auth/login/");
        ResponseBody body = responseTmp.getBody();
        response = responseTmp;
        System.out.println(responseTmp.getStatusLine());
        System.out.println(body.asString());

        JsonPath j = new JsonPath(body.asString());

        //get a field value from nested JSON
        String p = j.getString("user.firstName");
        System.out.println("firstName is: " + p);

        return responseTmp;
    }

}
