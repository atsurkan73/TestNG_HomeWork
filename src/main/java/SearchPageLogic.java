import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class SearchPageLogic extends SearchPageElements {

    public String titleOfProduct(ElementsCollection title, int index) {
        return title.get(index).getText();
    }

    public String priceOfProduct(ElementsCollection price, int index) {
        return price.get(index).getText().replaceAll(" ", "").replaceAll("₴", "");
    }

    public SearchPageLogic addProductToCart(SelenideElement addProductToCartBtn) {
        addProductToCartBtn.shouldBe(Condition.visible);
        addProductToCartBtn.click();
        return this;
    }

    public String getCartCounterText(SelenideElement cartCounter) {
        return cartCounter.innerText().trim();
    }

    public CartPageLogic clickOnCartBtn(SelenideElement btn) {
        btn.shouldBe(Condition.visible);
        btn.click();
        return Selenide.page(CartPageLogic.class);
    }
}


