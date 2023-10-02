package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.ConfigReader;
import data.ItemData;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarketMainPage {
    static String baseUrl = ConfigReader.getProperty("baseUrl");

    /**
     * Метод кликает на кнопку Каталог на главной странице
     * Автор: [Юлия Константинова]
     */
    public YandexMarketMainPage click(String catalogButton) {
        open(baseUrl, YandexMarketMainPage.class);
        $(By.xpath("//button[.//span[text()='" + catalogButton + "']]")).click();
        return page(YandexMarketMainPage.class);

    }

    /**
     * Метод Наводит мышь на раздел 'Электроника'
     * Автор: [Юлия Константинова]
     */
    public YandexMarketMainPage hover(String category) {
        $(By.xpath("//a/span[text()='" + category + "']")).hover();
        return page(YandexMarketMainPage.class);
    }

    /**
     * Метод кликает на подкатегорию 'Смартфоны'
     * Автор: [Юлия Константинова]
     */
    public YandexMarketMainPage clickOnSubCategory(String subcategory) {

      $(By.xpath("//span[text()= ' " + subcategory + "']")).click();
        return page(YandexMarketMainPage.class);
    }

    /**
     * Метод устанавливает фильтр по производителю
     * Автор: [Юлия Константинова]
     */
    public YandexMarketMainPage selectManufacturer(String manufacturer) {

        SelenideElement manufacturerElement = $(By.xpath("//span[text()='" + manufacturer + "']"));
        manufacturerElement.click();
        if (!manufacturerElement.exists()) {
            $(By.xpath("//span[text()='Показать всё']/parent::button[@aria-expanded='false']")).click();
            manufacturerElement.scrollTo().click();

        }
        return page(YandexMarketMainPage.class);
    }


    /**
     * Метод проверяет, что товары в выдаче соответствуют фильтру по модели
     * Автор: [Юлия Константинова]
     */
    public YandexMarketMainPage checkFilter(String model) {
        int currentPage = 1;
        int maxPages = 10;
        boolean allItemsMatchFilter = true;

        do {
            List<ItemData> itemsOnCurrentPage = getItemsOnCurrentPage();

            for (ItemData item : itemsOnCurrentPage) {
                item.getTitleElement().should(exist.because("Товар с фильтром" + model + "не найден"));
            }

            currentPage++;
            goToNextPage();
            allItemsMatchFilter = !itemsOnCurrentPage.isEmpty();

        } while (hasNextPage() && currentPage <= maxPages && allItemsMatchFilter);

        return page(YandexMarketMainPage.class);

    }

    /**
     * Метод Получает список товаров на текущей странице и возвращает их в виде списка
     * Автор: [Юлия Константинова]
     */
    private List<ItemData> getItemsOnCurrentPage() {
        List<ItemData> items = new ArrayList<>();

        ElementsCollection resultSearch = $$x("//div[@data-test-id='virtuoso-item-list']//descendant::*[@data-index]");
        for (SelenideElement element : resultSearch) {
            ItemData item = parseItem(element);
            items.add(item);
        }

        return items;
    }

    /**
     * Метод парсит заголовки
     * Автор: [Юлия Константинова]
     */
    private ItemData parseItem(SelenideElement element) {

        SelenideElement titleElement = $(By.xpath("//h3[@data-auto='snippet-title-header']"));
        return new ItemData(titleElement);
    }

    /**
     * Метод проверяет, что кнопка перехода на следующую страницу активна
     * Автор: [Юлия Константинова]
     */
    private boolean hasNextPage() {
        SelenideElement nextPageButton = $(By.xpath("//div[@data-baobab-name='next']"));
        return nextPageButton.isEnabled();
    }

    /**
     * Метод для перехода на следующую страницу
     * Автор: [Юлия Константинова]
     */
    private void goToNextPage() {
        SelenideElement nextPageButton = $(By.xpath("//div[@data-baobab-name='next']"));

        if (nextPageButton.isEnabled()) {
            nextPageButton.click();
        }
    }
}