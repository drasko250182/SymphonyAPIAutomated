package com.symphony.api.tests;

import com.symphony.api.asserts.AssertPosts;
import com.symphony.api.provider.UsersDataProvider;
import com.symphony.api.rest.sections.LoginSection;
import com.symphony.api.rest.sections.PostsSection;
import com.symphony.api.rest.sections.SignupSection;
import com.symphony.api.utils.ResposeHandler;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PostsTests extends BaseTest {

    @Test(groups = {"Smoke"}, dataProvider = "UserDataProvider", dataProviderClass = UsersDataProvider.class)
    public void verifyAddPost(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        SignupSection signupSection = new SignupSection(requestSpec);
        signupSection.signupUser(email, password, firstName, lastName, username, dateOfBirth);

        LoginSection loginSection = new LoginSection(requestSpec);
        Response response = loginSection.login(username, password);
        String token = ResposeHandler.getToken(response);

        PostsSection postsSection = new PostsSection(requestSpec);
        response = postsSection.addPost(token,"new post");

        AssertPosts assertPosts = new AssertPosts();
        assertPosts.successfullyAddPost(response, firstName, lastName, "new post");
    }

    @Test(groups = {"Smoke"}, dataProvider = "UserDataProvider", dataProviderClass = UsersDataProvider.class)
    public void verifyAddComment(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        SignupSection signupSection = new SignupSection(requestSpec);
        signupSection.signupUser(email, password, firstName, lastName, username, dateOfBirth);

        LoginSection loginSection = new LoginSection(requestSpec);
        Response response = loginSection.login(username, password);
        String token = ResposeHandler.getToken(response);

        PostsSection postsSection = new PostsSection(requestSpec);
        response = postsSection.addPost(token,"new post");
        Object id = ResposeHandler.getSingleValueFromResponse(response, "id");
        response = postsSection.addComment(token, "new comment", (Integer) id);

        AssertPosts assertPosts = new AssertPosts();
        assertPosts.successfullyAddComment(response, firstName, lastName, "new comment");

    }

    @Test(groups = {"Smoke"}, dataProvider = "UserDataProvider", dataProviderClass = UsersDataProvider.class)
    public void verifyGetComment(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        SignupSection signupSection = new SignupSection(requestSpec);
        signupSection.signupUser(email, password, firstName, lastName, username, dateOfBirth);

        LoginSection loginSection = new LoginSection(requestSpec);
        Response response = loginSection.login(username, password);
        String token = ResposeHandler.getToken(response);

        PostsSection postsSection = new PostsSection(requestSpec);
        response = postsSection.addPost(token,"new post");
        Object postId = ResposeHandler.getSingleValueFromResponse(response, "id");
        Object id = ResposeHandler.getSingleValueFromResponse(response, "id");
        response = postsSection.addComment(token, "new comment", (Integer) id);
        response = postsSection.getComment(token, (Integer) postId);

        AssertPosts assertPosts = new AssertPosts();
        assertPosts.assertGetComment(response, (Integer) postId, firstName, lastName, "new comment");
    }

}
