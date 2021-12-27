import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CategoryPageElements {
    SelenideElement laptopCategory = $(By.xpath("//a[@title='Ноутбуки']"));
}
