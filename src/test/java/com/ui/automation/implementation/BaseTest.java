package com.ui.automation.implementation;


import com.codeborne.selenide.Configuration;
import com.ui.core.driver.WebDriverHolder;
import com.ui.listeners.ApplicationTestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(ApplicationTestListener.class)
public class BaseTest {

    @BeforeClass
    public void init() {
        Configuration.timeout = 60000;
        Configuration.collectionsTimeout = 60000;
    }

    public void initDriver(String browserName, String hub) {
        new WebDriverHolder(browserName, hub);
    }
}