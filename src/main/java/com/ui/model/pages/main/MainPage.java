package com.ui.model.pages.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.ui.core.report.TestReporter;
import com.ui.core.utils.WaitingUtils;
import com.ui.model.pages.BasePage;
import com.ui.model.pages.gift_card_filter.GiftsCardsFilters;
import com.ui.model.properties.PropertiesData;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.ui.model.providers.DataProviders.getValue;

public class MainPage extends BasePage<MainPage> {
    public static final String PAGE_NAME = "main_page";
    private static final By AMAZON_TITTLE = Selectors.byId(getValue(PAGE_NAME, "amazon_tittle_element"));
    private final String navContainer = getValue(PAGE_NAME, "nav_container");

    public MainPage() {
        super(PropertiesData.ENVIRONMENT_URL, AMAZON_TITTLE);
    }

    /**
     * Open Gift filter Cards page
     *
     * @return Schedule page instance
     */
    public GiftsCardsFilters openItemOnTopMenu(String menuItem) {
        SelenideElement navContainerElement = $(Selectors.byXpath(String.format(navContainer, menuItem)));
        navContainerElement.shouldBe(Condition.visible).click();
        TestReporter.reportDebugStep("Gifts was opened");
        return new GiftsCardsFilters();
    }
}
