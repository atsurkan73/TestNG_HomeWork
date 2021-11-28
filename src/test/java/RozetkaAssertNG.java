import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;

public class RozetkaAssertNG {
    WebDriver driver;
    WebDriverWait wait;

    By inputSearchXpath = By.xpath("//input[@name='search']");
    By searchBtnXpath = By.xpath("//button[contains (@class,'button_size_medium search')]");
    By titleOfFirstItemXpath = By.xpath("//span[@class='goods-tile__title']");


    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/Resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void FirstTest() {
        driver.manage().window().maximize();

        driver.get("https://rozetka.com.ua/");

        WebElement inputSearch = driver.findElement(inputSearchXpath);

        String sendKeysText = "Mac";

        inputSearch.sendKeys(sendKeysText); //ввести в поиск

        WebElement searchBtn = driver.findElement(searchBtnXpath); //passed

        searchBtn.click();

        wait.until(visibilityOfElementLocated(titleOfFirstItemXpath));

        WebElement titleOfFirstItem = driver.findElement(titleOfFirstItemXpath);

        String titleFirstItemText = titleOfFirstItem.getText();

        String titleExpected = "Бокал для шампанского Chef&Sommelier 300 мл серия Macaron (L9348)";

        assertEquals(titleFirstItemText, titleExpected);

    }

    @AfterTest
    public void after() {
        driver.quit();
    }
}





