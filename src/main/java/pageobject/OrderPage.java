package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// Класс для страницы заказа
public class OrderPage {
    private final WebDriver driver;

    // Локатор формы заказа
    private By orderForm = By.xpath(".//div[@class='Order_Form__17u6u']");

    // Локаторы для страницы заказа "Для кого самокат"

    // Локатор для поля "Имя"
    private By inputName = By.xpath(".//input[contains(@placeholder,'Имя')]");
    // Локатор для поля "Фамилия"
    private By inputSurname = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    // Локатор для поля "Адрес"
    private By inputAddress = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    // Локатор для поля "Станция метро"
    private By inputMetro = By.xpath(".//input[contains(@placeholder,'Станция')]");
    // Локатор для доступных станций метро из выпадающего списка
    private By metroListValues = By.xpath(".//div[@class='select-search__select']//div[@class='Order_Text__2broi']");
    // Локатор для поля "Телефон"
    private By inputPhone = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    // Локатор кнопки "Далее"
    private By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локаторы для страницы заказа "Про аренду"

    // Локатор поля "Когда привезти самокат"
    private By inputDate = By.xpath(".//input[contains(@placeholder,'Когда')]");
    // Локатор выбранной даты
    private By selectDate = By.className("react-datepicker__day--selected");
    // Локатор календаря
    private By calendarLocate = By.xpath(".//div[@class='react-datepicker__month-container']");
    // Локатор выпадающего списка "Срок аренды"
    private By rentPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");
    // Локатор выбора срока аренды
    private By chooseRentPeriod = By.className("Dropdown-option");
    // Локатор выбора цвета самоката
    private By scooterColor = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]//label");
    // Локатор поля "Комментарий"
    private By inputComment = By.xpath(".//input[contains(@placeholder,'Комментарий')]");
    // Локатор кнопки "Заказать"
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //Локатор кнопки "Да"
    private By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Локатор всплывающего окна с подтверждением заказа
    private By orderPlace = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");


    // Конструктор класса OrderPage
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для ожидания загрузки страницы
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }

    //Метод для выбора элемента из выпадающего списка
    private void clickOnElementFromDropdown(By dropdownElements, String elementToChoose) {
        List<WebElement> elementsFiltered = driver.findElements(dropdownElements);
        for (WebElement element : elementsFiltered) {
            if (element.getText().equals(elementToChoose)) {
                element.click();
                break;
            }
        }
    }

    // Метод для заполнения поля "Имя"
    public void setInputName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    // Метод для заполнения поля "Фамилия"
    public void setInputSurname(String surname) {
        driver.findElement(inputSurname).sendKeys(surname);
    }

    // Метод для заполнения поля "Адрес"
    public void setInputAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    // Метод для выбора станции метро
    public void setInputMetro(String metro) {
        driver.findElement(inputMetro).sendKeys(metro);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(metroListValues));
        clickOnElementFromDropdown(metroListValues, metro);
    }

    // Метод для заполнения поля "Телефон"
    public void setInputPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    // Метод клика по кнопке "Далее"
    public void clickOnNextButton() {
        driver.findElement(nextButton).click();
    }

    // Метод установки даты в поле "Когда привезти самокат"
    public void setInputDate(String date) {
        driver.findElement(inputDate).sendKeys(date);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(calendarLocate));
        driver.findElement(selectDate).click();
    }

    // Метод клика на поле "Срок аренды"
    public void setRentPeriod(String rent) {
        driver.findElement(rentPeriod).click();
        clickOnElementFromDropdown(chooseRentPeriod, rent);
    }

    // Метод выбора цвета
    public void setScooterColor(String color) {
        clickOnElementFromDropdown(scooterColor, color);
    }

    // Метод для заполнения поля "Комментарий"
    public void setInputComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    // Метод клика на кнопку "Заказать"
    public void clickOnOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Метод клика на кнопку "Да" во всплывающем окне
    public void clickOnYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        driver.findElement(yesButton).click();
    }

    // Метод получения текста об успешности заказа
    public String getOrderPlacedText() {
        return driver.findElement(orderPlace).getText();
    }

    // Метод для заполнения полей заказа
    public void fillOrdersFields(String name, String surname, String address,
                                 String metro, String phone, String date,
                                 String rent, String color, String comment) {
        waitForLoad();
        setInputName(name);
        setInputSurname(surname);
        setInputAddress(address);
        setInputMetro(metro);
        setInputPhone(phone);

        clickOnNextButton();

        waitForLoad();
        setInputDate(date);
        setRentPeriod(rent);
        setScooterColor(color);
        setInputComment(comment);

    }
    // Метод для проверки заказа после заполнения полей заказа
    public void checkOrderAfterFill() {
        clickOnOrderButton();
        clickOnYesButton();
        getOrderPlacedText();
    }

}
