package com.ui.model.pages.card_price;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.ui.core.report.TestReporter;
import com.ui.model.pages.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.ui.model.providers.DataProviders.getValue;

public class CardPricePage extends BasePage<CardPricePage> {
    private static final String PAGE_NAME = "shopping_cart_page";
    private static final By SHOPPING_CAR_TITTLE = Selectors.byXpath(getValue(PAGE_NAME, "shopping_car_tittle"));
    private final SelenideElement subTotal = $(Selectors.byXpath(getValue(PAGE_NAME, "sub_total")));
    private final SelenideElement subAmountTotal = $(Selectors.byXpath(getValue(PAGE_NAME, "sub_amount_total")));
    private final SelenideElement deleteCardLink = $(Selectors.byXpath(getValue(PAGE_NAME, "delete_card_link")));

    public CardPricePage() {
        super(SHOPPING_CAR_TITTLE);
    }


    /**
     * Verify sub total
     *
     * @return true if value correct, otherwise false
     */
    public boolean isSubTotalHaveCorrectValue(String value) {
        boolean isSubTotalCorrect = subTotal.shouldBe(Condition.visible).text().contains(value);
        TestReporter.reportDebugStep("%s - sub total value, the result is: %s", value, isSubTotalCorrect);
        return isSubTotalCorrect;
    }

    /**
     * Verify sub amount total
     *
     * @return true if value correct, otherwise false
     */
    public boolean isTotalHaveCorrectValue(String value) {
        boolean isSubTotalCorrect = subAmountTotal.shouldBe(Condition.visible).text().contains(value);
        TestReporter.reportDebugStep("%s - sub total value, the result is: %s", value, isSubTotalCorrect);
        return isSubTotalCorrect;
    }

    /**
     * Delete card from shopping
     */
    public void deleteCard() {
        deleteCardLink.shouldBe(Condition.visible).click();
        TestReporter.reportDebugStep("delete card link was clicked");
    }
}
