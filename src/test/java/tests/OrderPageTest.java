package tests;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.MainPage;
import pageobject.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)

public class OrderPageTest extends BaseTest {

    public String name;
    public String surname;
    public String address;
    public String metro;
    public String phone;
    public String date;
    public String rent;
    public String color;
    public String comment;
    public String expectedSuccessText = "Заказ оформлен";

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

    // Кликаем кнопку "Заказать" вверху страницы, заполняем все поля тестовыми данными.
    // Ожидаемый результат: статус "Заказ оформлен"
    @Test
    public void orderAfterClickOnHeaderOrderButtonTest () {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        MainPage.waitForLoad();
        MainPage.clickOnAcceptCookieButton();

        mainPage.clickOnHeaderOrderButton();

        orderPage.fillOrdersFields(name, surname, address,
                metro, phone, date,
                rent, color, comment);

        orderPage.checkOrderAfterFill();

        MatcherAssert.assertThat("Не удалось создать новый заказ",
                orderPage.getOrderPlacedText(),
                containsString(expectedSuccessText));
    }

    // Кликаем кнопку "Заказать" внизу страницы, заполняем все поля тестовыми данными.
    // Ожидаемый результат: статус "Заказ оформлен"
    @Test
    public void orderAfterClickOnMiddleOrderButtonTest () {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        MainPage.waitForLoad();
        MainPage.clickOnAcceptCookieButton();

        mainPage.clickOnMiddleOrderButton();

        orderPage.fillOrdersFields(name, surname, address,
                metro, phone, date,
                rent, color, comment);

        orderPage.checkOrderAfterFill();

        MatcherAssert.assertThat("Не удалось создать новый заказ",
                orderPage.getOrderPlacedText(),
                containsString(expectedSuccessText));
    }

}