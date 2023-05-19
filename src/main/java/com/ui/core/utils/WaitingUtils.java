package com.ui.core.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;

import java.util.concurrent.TimeUnit;

public class WaitingUtils {

    /**
     * Make a delay
     *
     * @param time     time
     * @param timeUnit time unit
     */
    public static void delay(long time, TimeUnit timeUnit) {
        try {
            Thread.sleep(timeUnit.toMillis(time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Explicitly waiter for <i>SelenideElement</i> with <b>Selenide</b> condition.
     * If element not execute condition, exception will not throw.
     *
     * @param condition       waiting exit condition
     * @param selenideElement web element
     * @param time            wait time
     * @param timeUnit        unit of time
     * @return true or false
     */
    public static boolean isSelenideElement(Condition condition, SelenideElement selenideElement, long time, TimeUnit timeUnit) {
        try {
            selenideElement.waitUntil(condition, timeUnit.toMillis(time));
            return true;
        } catch (UIAssertionError e) {
            return false;

        }
    }
}

