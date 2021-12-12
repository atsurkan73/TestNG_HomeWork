import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;

public class Homework19_TestRozetkaAddGoodsToBag {
    WebDriver driver;
    WebDriverWait wait;
    FileWriter fileWriter;

    @BeforeMethod
    public void before() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/test/Resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");
        fileWriter = new FileWriter("src/test/rozetkaTest.txt");
    }

    @Test
    public void FirstTest() throws IOException {

        wait.until(visibilityOfElementLocated(By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]")));
        WebElement laptopAndCompCategory = driver.findElement(By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]"));
        laptopAndCompCategory.click();

        wait.until(elementToBeClickable(By.xpath("//a[@title='Ноутбуки']")));
        WebElement laptopCategory = driver.findElement(By.xpath("//a[@title='Ноутбуки']"));
        laptopCategory.click();

        wait.until(visibilityOfElementLocated(By.xpath("//span[@class='goods-tile__title']")));
        List<WebElement> titleOfGoogs = driver.findElements(By.xpath("//span[@class='goods-tile__title']")); // List of titleOfGoogs entries
        List<WebElement> priceOfGoogs = driver.findElements(By.xpath("//span[@class=\"goods-tile__price-value\"]")); // List of priceOfGoogs entries

        int countOfGoods = titleOfGoogs.size();

        assertEquals(countOfGoods, 60);

//        Map<String, String> mapTitleAndPriceOfGoogs = new LinkedHashMap<>();               // create map
//        int index = 0;
//
//        for (WebElement element : titleOfGoogs) {
//            String elementText = element.getText();
//            System.out.println(elementText);                                               // print titleOfGoogs list on the screen
//            mapTitleAndPriceOfGoogs.put(titleOfGoogs.get(index).getText(), priceOfGoogs.get(index++).getText()); // fill out the map
//        }
//
//        for (Map.Entry<String, String> entry : mapTitleAndPriceOfGoogs.entrySet())
//            fileWriter.write(entry.getKey() + " - " + entry.getValue() + '\n');        // write titleOfGoogs entries to file

    }

    @AfterTest
    public void after() throws IOException {
        driver.quit();
        fileWriter.close();
    }
}






