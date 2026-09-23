package com.aqa.course.ui.pages.elementspages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ButtonsPage {

    private final SelenideElement doubleClickBtn    = $("#doubleClickBtn");
    private final SelenideElement rightClickBtn     = $("#rightClickBtn");
    // Внимание: id="kFANL" динамический, поэтому ищем кнопку по тексту внутри тега <button>
    private final SelenideElement dynamicClickBtn   = $x("//button[text()='Click Me']");

    private final SelenideElement doubleClickMsg    = $("#doubleClickMessage");
    private final SelenideElement rightClickMsg     = $("#rightClickMessage");
    private final SelenideElement dynamicClickMsg   = $("#dynamicClickMessage");

    public ButtonsPage checkOpened() {
        $("h1").shouldHave(text("Buttons"));
        return this;
    }

    public ButtonsPage doubleClick() {
        doubleClickBtn.doubleClick();
        return this;
    }

    public ButtonsPage rightClick() {
        // Selenide умеет делать контекстный клик через actions()
        actions().contextClick(rightClickBtn).perform();
        return this;
    }

    public ButtonsPage dynamicClick() {
        dynamicClickBtn.click();
        return this;
    }

    // --- Проверки ---

    public ButtonsPage checkDoubleClickMessage() {
        doubleClickMsg.shouldBe(visible)
                .shouldHave(text("You have done a double click"));
        return this;
    }

    public ButtonsPage checkRightClickMessage() {
        rightClickMsg.shouldBe(visible)
                .shouldHave(text("You have done a right click"));
        return this;
    }

    public ButtonsPage checkDynamicClickMessage() {
        dynamicClickMsg.shouldBe(visible)
                .shouldHave(text("You have done a dynamic click"));
        return this;
    }
}