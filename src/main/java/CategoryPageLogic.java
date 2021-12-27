import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class CategoryPageLogic extends CategoryPageElements {
    public SearchPageLogic clickOnCategory(SelenideElement category) {
        category.shouldBe(Condition.visible).click();
        return Selenide.page(SearchPageLogic.class);
    }
}
