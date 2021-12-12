import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class Homework18_TestRozetkaCheckSaleProductProverkaDlyaLehi {
    WebDriver driver;
    WebDriverWait wait;
    private final String MAIN_URL = "https://rozetka.com.ua/";

    By saleBlockGoodsXpath = By.xpath("//h2[contains(text(), ' Акционные предложения ')]/../ul/li");
    By priceOfGoodsInSaleBlockXpath = By.xpath("//h2[contains(text(), ' Акционные предложения ')]/../ul/li//preceding-sibling::span[@class='tile__price-value']");
    By priceOfGoodOnProductPageXpath = By.xpath("//p[contains(@class, 'product-prices__big')]");


    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/Resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.get(MAIN_URL);
        driver.manage().window().maximize();

    }

    @Test
    public void FirstTest() {
        int numOfElementsInBlock = 6;
        for (int i = 0; i < numOfElementsInBlock; i++) {
            proverkaDlyaLehi(i);
        }
    }

    public void proverkaDlyaLehi(int numOfElement) {
        wait.until(presenceOfAllElementsLocatedBy(saleBlockGoodsXpath));
        wait.until(presenceOfAllElementsLocatedBy(priceOfGoodsInSaleBlockXpath));

        List<WebElement> saleBlockGoods = driver.findElements(saleBlockGoodsXpath);
        List<WebElement> priceOfGoodsInSaleBlock = driver.findElements(priceOfGoodsInSaleBlockXpath);

        int saleBlockGoodsCount = saleBlockGoods.size();

        Assert.assertEquals(saleBlockGoodsCount, 6);

        wait.until(elementToBeClickable(saleBlockGoodsXpath));

        String priceOfGoodsInSaleBlockText = priceOfGoodsInSaleBlock.get(numOfElement).getText().trim();

        saleBlockGoods.get(numOfElement).click();

        wait.until(presenceOfElementLocated(priceOfGoodOnProductPageXpath));

        WebElement priceOfGoodOnProductPage = driver.findElement(priceOfGoodOnProductPageXpath);

        String priceOfGoodOnProductPageText = priceOfGoodOnProductPage.getText().trim();
        priceOfGoodOnProductPageText = priceOfGoodOnProductPageText.substring(0, priceOfGoodOnProductPageText.length() - 1);

        System.out.println("========================================");
        System.out.println("priceOfGoodOnProductPageText [" + numOfElement + "] " + priceOfGoodOnProductPageText);
        System.out.println("priceOfGoodInSaleBlockText [" + numOfElement + "] " + priceOfGoodsInSaleBlockText);

        Assert.assertEquals(priceOfGoodOnProductPageText, priceOfGoodsInSaleBlockText);

        driver.navigate().back();
        driver.navigate().refresh();

        String url = driver.getCurrentUrl();

        Assert.assertEquals(url, MAIN_URL);

        Assert.assertEquals(saleBlockGoodsCount, 6);

    }

    @AfterTest
    public void after() {
        driver.quit();
    }
}
