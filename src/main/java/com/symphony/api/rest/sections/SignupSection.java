package com.symphony.api.rest.sections;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;

public class SignupSection extends BaseSection {

    public SignupSection(RequestSpecification requestSpec, ResponseSpecification responseSpec, Response response) {
        super(requestSpec, responseSpec, response);
    }

    public SignupSection(RequestSpecification requestSpec) {
        super(requestSpec);
    }

    public Response signupUser(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", email);
        requestParams.put("password", password);
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("username", username);
        requestParams.put("dateOfBirth", dateOfBirth);

        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://randomlyapi.symphony.is/api").
                build();
        requestSpecification.contentType("application/json").body(requestParams.toJSONString());

        Response responseTmp = RestAssured.given(requestSpecification).post("/auth/signup/");
        ResponseBody body = responseTmp.getBody();
        response = responseTmp;
        System.out.println(responseTmp.getStatusLine());
        System.out.println(body.asString());

        return responseTmp;
    }

    public Response signupUser(String password, String firstName, String lastName, String username, String dateOfBirth) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("password", password);
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("username", username);
        requestParams.put("dateOfBirth", dateOfBirth);

        requestSpec.contentType("application/json").body(requestParams.toJSONString());

        Response responseTmp = RestAssured.given(requestSpec).post("/auth/signup/");
        ResponseBody body = responseTmp.getBody();
        response = responseTmp;
        System.out.println(responseTmp.getStatusLine());
        System.out.println(body.asString());

        return responseTmp;
    }

}
