package com.symphony.api.asserts;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.asserts.SoftAssert;

import java.util.Optional;

public class AssertPosts {

    public void successfullyAddPost(Response response, String firstName, String lastName, String text) {
        ResponseBody body = response.getBody();
        JsonPath jPath = new JsonPath(body.asString());

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), 201, "Status code is not as expected");
        softAssert.assertEquals(jPath.getString("user.firstName"), firstName, "First Name is not as expected");
        softAssert.assertEquals(jPath.getString("user.lastName"), lastName, "Last Name is not as expected");
        softAssert.assertEquals(jPath.getString("text"), text, "Text is not as expected");

        softAssert.assertAll();

    }

    public void successfullyAddComment(Response response, String firstName, String lastName, String text) {
        ResponseBody body = response.getBody();
        JsonPath jPath = new JsonPath(body.asString());

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), 201, "Status code is not as expected");
        softAssert.assertEquals(jPath.getString("user.firstName"), firstName, "First Name is not as expected");
        softAssert.assertEquals(jPath.getString("user.lastName"), lastName, "Last Name is not as expected");
        softAssert.assertEquals(jPath.getString("text"), text, "Text is not as expected");
        //softAssert.assertEquals(jPath.getString("results.text"), text, "Text is not as expected");


        softAssert.assertAll();

    }

    public void assertGetComment(Response response, int postId, String firstName, String lastName, String text) {
        ResponseBody body = response.getBody();
        JsonPath jPath = new JsonPath(body.asString());

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not as expected");
        softAssert.assertEquals(jPath.getString("results.user.firstName").substring(1, jPath.getString("results.user.firstName").length() -1), firstName, "First Name is not as expected");
        softAssert.assertEquals(jPath.getString("results.user.lastName").substring(1, jPath.getString("results.user.lastName").length() -1), lastName, "Last Name is not as expected");
        softAssert.assertEquals(jPath.getString("results.text").substring(1, jPath.getString("results.text").length() - 1), text, "Text is not as expected");
        //softAssert.assertEquals(jPath.getString("results.id"), postId, "Id is not as expected");

        softAssert.assertAll();

    }

}
