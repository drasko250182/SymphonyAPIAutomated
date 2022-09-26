package com.symphony.api.rest.sections;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;

public class PostsSection extends BaseSection {


    PostsSection(RequestSpecification requestSpec, ResponseSpecification responseSpec, Response response) {
        super(requestSpec, responseSpec, response);
    }

    public PostsSection() {
        super();

    }

    public PostsSection(RequestSpecification requestSpec, Response response) {
        super(requestSpec, response);
    }

    public PostsSection(RequestSpecification requestSpec) {
        super(requestSpec);
    }

    public Response addComment(String token, String text, Integer post) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("text", text);
        requestParams.put("post", post);


        //requestSpec.header("Authorization", "token " + "eb01c2e6bd111bedaa7d0e63ad62720388fbdb6d").contentType("application/json").body(requestParams.toJSONString());
        requestSpec.header("Authorization", "token " + token).contentType("application/json").body(requestParams.toJSONString());

        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://randomlyapi.symphony.is/api").
                build();
        requestSpecification.header("Authorization", "token " + token).contentType("application/json").body(requestParams.toJSONString());

        //Response responseTmp = requestSpec.post("/post-comments/");
        Response responseTmp = RestAssured.given(requestSpecification).post("/post-comments/");
        ResponseBody body = responseTmp.getBody();
        response = responseTmp;
        System.out.println(responseTmp.getStatusLine());
        System.out.println(body.asString());

        return responseTmp;
    }

    public Response addPost(String token, String text) {

        JSONObject requestParams = new JSONObject();
        requestParams.put("text", text);

        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://randomlyapi.symphony.is/api").
                build();
        requestSpecification.header("Authorization", "token " + token).contentType("application/json").body(requestParams.toJSONString());

        Response responseTmp = RestAssured.given(requestSpecification).post("/posts/");
        ResponseBody body = responseTmp.getBody();
        response = responseTmp;
        System.out.println(responseTmp.getStatusLine());
        System.out.println(body.asString());

        return responseTmp;
    }

    public Response getComment(String token, Integer commentId) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://randomlyapi.symphony.is/api").
                build();
        requestSpecification.header("Authorization", "token " + token).contentType("application/json");

        Response responseTmp = RestAssured.given(requestSpecification).get("/posts/" + commentId + "/comments/");
        ResponseBody body = responseTmp.getBody();
        response = responseTmp;
        System.out.println(responseTmp.getStatusLine());
        System.out.println(body.asString());

        return responseTmp;
    }
}
