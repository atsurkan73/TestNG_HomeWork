import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class MainPageLogic extends MainPageElements {
    public CategoryPageLogic clickOnCategory(ElementsCollection category) {
        category.get(0).shouldBe(Condition.visible);
        category.get(0).click();
        return Selenide.page(CategoryPageLogic.class);
    }
}
