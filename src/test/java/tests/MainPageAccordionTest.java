package tests;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.MainPage;
import static org.hamcrest.CoreMatchers.equalTo;
import static pageobject.ConstantsForAccordion.*;

@RunWith(Parameterized.class)

public class MainPageAccordionTest extends BaseTest {

    public int indexNumber;
    public String expectedAccordionHeader;
    public String expectedAccordionText;

    public MainPageAccordionTest(int indexNumber, String expectedAccordionHeader, String expectedAccordionText) {
        this.indexNumber = indexNumber;
        this.expectedAccordionHeader = expectedAccordionHeader;
        this.expectedAccordionText = expectedAccordionText;
    }

    @Parameterized.Parameters
    public static Object[][] headersAndText() {
        return new Object[][]{
                {0, HOW_MUCH_0, PRICE_DAY_0},
                {1, WANT_MORE_SCOOTER_1, ONE_ORDER_ONE_SCOOTER_1},
                {2, HOW_CALCULATE_2, ABOUT_RENT_TIME_2},
                {3, WANT_SCOOTER_TODAY_3, ONLY_TOMORROW_3},
                {4, WANT_EXTEND_RETURN_4, URGENT_1010_4},
                {5, WANT_CHARGER_5, FULL_CHARGED_5},
                {6, WANT_CANCEL_ORDER_6, ALL_OWN_6},
                {7, BEYOND_MKAD_7, DELIVERY_AREA_7},
        };
    }

    // Заголовки и текст соответствуют тестовым данным
    @Test
    public void checkAccordionHeaderAndTextIsCorrectTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickOnAcceptCookieButton();
        mainPage.clickAccordionHeader(indexNumber);
        mainPage.waitForLoadAccordionText(indexNumber);

        MatcherAssert.assertThat("Неверный текст заголовка",
                expectedAccordionHeader,
                equalTo(mainPage.getAccordionHeaderText(indexNumber)));

        MatcherAssert.assertThat("Неверный выпадающий текст",
                expectedAccordionText,
                equalTo(mainPage.getAccordionText(indexNumber)));
        // Тест падает на седьмом шаге, ошибка в тексте заголовка "Я жи_з_у за МКАДом, привезёте?"
    }

}