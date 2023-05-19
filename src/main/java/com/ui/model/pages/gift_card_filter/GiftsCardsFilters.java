package com.ui.model.pages.gift_card_filter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.ui.core.report.TestReporter;
import com.ui.model.pages.BasePage;
import com.ui.model.pages.product_page.ProductPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.ui.model.providers.DataProviders.getValue;

public class GiftsCardsFilters extends BasePage<GiftsCardsFilters> {
    private static final String PAGE_NAME = "gifts_cards_filter";
    private static final By GIFT_LEFT_PANEL = Selectors.byId(getValue(PAGE_NAME, "gifts_left_panel"));
    private final String giftsCards = getValue(PAGE_NAME, "gifts_cards_item");
    private final String cardItem = getValue(PAGE_NAME, "card_item");

    public GiftsCardsFilters() {
        super(GIFT_LEFT_PANEL);
    }


    /**
     * Set item in left menu
     *
     * @param mainSection   section to set main filter
     * @param subSectionIem item to set filter for sub
     * @return current instance
     */
    public GiftsCardsFilters selectSectionInLeftMenu(String mainSection, String subSectionIem) {
        SelenideElement giftItemElement = $(Selectors.byXpath(String.format(giftsCards, mainSection, subSectionIem)));
        giftItemElement.shouldBe(Condition.visible).click();
        TestReporter.reportDebugStep(" %s - main section was opened, sub section is: %s", mainSection, subSectionIem);
        return this;
    }

    /**
     * Opent Gift product by index
     *
     * @param cardIndex index of product
     * @return ProductPage instance
     */
    public ProductPage selectItemByIndexInRow(int cardIndex) {
        SelenideElement giftItemElement = $(Selectors.byXpath(String.format(cardItem, cardIndex)));
        giftItemElement.shouldBe(Condition.visible).click();
        TestReporter.reportDebugStep("Gift product was opened by %s index", cardIndex);
        return new ProductPage();
    }
}
