import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CartPageElements {
    SelenideElement titleOfProductOnCartPage = $(By.xpath("//div[@class='cart-product__main']"));
    SelenideElement priceOfProductOnCartPage = $(By.xpath("//div[@class='cart-receipt ng-star-inserted']/div"));
}
