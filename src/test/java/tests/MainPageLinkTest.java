package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class MainPageLinkTest {

    private WebDriver driver;
    private final static String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private By headerLogoScooter = By.className("Header_LogoScooter__3lsAR");
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

    //Логотип Самокат ведет на главную страницу Яндекс.Самокат
    @Test
    public void checkScooterLogoLinkOpenMainPage() {
        MainPage objMainPage = new MainPage(driver);
        driver.get(MAIN_PAGE_URL);
        WebElement link = driver.findElement(headerLogoScooter);
        link.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Ссылка Самокат не открывает главную страницу Яндекс.Самокат", MAIN_PAGE_URL, currentUrl);
    }

}
