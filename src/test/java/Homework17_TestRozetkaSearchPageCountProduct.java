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
    FileWriter fileWriter1;

    @BeforeMethod
    public void before() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/test/Resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");
        fileWriter = new FileWriter("src/test/rozetkaTest.txt");
        fileWriter1 = new FileWriter("src/test/rozetkaTest1.txt");
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

        for (WebElement element : titleOfGoogs) {
            System.out.println(element.getText());
        }

        Map<WebElement, String> mapTitleOfGoogs = new HashMap<>(); // create map with key = WebElement
        for (WebElement element : titleOfGoogs) {
            mapTitleOfGoogs.put(element, element.getText());
        }

        for (Map.Entry<WebElement, String> entry : mapTitleOfGoogs.entrySet())
            fileWriter.write(entry.getValue() + '\n');
        fileWriter.close();

        Map<Integer, String> mapTitleOfGoogs1 = new HashMap<>();  // create map1 with key = index
        Integer index = 1;
        for (int i = 0; i < titleOfGoogs.size(); i++) {
            mapTitleOfGoogs1.put(index++, titleOfGoogs.get(i).getText());
        }

        for (Map.Entry<Integer, String> entry : mapTitleOfGoogs1.entrySet())
            fileWriter1.write(entry.getKey() + " " + entry.getValue() + '\n');
        fileWriter1.close();
    }

    @AfterTest
    public void after() {
        driver.quit();
    }
}






