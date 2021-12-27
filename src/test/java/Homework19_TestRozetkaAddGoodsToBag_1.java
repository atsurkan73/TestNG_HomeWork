import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Homework19_TestRozetkaAddGoodsToBag_1 {
    WebDriver driver;
    WebDriverWait wait;
    int numOfProduct;

    //MainPage
    By laptopAndCompCategory = By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]");
    //laptopAndCompCategory
    By laptopCategory = By.xpath("//a[@title='Ноутбуки']");

    //laptopCategoryPagePage
    By titleOfProductOnCategoryXpath = By.xpath("//span[@class='goods-tile__title']");
    By priceOfProductOnCategoryXpath = By.xpath("//li[contains(@class, 'catalog-grid__cell')][1]//span[@class='goods-tile__price-value']");
//    By addToShoppingBagXpath = By.xpath(//button[@class='buy-button goods-tile__buy-button buy-button_state_in-cart ng-star-inserted']);

    //Product page
    By buyButton = By.xpath("//button[contains(@class,'buy-button ')]");
    By titleOfProductOnProductXpath = By.xpath("//h1[@class='product__title']");
    By priceOfProductOnProductXpath = By.xpath("//div[@class='product-prices__inner ng-star-inserted']");
    By shoppingBagButtonXpath = By.xpath("//li[contains(@class,'catalog-grid')][1]//button[contains(@class,'buy-button')]");

    //ShoppingBagPage
    By titleOfProductOnShopingBagXpath = By.xpath("//div[@class='cart-product__main']");
    By priceOnShoppingBagPageXpath = By.xpath("//div[@class='cart-receipt ng-star-inserted']/div");
    By priceOnShoppingBagPage1Xpath = By.xpath("//p[@class='cart-product__price cart-product__price--red']");

    //ShoppingBagButton
    By shoppingBagBtnXpath = By.xpath("//*[@href='#icon-header-basket']/../..");
    By counterOfShoppingBagXpath = By.xpath("//span[@class='counter counter--green ng-star-inserted']");


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
        clickElement(laptopAndCompCategory);
        clickElement(laptopCategory);

        String titleOfProductOnCategory = getTextFromList(titleOfProductOnCategoryXpath);
        System.out.println("titleOfProductsOnCategory: " + titleOfProductOnCategory);
        String priceOfProducts = getTextFromList(priceOfProductOnCategoryXpath);

        clickElement(shoppingBagButtonXpath);

        String counterOfShoppingBag = getTextFromList(counterOfShoppingBagXpath);
        System.out.println("counterOfShoppingBag: " + counterOfShoppingBag);
        Assert.assertEquals(counterOfShoppingBag, "1");

        clickElement(shoppingBagButtonXpath);

        String titleOfProductOnShoppingBag = getTextFormElement(titleOfProductOnShopingBagXpath);
        String priceOfProductOnShoppingBag = getTextFormElement(priceOnShoppingBagPageXpath);
        System.out.println("titleOfProductOnShoppingBag: " + titleOfProductOnShoppingBag);
        System.out.println("priceOfProductOnShoppingBag: " + priceOfProductOnShoppingBag);

        Assert.assertEquals(titleOfProductOnCategory, titleOfProductOnShoppingBag);
        System.out.println("titleOfProductsText matches titleInShoppingBagPageText : " + titleOfProductOnShoppingBag);

        Assert.assertEquals(priceOfProducts, priceOfProductOnShoppingBag);
        System.out.println("priceOfProductsText matches priceInShoppingBagPageText : " + priceOfProductOnShoppingBag);

    }

    public void clickElement(By xpath) {
        wait.until(visibilityOfElementLocated(xpath));
        WebElement element = driver.findElement(xpath);
        element.click();
    }

    public String getTextFromList(By xpath) {
        wait.until(visibilityOfElementLocated(xpath));
        List<WebElement> listOfElements = driver.findElements(xpath);
        String textOfElement = listOfElements.get(0).getText().replaceAll(" ", "");
        return textOfElement;

    }

    public String getTextFormElement(By xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        WebElement textFromElement = driver.findElement(xpath);
        String textOfElement = textFromElement.getText().replaceAll(" ", "").replaceAll("₴", "");
        return textOfElement;
    }

    @AfterTest
    public void after() {
        driver.quit();
    }
}






