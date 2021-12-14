import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Homework19_TestRozetkaAddGoodsToBag {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void before() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/test/Resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");
    }

    @Test
    public void checkPuttingProductToBag() {

        //enter one number of Laptop product from the list (1...60) to test
        puttingOneProductToBag(1);
    }

    public void puttingOneProductToBag(int numOfProduct) {
        System.out.println("Number of Laptop product: " + numOfProduct);

        wait.until(visibilityOfElementLocated(By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]")));
        WebElement laptopAndCompCategory = driver.findElement(By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]"));
        laptopAndCompCategory.click();

        wait.until(elementToBeClickable(By.xpath("//a[@title='Ноутбуки']")));
        WebElement laptopCategory = driver.findElement(By.xpath("//a[@title='Ноутбуки']"));
        laptopCategory.click();

        wait.until(visibilityOfElementLocated(By.xpath("//span[@class='goods-tile__title']")));
        List<WebElement> titleOfProducts = driver.findElements(By.xpath("//span[@class='goods-tile__title']"));
        List<WebElement> priceOfProducts = driver.findElements(By.xpath("//span[@class=\"goods-tile__price-value\"]"));

        WebElement shoppingBagButtonOfFirstProduct = driver.findElement(By.xpath("//li[contains(@class,'catalog-grid')][" + numOfProduct + "]//button[contains(@class,'buy-button')]"));

        shoppingBagButtonOfFirstProduct.click();

        wait.until(elementToBeClickable(titleOfProducts.get(numOfProduct - 1)));
        String titleOfProductsText = titleOfProducts.get(numOfProduct - 1).getText().trim();
        wait.until(elementToBeClickable(priceOfProducts.get(numOfProduct - 1)));
        String priceOfProductsText = priceOfProducts.get(numOfProduct - 1).getText().replaceAll(" ", "");

        wait.until(visibilityOfElementLocated(By.xpath("//span[@class='counter counter--green ng-star-inserted']")));
        WebElement counterOfShoppingBag = driver.findElement(By.xpath("//span[@class='counter counter--green ng-star-inserted']"));

        String counterOfShoppingBagValue = counterOfShoppingBag.getText();

        Assert.assertEquals(counterOfShoppingBagValue, "1");
        System.out.println("One product is in the Bag");

        WebElement shoppingBagPage = driver.findElement(By.xpath("//*[@href='#icon-header-basket']/../.."));

        wait.until(elementToBeClickable(shoppingBagPage));

        shoppingBagPage.click();

        wait.until(visibilityOfElementLocated(By.xpath("//a[@class=\"cart-product__title\"]")));
        WebElement titleInShoppingBagPage = driver.findElement(By.xpath("//a[@class=\"cart-product__title\"]"));

        wait.until(visibilityOfElementLocated(By.xpath("//div[@class='cart-receipt ng-star-inserted']/div")));
        WebElement priceInShoppingBagPage = driver.findElement(By.xpath("//div[@class='cart-receipt ng-star-inserted']/div"));

        String titleInShoppingBagPageText = titleInShoppingBagPage.getText().trim();
        String priceInShoppingBagPageText = priceInShoppingBagPage.getText().replaceAll(" ", "");
        priceInShoppingBagPageText = priceInShoppingBagPageText.substring(0, priceInShoppingBagPageText.length() - 1);

        Assert.assertEquals(titleOfProductsText, titleInShoppingBagPageText);
        System.out.println("titleOfProductsText matches titleInShoppingBagPageText : " + titleInShoppingBagPageText);

        Assert.assertEquals(priceOfProductsText, priceInShoppingBagPageText);
        System.out.println("priceOfProductsText matches priceInShoppingBagPageText : " + priceInShoppingBagPageText);
    }

    @AfterTest
    public void after() {
        driver.quit();
    }
}






