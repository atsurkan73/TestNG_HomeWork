import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPageElements {

    ElementsCollection titles = $$(By.xpath("//span[@class='goods-tile__title']"));
    ElementsCollection prices = $$(By.xpath("//li[contains(@class, 'catalog-grid__cell')]//span[@class='goods-tile__price-value']"));
    SelenideElement cartBtn = $(By.xpath("//*[@href='#icon-header-basket']/../.."));
    SelenideElement addToCartBtn = $(By.xpath("//li[@class='catalog-grid__cell catalog-grid__cell_type_slim ng-star-inserted'][1]//button[@class='buy-button goods-tile__buy-button ng-star-inserted']"));
    SelenideElement counterOfCartProducts = $(By.xpath("//span[@class='counter counter--green ng-star-inserted']"));






}
