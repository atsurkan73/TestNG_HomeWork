/*Открыть https://rozetka.com.ua/
        Перейти в раздел «Компьютеры и ноутбуки»
        Перейти в раздел « Ноутбуки»
        Добавить первый товар в корзину
        Проверить что в корзину добавился один товар
        Перейти в корзину и проверить, что добавился правильный товар

*/

import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class Homework20_CheckingAddToCartOnSelenide_PageObject {
    private final CartPageLogic cartPageLogic = new CartPageLogic();
    private final SearchPageLogic searchPageLogic = new SearchPageLogic();

    @BeforeMethod
    public void before() {
        Configuration.startMaximized = true;
        open("https://rozetka.com.ua");
    }

    @Test
    public void firstTest() {
        new MainPageLogic().clickOnCategory(new MainPageLogic().categorySideBar)
                .clickOnCategory(new CategoryPageElements().laptopCategory);

        int indexOfProduct = 0;
        String titleOfProductOnSearchPage = searchPageLogic.titleOfProduct(searchPageLogic.titles, indexOfProduct);
        String priceOfProductOnSearchPage = searchPageLogic.priceOfProduct(searchPageLogic.prices, indexOfProduct);

        searchPageLogic.addProductToCart(searchPageLogic.addToCartBtn);

        String cartCounter = searchPageLogic.getCartCounterText(searchPageLogic.counterOfCartProducts);
        Assert.assertEquals(cartCounter, "1");

        searchPageLogic.clickOnCartBtn(searchPageLogic.cartBtn);

        String titleOfProductOnCart = cartPageLogic.titleOfProduct(cartPageLogic.titleOfProductOnCartPage);
        String priceOfProductOnCart = cartPageLogic.priceOfProduct(cartPageLogic.priceOfProductOnCartPage);

        Assert.assertEquals(titleOfProductOnCart, titleOfProductOnSearchPage);
        Assert.assertEquals(priceOfProductOnCart, priceOfProductOnSearchPage);
    }
}
