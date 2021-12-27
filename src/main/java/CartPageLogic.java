import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class CartPageLogic extends CartPageElements {
    public String titleOfProduct(SelenideElement title) {
        title.shouldBe(Condition.visible);
        return title.text().trim();
    }

    public String priceOfProduct(SelenideElement price) {
        price.shouldBe(Condition.visible);
        return price.text().replaceAll("₴", "").trim();
    }
}
