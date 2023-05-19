package com.ui.model.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.ui.core.exceptions.ExceptionError;
import com.ui.core.report.TestReporter;
import com.ui.core.utils.WaitingUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.ui.model.pages.main.MainPage.PAGE_NAME;
import static com.ui.model.providers.DataProviders.getValue;

public abstract class BasePage<PAGE extends BasePage<PAGE>> {
    private String pageURL;
    private final SelenideElement pageLoadingElement;
    private final SelenideElement dismissModuleWindow = $(Selectors.byXpath(getValue(PAGE_NAME, "dismiss_module_window")));

    protected BasePage(String pageURL, By pageLoadingElement) {
        this(pageLoadingElement);
        this.pageURL = pageURL;
    }

    protected BasePage(By pageLoadingElement) {
        this.pageLoadingElement = $(pageLoadingElement);
    }


    public PAGE openWindow() {
        if (StringUtils.isNotEmpty(pageURL)) {
            open(pageURL);
            TestReporter.reportDebugStep("%s was opened", this.getClass().getSimpleName());
        } else {
            throw new ExceptionError("Page URL not establish");
        }
        verifyThatDismissWindowAppeared();
        return (PAGE) this;
    }

    /**
     * Wait page loading.
     * Loading locator use from constructor <b>pageLoadingLocator</b> argument.
     *
     * @param time     wait time
     * @param timeUnit unit of time
     * @return current page instance
     */
    public PAGE waitPageLoading(long time, TimeUnit timeUnit) {
        TestReporter.reportDebugStep("Wait %s loading", this.getClass().getSimpleName());
        pageLoadingElement.waitUntil(visible, timeUnit.toMillis(time));
        return (PAGE) this;
    }

    /**
     * Close module window
     */
    public void verifyThatDismissWindowAppeared() {
        if (WaitingUtils.isSelenideElement(Condition.visible, dismissModuleWindow, 5, TimeUnit.SECONDS)) {
            dismissModuleWindow.shouldBe(Condition.visible).click();
            TestReporter.reportDebugStep("Dismiss window was clicked");
        }
    }
}
