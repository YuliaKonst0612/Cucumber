package data;

import com.codeborne.selenide.SelenideElement;

public class ItemData {

    private SelenideElement titleElement;
    public ItemData(SelenideElement titleElement) {
        this.titleElement = titleElement;
    }
    public SelenideElement getTitleElement() {
        return titleElement;
    }
}
