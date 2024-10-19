package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.MainPage;

import static org.junit.Assert.*;

public class MainPageCheckOrderTest {
    private WebDriver driver;
    private final static String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private final String inputTrack = "356100";

    @Before
    public void begin() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get(MAIN_PAGE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Ввод неверного номера заказа ведет на страницу с картинкой "Такого заказа нет"
    @Test
    public void wrongTrackNotFound () {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoad();
        objMainPage.clickOnHeaderOrderStatusButton();
        objMainPage.setInputTrack(inputTrack);
        objMainPage.clickOnGoButton();
        objMainPage.waitForLoadNotFoundPic();

        assertTrue(driver.findElement(objMainPage.trackImgNotFound).isDisplayed());

    }

}
