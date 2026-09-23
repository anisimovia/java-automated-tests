package com.aqa.course.ui.pages.interactionspage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SelectablePage {

    // Табы
    private final SelenideElement listTab = $("#demo-tab-list");
    private final SelenideElement gridTab = $("#demo-tab-grid");

    // List-панель и её элементы
    private final SelenideElement listPanel = $("#demo-tabpane-list");

    // Grid-панель
    private final SelenideElement gridPanel = $("#demo-tabpane-grid");

    public SelectablePage checkOpened() {
        $("h1").shouldHave(text("Selectable"));
        return this;
    }

    // --- Переключение режимов ---

    public SelectablePage clickListTab() {
        listTab.click();
        return this;
    }

    public SelectablePage clickGridTab() {
        gridTab.click();
        return this;
    }

    // --- Работа с List ---

    /** Выбирает элемент списка по тексту (например, "Cras justo odio"). */
    public SelectablePage selectListItem(String itemText) {
        listPanel.$$("li").findBy(text(itemText)).click();
        return this;
    }

    /** Проверяет, что элемент списка выделен (имеет класс active). */
    public SelectablePage shouldHaveListItemSelected(String itemText) {
        listPanel.$$("li").findBy(text(itemText)).shouldHave(cssClass("active"));
        return this;
    }

    /** Проверяет, что элемент списка НЕ выделен. */
    public SelectablePage shouldHaveListItemNotSelected(String itemText) {
        listPanel.$$("li").findBy(text(itemText)).shouldNotHave(cssClass("active"));
        return this;
    }

    // --- Работа с Grid ---

    /** Выбирает элемент сетки по тексту (например, "Five"). */
    public SelectablePage selectGridItem(String itemText) {
        gridPanel.$$("li").findBy(text(itemText)).click();
        return this;
    }

    /** Проверяет, что элемент сетки выделен. */
    public SelectablePage shouldHaveGridItemSelected(String itemText) {
        gridPanel.$$("li").findBy(text(itemText)).shouldHave(cssClass("active"));
        return this;
    }

    /** Проверяет, что элемент сетки НЕ выделен. */
    public SelectablePage shouldHaveGridItemNotSelected(String itemText) {
        gridPanel.$$("li").findBy(text(itemText)).shouldNotHave(cssClass("active"));
        return this;
    }

    // --- Проверки видимости панелей ---

    public SelectablePage shouldHaveListPanelVisible() {
        listPanel.shouldHave(cssClass("active"));
        gridPanel.shouldNotHave(cssClass("active"));
        return this;
    }

    public SelectablePage shouldHaveGridPanelVisible() {
        gridPanel.shouldHave(cssClass("active"));
        listPanel.shouldNotHave(cssClass("active"));
        return this;
    }

    // --- Проверки активного таба ---

    public SelectablePage shouldHaveListTabActive() {
        listTab.shouldHave(attribute("aria-selected", "true"));
        gridTab.shouldHave(attribute("aria-selected", "false"));
        return this;
    }

    public SelectablePage shouldHaveGridTabActive() {
        gridTab.shouldHave(attribute("aria-selected", "true"));
        listTab.shouldHave(attribute("aria-selected", "false"));
        return this;
    }
}