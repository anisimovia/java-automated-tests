package com.aqa.course.ui.pages.widgetspage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class TabsPage {

    // Кнопки табов
    private final SelenideElement whatTab   = $("#demo-tab-what");
    private final SelenideElement originTab = $("#demo-tab-origin");
    private final SelenideElement useTab    = $("#demo-tab-use");
    private final SelenideElement moreTab   = $("#demo-tab-more");

    // Панели с содержимым
    private final SelenideElement whatPanel   = $("#demo-tabpane-what");
    private final SelenideElement originPanel = $("#demo-tabpane-origin");
    private final SelenideElement usePanel    = $("#demo-tabpane-use");

    public TabsPage checkOpened() {
        $("h1").shouldHave(text("Tabs"));
        return this;
    }

    // --- Клики по табам ---

    public TabsPage clickWhat() {
        whatTab.click();
        return this;
    }

    public TabsPage clickOrigin() {
        originTab.click();
        return this;
    }

    public TabsPage clickUse() {
        useTab.click();
        return this;
    }

    // --- Проверки активного таба ---

    public TabsPage shouldHaveWhatTabActive() {
        whatTab.shouldHave(attribute("aria-selected", "true"));
        originTab.shouldHave(attribute("aria-selected", "false"));
        useTab.shouldHave(attribute("aria-selected", "false"));
        return this;
    }

    public TabsPage shouldHaveOriginTabActive() {
        originTab.shouldHave(attribute("aria-selected", "true"));
        whatTab.shouldHave(attribute("aria-selected", "false"));
        useTab.shouldHave(attribute("aria-selected", "false"));
        return this;
    }

    public TabsPage shouldHaveUseTabActive() {
        useTab.shouldHave(attribute("aria-selected", "true"));
        whatTab.shouldHave(attribute("aria-selected", "false"));
        originTab.shouldHave(attribute("aria-selected", "false"));
        return this;
    }

    // --- Проверки видимости панелей ---

    public TabsPage shouldHaveWhatPanelVisible() {
        whatPanel.shouldHave(cssClass("active"));
        originPanel.shouldNotHave(cssClass("active"));
        usePanel.shouldNotHave(cssClass("active"));
        return this;
    }

    public TabsPage shouldHaveOriginPanelVisible() {
        originPanel.shouldHave(cssClass("active"));
        whatPanel.shouldNotHave(cssClass("active"));
        usePanel.shouldNotHave(cssClass("active"));
        return this;
    }

    public TabsPage shouldHaveUsePanelVisible() {
        usePanel.shouldHave(cssClass("active"));
        whatPanel.shouldNotHave(cssClass("active"));
        originPanel.shouldNotHave(cssClass("active"));
        return this;
    }

    // --- Проверка содержимого ---

    public TabsPage shouldHaveWhatText(String expected) {
        whatPanel.shouldHave(text(expected));
        return this;
    }

    public TabsPage shouldHaveOriginText(String expected) {
        originPanel.shouldHave(text(expected));
        return this;
    }

    public TabsPage shouldHaveUseText(String expected) {
        usePanel.shouldHave(text(expected));
        return this;
    }

    // --- Проверка disabled таба ---

    public TabsPage shouldHaveMoreTabDisabled() {
        moreTab.shouldHave(attribute("disabled"));
        return this;
    }
}