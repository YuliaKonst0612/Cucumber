package selenide.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.YandexMarketMainPage;


public class StepsDefinition {
    private final YandexMarketMainPage yandexMarketMainPage = Selenide.page(YandexMarketMainPage.class);

    @Given("На главной странице YandexMarket кликнуть на кнопку {string}")
    public void givenOnMainPage(String catalogButton) {
        yandexMarketMainPage.click(catalogButton);
    }

    @When("Навести курсор на категорию {string}")
    public void hoverOnCategory(String category) {
        yandexMarketMainPage.hover(category);
    }

    @And("Кликнуть на подкатегорию {string}")
    public void clickOnSubCategory(String subCategory) {
        yandexMarketMainPage.clickOnSubCategory(subCategory);
    }

    @And("Выбрать производителя {string}")
    public void selectManufacturer(String manufacturer) {
        yandexMarketMainPage.selectManufacturer(manufacturer);
    }

    @Then("Убедиться, что отображаются только результаты с наименованием {string}")
    public void checkFilter(String model) {
        yandexMarketMainPage.checkFilter(model);
    }
}

