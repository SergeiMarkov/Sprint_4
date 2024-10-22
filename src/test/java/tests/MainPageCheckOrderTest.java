package tests;

import org.junit.Test;
import pageobject.MainPage;
import static org.junit.Assert.*;

public class MainPageCheckOrderTest extends BaseTest {

    public String inputTrack = "356100";

    // Ввод неверного номера заказа ведет на страницу с картинкой "Такого заказа нет"
    @Test
    public void wrongTrackNotFoundTest () {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnHeaderOrderStatusButton();
        mainPage.setInputTrack(inputTrack);
        mainPage.clickOnGoButton();
        mainPage.waitForLoadNotFoundPic();

        assertTrue(driver.findElement(mainPage.trackImgNotFound).isDisplayed());
    }

}
