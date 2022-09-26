package com.symphony.api.rest.sections;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseSection {

    public RequestSpecification requestSpec;
    public ResponseSpecification responseSpec;
    public Response response;

    BaseSection(RequestSpecification requestSpec, ResponseSpecification responseSpec, Response response) {
        this.requestSpec = requestSpec;
        this.responseSpec = responseSpec;
        this.response = response;
    }

    public BaseSection() {

    }

    public BaseSection(RequestSpecification requestSpec, Response response) {
        this.requestSpec = requestSpec;
        this.response = response;
    }

    public BaseSection(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }
}
