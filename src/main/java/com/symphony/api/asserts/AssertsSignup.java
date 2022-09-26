package com.symphony.api.asserts;

import org.testng.asserts.SoftAssert;

public class AssertsSignup {

    public void successfullySingup(int responseCode, String message) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseCode, 201, "Status code is not as expected");
        softAssert.assertEquals(message, "Thanks for signing up.", "Success message is not as expected");

        softAssert.assertAll();

    }

    public void existingUserSingup(int responseCode, String emailMessage, String userNameMessage) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseCode, 400, "Status code is not as expected");
        softAssert.assertEquals(emailMessage, "[User already has an account!]", "Message is not as expected");
        softAssert.assertEquals(userNameMessage, "[This username is already in use.]", "Message is not as expected");

        softAssert.assertAll();

    }

    public void userWithoutEmailSingup(int responseCode, String emailMessage) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseCode, 400, "Status code is not as expected");
        softAssert.assertEquals(emailMessage, "[This field is required.]", "Message is not as expected");

        softAssert.assertAll();

    }

}
