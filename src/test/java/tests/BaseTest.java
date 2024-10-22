package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import static pageobject.MainPage.MAIN_PAGE_URL;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void startUp(){
        initChrome();
        //initFirefox();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    public void initChrome(){
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get(MAIN_PAGE_URL);
    }

    public void initFirefox(){
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.get(MAIN_PAGE_URL);
    }

}
