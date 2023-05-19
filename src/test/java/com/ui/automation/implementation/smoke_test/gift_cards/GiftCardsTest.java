package com.ui.automation.implementation.smoke_test.gift_cards;

import com.ui.automation.implementation.BaseTest;
import com.ui.model.pages.card_price.CardPricePage;
import com.ui.model.pages.main.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static com.ui.model.pages.constants.GiftsCardsConstants.*;

public class GiftCardsTest extends BaseTest {

    @Test
    @Issue("https://tickets.sestcompany.com/browse/TEST-11111")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that gift card will added and deleted correctly in gift price page.")
    public void checkGiftCardsTestWillAppearedInGiftCardPricePageTest() {
        CardPricePage cardPricePage = new MainPage()
                .openWindow()
                .waitPageLoading(15, TimeUnit.SECONDS)
                .openItemOnTopMenu(GIFT_CARDS)
                .waitPageLoading(15, TimeUnit.SECONDS)
                .selectSectionInLeftMenu(DELIVERY_TYPE, PRINT_AT_HOME)
                .selectSectionInLeftMenu(FORMAT, PRINT_FOLD)
                .selectItemByIndexInRow(3)
                .setCardDesignGift()
                .setAmountGift(AMOUNT)
                .addToCart()
                .waitUntilAddToCardNotificationAppeared()
                .openCartPricePage();

        Assert.assertTrue(cardPricePage.isSubTotalHaveCorrectValue(FIRST_AMOUNT));
        Assert.assertTrue(cardPricePage.isTotalHaveCorrectValue(GIFT_AMOUNT));

        cardPricePage.deleteCard();
        Assert.assertTrue(cardPricePage.isSubTotalHaveCorrectValue(EMPTY_VALUE));
        Assert.assertTrue(cardPricePage.isTotalHaveCorrectValue(EMPTY_AMOUNT));
    }
}
