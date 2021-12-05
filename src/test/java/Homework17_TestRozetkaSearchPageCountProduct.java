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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertEquals;

public class Homework17_TestRozetkaSearchPageCountProduct {
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
        List<WebElement> titleOfGoogs = driver.findElements(By.xpath("//span[@class='goods-tile__title']"));
        int countOfGoods = titleOfGoogs.size();

        assertEquals(countOfGoods, 60);

        Map<Integer, String> mapTitleOfGoogs = new HashMap<>();               // create map
        Integer index = 0;

        for (WebElement element : titleOfGoogs) {
            String elementText = element.getText();
            System.out.println(elementText);
            mapTitleOfGoogs.put(index, titleOfGoogs.get(index++).getText()); // fill out the map
        }

        for (Map.Entry<Integer, String> entry : mapTitleOfGoogs.entrySet())
            fileWriter.write(entry.getValue() + '\n');                   // write titleOfGoogs entries to file
        fileWriter.close();
    }

    @AfterTest
    public void after() {
        driver.quit();
    }
}






