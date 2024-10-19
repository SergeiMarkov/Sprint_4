package tests;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageobject.MainPage;
import pageobject.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)

public class OrderPageTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rent;
    private final String color;
    private final String comment;
    private final String expectedSuccessText = "Заказ оформлен";
    private final static String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public OrderPageTest (String name, String surname, String address,
                          String metro, String phone, String date,
                          String rent, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rent = rent;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] dataForOrder() {
        return new Object[][] {
                {"Неяснов", "Непонятнов", "Москва, ул. 9-я Паркова, д.52к3", "Первомайская", "+79991234320", "23.10.2024", "двое суток", "чёрный жемчуг", "Заработаешь, и на том спасибо"},
                {"Как", "Этоделатев", "Москва, ул. 1812 года, дом 14с1", "Парк Победы", "89777651221", "10.11.2024", "трое суток", "серая безысходность", "Буду на это надеяться"},
        };
    }

    @Before
    public void begin() {
        ChromeOptions options = new ChromeOptions(); // в браузере Chrome тест ожидаемо падает
        driver = new ChromeDriver(options);
        driver.get(MAIN_PAGE_URL);

//        FirefoxOptions options = new FirefoxOptions(); //*в браузере Firefox тест проходит,
//        driver = new FirefoxDriver(options);           // использовал для проверки работы теста
//        driver.get(MAIN_PAGE_URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    // Кликаем кнопку "Заказать" вверху страницы, заполняем все поля тестовыми данными.
    // Ожидаемый результат: статус "Заказ оформлен"
    @Test
    public void orderAfterClickOnHeaderOrderButton () {
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        objMainPage.waitForLoad();
        objMainPage.clickOnAcceptCookieButton();
        objMainPage.clickOnHeaderOrderButton();

        objOrderPage.waitForLoad();
        objOrderPage.setInputName(name);
        objOrderPage.setInputSurname(surname);
        objOrderPage.setInputAddress(address);
        objOrderPage.setInputMetro(metro);
        objOrderPage.setInputPhone(phone);

        objOrderPage.clickOnNextButton();

        objOrderPage.waitForLoad();
        objOrderPage.setInputDate(date);
        objOrderPage.setRentPeriod(rent);
        objOrderPage.setScooterColor(color);
        objOrderPage.setInputComment(comment);

        objOrderPage.clickOnOrderButton();
        objOrderPage.clickOnYesButton();
        objOrderPage.getOrderPlacedText();

        MatcherAssert.assertThat("Не удалось создать новый заказ",
                objOrderPage.getOrderPlacedText(),
                containsString(expectedSuccessText));
    }

    // Кликаем кнопку "Заказать" внизу страницы, заполняем все поля тестовыми данными.
    // Ожидаемый результат: статус "Заказ оформлен"
    @Test
    public void orderAfterClickOnMiddleOrderButton () {
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        objMainPage.waitForLoad();
        objMainPage.clickOnAcceptCookieButton();
        objMainPage.clickOnMiddleOrderButton();

        objOrderPage.waitForLoad();
        objOrderPage.setInputName(name);
        objOrderPage.setInputSurname(surname);
        objOrderPage.setInputAddress(address);
        objOrderPage.setInputMetro(metro);
        objOrderPage.setInputPhone(phone);

        objOrderPage.clickOnNextButton();

        objOrderPage.waitForLoad();
        objOrderPage.setInputDate(date);
        objOrderPage.setRentPeriod(rent);
        objOrderPage.setScooterColor(color);
        objOrderPage.setInputComment(comment);

        objOrderPage.clickOnOrderButton();
        objOrderPage.clickOnYesButton();
        objOrderPage.getOrderPlacedText();

        MatcherAssert.assertThat("Не удалось создать новый заказ",
                objOrderPage.getOrderPlacedText(),
                containsString(expectedSuccessText));
    }

}