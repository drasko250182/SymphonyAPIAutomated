package com.symphony.api.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.symphony.api.rest.sections.BaseSection;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest extends BaseSection {

    public BaseSection baseSection = new BaseSection();

    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest(alwaysRun=true)
    public void setBaseURI() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://randomlyapi.symphony.is/api").
                build();
    }

    @AfterMethod(alwaysRun=true)
    public void updateRequestSpec() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://randomlyapi.symphony.is/api").
                build();
    }

    @BeforeTest
    public void config()
    {
        htmlReporter = new ExtentHtmlReporter("C:\\Users\\Dragoje Janjevic\\Documents\\Docs\\Septembar 2022\\Reports\\ExtentReportsTestNG.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterTest
    public void tearDown()
    {
        extent.flush();
    }
}
