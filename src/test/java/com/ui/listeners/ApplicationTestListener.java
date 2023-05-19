package com.ui.listeners;

import com.ui.automation.implementation.BaseTest;
import com.ui.core.report.TestReporter;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.*;
import org.testng.internal.IResultListener2;

import static com.ui.core.driver.WebDriverHolder.closeDriver;
import static com.ui.model.properties.PropertiesData.getBrowser;
import static com.ui.model.properties.PropertiesData.getHubServer;


public class ApplicationTestListener extends TestListenerAdapter implements
        IReporter, IExecutionListener, ISuiteListener, IResultListener2 {

    private final AllureTestNg allureTestNg = new AllureTestNg();
    private final BaseTest baseTest = new BaseTest();
    private final String browserValue = getBrowser();
    private final String hub = getHubServer();

    @Override
    public void onExecutionStart() {
    }

    @Override
    public void beforeConfiguration(ITestResult testResult) {
        if (testResult.getMethod().getMethodName().equalsIgnoreCase("terminate")) {
            baseTest.initDriver(browserValue, hub);
        }
    }

    @Override
    public void onStart(ISuite suite) {
        allureTestNg.onStart(suite);
    }

    @Override
    public void onStart(ITestContext testContext) {
        allureTestNg.onStart(testContext);
    }

    @Override
    public void onTestStart(ITestResult testResult) {
        baseTest.initDriver(browserValue, hub);
        allureTestNg.onTestStart(testResult);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        TestReporter.logger.info("Test was successfully completed: " + testResult.getName());
        closeDriver();
        allureTestNg.onTestSuccess(testResult);
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        TestReporter.logger.info("Test was skipped: " + testResult.getName());
        closeDriver();
        allureTestNg.onTestSkipped(testResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
        allureTestNg.onTestFailedButWithinSuccessPercentage(testResult);
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        TestReporter.logger.info("Test was failed: " + testResult.getName());
        closeDriver();
        allureTestNg.onTestFailure(testResult);
    }

    @Override
    public void onConfigurationFailure(ITestResult testResult) {

    }

    @Override
    public void onFinish(ITestContext testContext) {
        allureTestNg.onFinish(testContext);
    }

    @Override
    public void onFinish(ISuite suite) {
        allureTestNg.onFinish(suite);
    }
}