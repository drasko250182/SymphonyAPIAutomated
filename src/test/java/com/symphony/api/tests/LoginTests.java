package com.symphony.api.tests;

import com.symphony.api.asserts.AssertLogin;
import com.symphony.api.provider.UsersDataProvider;
import com.symphony.api.rest.sections.LoginSection;
import com.symphony.api.rest.sections.SignupSection;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = {"Smoke"}, dataProvider = "UserDataProvider", dataProviderClass = UsersDataProvider.class)
    public void verifyLoginValidUser(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        SignupSection signupSection = new SignupSection(requestSpec);

        signupSection.signupUser(email, password, firstName, lastName, username, dateOfBirth);
        //signupSection.signupUser("drasko2501@gmail.com", "drasko250182", "Drasko", "Janjevic", "drasko2501", "25/01/1982");

        LoginSection loginSection = new LoginSection(requestSpec);
        Response response = loginSection.login(username, password);
        AssertLogin assertLogin = new AssertLogin();
        assertLogin.successfullyLogin(response, firstName, lastName, username);
    }

}
