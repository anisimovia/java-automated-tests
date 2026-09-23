package com.aqa.course.ui.pages.interactionspage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class InteractionsPage {

    private final SelenideElement sortableLink   = $(byAttribute("href", "/sortable"));
    private final SelenideElement selectableLink = $(byAttribute("href", "/selectable"));
    private final SelenideElement resizableLink = $(byAttribute("href", "/resizable"));

    public InteractionsPage checkOpened() {
        // Раздел открыт, если видна хотя бы одна ссылка из него
        selectableLink.shouldBe(Condition.visible);
        return this;
    }

    public SelectablePage openSelectable() {
        selectableLink.click();
        return new SelectablePage();
    }

    public ResizablePage openResizable() {
        resizableLink.click();
        return new ResizablePage();
    }}