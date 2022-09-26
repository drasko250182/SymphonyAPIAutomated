package com.symphony.api.provider;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class UsersDataProvider {

    @DataProvider(name = "UsersDataProvider")
    public static Object[][] getUsers() {
        Faker faker = new Faker();

        return new Object[][]{
                {faker.internet().emailAddress(), faker.internet().password(), faker.address().firstName(), faker.address().lastName(), faker.address().buildingNumber(), "25/01/1982"},
                {faker.internet().emailAddress(), faker.internet().password(), faker.address().firstName(), faker.address().lastName(), faker.address().buildingNumber(), "25/01/1982"},

        };

    }

    @DataProvider(name = "UserDataProvider")
    public static Object[][] getUser() {
        Faker faker = new Faker();

        return new Object[][]{
                {faker.internet().emailAddress(), faker.internet().password(), faker.address().firstName(), faker.address().lastName(), faker.address().buildingNumber(), "25/01/1982"},
        };

    }

    @DataProvider(name = "UserDataProviderWithoutEmail")
    public static Object[][] getUserWithoutEmail() {
        Faker faker = new Faker();

        return new Object[][]{
                {faker.internet().password(), faker.address().firstName(), faker.address().lastName(), faker.address().buildingNumber(), "25/01/1982"},
        };

    }
}
