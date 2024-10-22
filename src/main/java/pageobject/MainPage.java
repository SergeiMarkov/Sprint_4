package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


// Класс для главной страницы
public class MainPage {
    private static WebDriver driver;
    // Константа URL для главной страницы Яндекс.Самокат
    public final static String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    // Локатор логотипа Яндекс
    private final By headerLogoYandex = By.className("Header_LogoYandex__3TSOI");
    // Локатор логотипа Самокат
    private final By headerLogoScooter = By.className("Header_LogoScooter__3lsAR");
    // Локатор кнопки "Заказать" вверху страницы
    private final By headerOrderButton = By.className("Button_Button__ra12g");
    // Локатор кнопки "Принять куки"
    private static final By acceptCookieButton = By.className("App_CookieButton__3cvqF");
    // Локатор для кнопки "Заказать" внизу страницы
    private final By middleOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локатор ссылки "Статус заказа" ввурху страницы
    private final By headerOrderStatusLink = By.className("Header_Link__1TAG7");
    // Локатор поля "Введите номер заказа"
    private final By inputTrack = By.xpath(".//input[contains(@placeholder,'Введите')]");
    // Локатор кнопки "Go!"
    private final By goButton = By.xpath(".//button[contains(@class,'Header_Button__28dPO')]");
    // Локатор картинки "Номер заказа не найден"
    public By trackImgNotFound = By.xpath(".//img[@src='/assets/not-found.png']");

    // Локатор заголовка раскрывающегося списка
    private final By accordionHeader = By.xpath(".//div[starts-with(@id, 'accordion__heading-')]");
    // Локатор текста внутри раскрывающегося списка
    private final By accordionText = By.xpath(".//div[starts-with(@id, 'accordion__panel-')]");

    // Локатор картинки самоката (для ожидания загрузки)
    private static final By scooterImage = By.xpath(".//img[@src='/assets/blueprint.png']");

    // Конструктор класса MainPage
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для ожидания загрузки
    public static void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(scooterImage));
    }
    // Метод клика по кнопке "Принять куки"
    public static void clickOnAcceptCookieButton() {
        driver.findElement(acceptCookieButton).click();
    }

    // Метод клика по кнопке "Заказать" в хэддере
    public void clickOnHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    // Метод клика по кнопке "Статус заказа"
    public void clickOnHeaderOrderStatusButton() {
        driver.findElement(headerOrderStatusLink).click();
    }

    // Метод клика по кнопке "Заказать" в середине страницы
    public void clickOnMiddleOrderButton() {
        driver.findElement(middleOrderButton).click();
    }

    // Метод для ожидания загрузки элемента аккордеона
    public void waitForLoadAccordionText(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElements(accordionText).get(index)));
    }

    // Метод для получения текста заголовка в списке
    public String getAccordionHeaderText(int index) {
        return driver.findElements(accordionHeader).get(index).getText();
    }

    // Метод для получения текста из раскрывающегося списка
    public String getAccordionText(int index) {
        return driver.findElements(accordionText).get(index).getText();
    }

    // Метод клика на заголовок списка
    public void clickAccordionHeader(int index) {
        driver.findElements(accordionHeader).get(index).click();
    }

    //Метод для ввода номера заказа
    public void setInputTrack(String track) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputTrack)).click();
        driver.findElement(inputTrack).sendKeys(track);
    }

    // Метод для клика по кнопке "Go!" c ожиданием
    public void clickOnGoButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(goButton)).click();
    }
    // Метод для ожидания загрузки Картинки
    public void waitForLoadNotFoundPic() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(trackImgNotFound));
    }
    // Метод для клика по логотипу Самока
    public void clickOnLinkScooter() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(headerLogoScooter)).click();
    }

}

