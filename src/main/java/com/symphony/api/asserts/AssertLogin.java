package com.symphony.api.asserts;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.testng.asserts.SoftAssert;

public class AssertLogin {

    public void successfullyLogin(Response response, String firstName, String lastName, String userName) {
        ResponseBody body = response.getBody();
        JsonPath jPath = new JsonPath(body.asString());

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not as expected");
        softAssert.assertEquals(jPath.getString("user.firstName"), firstName, "First Name is not as expected");
        softAssert.assertEquals(jPath.getString("user.lastName"), lastName, "Last Name is not as expected");

        softAssert.assertAll();

    }

}
