package tests;

import org.junit.Test;
import pageobject.MainPage;

import static org.junit.Assert.assertEquals;
import static pageobject.MainPage.MAIN_PAGE_URL;

public class MainPageLinkTest extends BaseTest{

    //Логотип Самокат ведет на главную страницу Яндекс.Самокат
    @Test
    public void checkScooterLogoLinkOpenMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickOnAcceptCookieButton();
        driver.get(MAIN_PAGE_URL);
        mainPage.clickOnLinkScooter();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Ссылка Самокат не открывает главную страницу Яндекс.Самокат", MAIN_PAGE_URL, currentUrl);
    }

}
