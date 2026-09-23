package com.aqa.course.ui.pages.elementspages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RadioButtonPage {

    // --- Радиокнопки ---
    private final SelenideElement yesRadio        = $("#yesRadio");
    private final SelenideElement impressiveRadio = $("#impressiveRadio");
    private final SelenideElement noRadio         = $("#noRadio");

    // --- Label'ы — по ним кликаем ---
    private final SelenideElement yesLabel        = $("label[for='yesRadio']");
    private final SelenideElement impressiveLabel = $("label[for='impressiveRadio']");

    // --- Блок результата (реальный HTML: <p class="mt-3">) ---
    private final SelenideElement resultBlock = $("p.mt-3");

    // --- Проверка, что открыта нужная страница ---
    public RadioButtonPage checkOpened() {
        $("h1").shouldHave(text("Radio Button"));
        return this;
    }

    // --- Выбор радиокнопок ---
    public RadioButtonPage selectYes() {
        yesLabel.click();
        return this;
    }

    public RadioButtonPage selectImpressive() {
        impressiveLabel.click();
        return this;
    }

    // --- Проверки состояния ---
    public RadioButtonPage shouldBeSelectedYes() {
        yesRadio.shouldBe(selected);
        return this;
    }

    public RadioButtonPage shouldBeSelectedImpressive() {
        impressiveRadio.shouldBe(selected);
        return this;
    }

    public RadioButtonPage shouldBeDisabledNo() {
        noRadio.shouldBe(disabled);
        return this;
    }

    // --- Проверка блока результата ---
    public RadioButtonPage shouldHaveResult(String expectedValue) {
        resultBlock.shouldBe(visible).shouldHave(text(expectedValue));
        return this;
    }
}