package com.ui.core.driver;

import com.codeborne.selenide.WebDriverRunner;
import com.ui.core.exceptions.ExceptionError;
import com.ui.core.report.TestReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;


public class WebDriverHolder {
    private static final ThreadLocal<WebDriver> DRIVER_HOLDER = new ThreadLocal<>();

    /**
     * Web driver constructor
     *
     * @param browser browser value
     * @param hub     hub server
     */
    public WebDriverHolder(String browser, String hub) {
        this.setDriver(browser, hub);
    }

    public WebDriverHolder() {

    }

    /**
     * Set web driver to thread local holder.
     *
     * @param browser web driver instance
     * @param hub     hub ip instance
     */
    private void setDriver(String browser, String hub) {
        WebDriver driver;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (browser.equalsIgnoreCase("chrome")) {
            desiredCapabilities.setBrowserName(browser);
            driver = new RemoteWebDriver(getUrl(hub), desiredCapabilities);
            WebDriverRunner.setWebDriver(driver);
            DRIVER_HOLDER.set(driver);
        } else {
            throw new ExceptionError("there are not correct browser for web driver initialization");
        }
        getDriver().manage().window().maximize();
    }

    /**
     * Getting thread-safe web driver instance.
     *
     * @return web driver instance
     */

    public static WebDriver getDriver() {
        return DRIVER_HOLDER.get();
    }


    /**
     * Quit work of web driver.
     * Remove web driver instance from thread local holder.
     */
    public static void closeDriver() {
        try {
            if (Objects.nonNull(DRIVER_HOLDER.get())) {
                DRIVER_HOLDER.get().quit();
                DRIVER_HOLDER.remove();
            }
        } catch (WebDriverException exception) {
            TestReporter.reportErrorStep("Session timed out or not found");
        }
    }

    private URL getUrl(String hub) {
        try {
            return new URL(hub);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
