package com.symphony.api.tests;

import com.symphony.api.asserts.AssertsSignup;
import com.symphony.api.provider.UsersDataProvider;
import com.symphony.api.rest.sections.SignupSection;
import com.symphony.api.utils.ResposeHandler;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class SignupTests extends BaseTest {

    @Test(dataProvider = "UsersDataProvider", dataProviderClass = UsersDataProvider.class, groups = {"Regression"})
    public void verifySignupUsers(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        SignupSection signupSection = new SignupSection(requestSpec);
        //signupSection.signupUser("drasko2501@gmail.com", "drasko250182", "Drasko", "Janjevic", "drasko2501", "25/01/1982");
        signupSection.signupUser(email, password, firstName, lastName, username, dateOfBirth);
    }

    @Test(groups = {"Smoke"}, dataProvider = "UserDataProvider", dataProviderClass = UsersDataProvider.class)
    public void verifySignupUser(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        SignupSection signupSection = new SignupSection(requestSpec);

        Response response = signupSection.signupUser(email, password, firstName, lastName, username, dateOfBirth);
        AssertsSignup assertsSignup = new AssertsSignup();
        assertsSignup.successfullySingup(response.getStatusCode(), (String) ResposeHandler.getSingleValueFromResponse(response, "success"));
    }

    @Test(groups = {"Regression"}, dataProvider = "UserDataProvider", dataProviderClass = UsersDataProvider.class)
    public void verifySignupExistingUser(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        SignupSection signupSection = new SignupSection(requestSpec);
        //Response response = signupSection.signupUser("drasko2501@gmail.com", "drasko250182", "Drasko", "Janjevic", "drasko2501", "25/01/1982");

        signupSection.signupUser(email, password, firstName, lastName, username, dateOfBirth);
        Response response = signupSection.signupUser(email, password, firstName, lastName, username, dateOfBirth);
        List<Object> emailKey = ResposeHandler.getArrayFromResponse(response, "email");
        List<Object> userKey = ResposeHandler.getArrayFromResponse(response, "username");
        AssertsSignup assertsSignup = new AssertsSignup();
        assertsSignup.existingUserSingup(response.getStatusCode(), String.valueOf(emailKey), String.valueOf(userKey));
    }

    @Test(groups = {"Regression"}, dataProvider = "UserDataProviderWithoutEmail", dataProviderClass = UsersDataProvider.class)
    public void verifySignupWithoutEmail(String password, String firstName, String lastName, String username, String dateOfBirth) {
        SignupSection signupSection = new SignupSection(requestSpec);

        Response response = signupSection.signupUser(password, firstName, lastName, username, dateOfBirth);
        List<Object> emailKey = ResposeHandler.getArrayFromResponse(response, "email");

        AssertsSignup assertsSignup = new AssertsSignup();
        assertsSignup.userWithoutEmailSingup(response.getStatusCode(), String.valueOf(emailKey));
    }

}
