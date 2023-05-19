package com.ui.model.pages.product_page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.ui.core.exceptions.ExceptionError;
import com.ui.core.report.TestReporter;
import com.ui.core.utils.WaitingUtils;
import com.ui.model.pages.BasePage;
import com.ui.model.pages.card_price.CardPricePage;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.ui.model.providers.DataProviders.getValue;

public class ProductPage extends BasePage<ProductPage> {
    private static final String PAGE_NAME = "product_page";
    private static final By NAV_LOGO = Selectors.byXpath(getValue(PAGE_NAME, "tittle"));
    private final String amountGift = getValue(PAGE_NAME, "amount_card");
    private final SelenideElement cardDesignGift = $(Selectors.byId(getValue(PAGE_NAME, "gift_design_card")));
    private final SelenideElement addToCartButton = $(Selectors.byName(getValue(PAGE_NAME, "add_card")));
    private final SelenideElement addedToCartNotification = $(Selectors.byText(getValue(PAGE_NAME, "added_to_cart_notification")));
    private final SelenideElement cardPriceButton = $(Selectors.byId(getValue(PAGE_NAME, "card_price_button")));

    public ProductPage() {
        super(NAV_LOGO);
    }

    /**
     * Open Gift Cards page
     *
     * @return GiftsCardsFilters page instance
     */
    public ProductPage setCardDesignGift() {
        cardDesignGift.shouldBe(Condition.visible).click();
        TestReporter.reportDebugStep("Card design was set");
        return this;
    }

    /**
     * Open Gift Cards page
     *
     * @param amount amount of gift
     * @return GiftsCardsFilters page instance
     */
    public ProductPage setAmountGift(String amount) {
        SelenideElement amountGiftElement = $(Selectors.byXpath(String.format(amountGift, amount)));
        amountGiftElement.shouldBe(Condition.visible).click();
        TestReporter.reportDebugStep("%s - amount was set", amount);
        return this;
    }

    /**
     * Add to cart
     *
     * @return ProductPage page instance
     */
    public ProductPage addToCart() {
        addToCartButton.shouldBe(Condition.visible).click();
        TestReporter.reportDebugStep("Add to Card button was clicked");
        return this;
    }

    /**
     * wait Until Add To Card Notification Appeared
     *
     * @return ProductPage instance, otherwise throw new Exception
     */
    public ProductPage waitUntilAddToCardNotificationAppeared() {
        if (WaitingUtils.isSelenideElement(Condition.visible, addedToCartNotification, 15, TimeUnit.SECONDS)) {
            return this;
        } else {
            throw new ExceptionError("Added to Cart notification wasn't appeared");
        }
    }

    /**
     * Ope card price page
     *
     * @return ProductPage instance, otherwise throw new Exception
     */
    public CardPricePage openCartPricePage() {
        cardPriceButton.shouldBe(Condition.visible).click();
        TestReporter.reportDebugStep("Card page was opened");
        return new CardPricePage();
    }
}
