package com.aqa.course.ui.pages.widgetspage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AccordianPage {

    // Общие локаторы
    private final SelenideElement firstBody  = $x("(//div[contains(@class,'accordion-body')])[1]");
    private final SelenideElement secondBody = $x("(//div[contains(@class,'accordion-body')])[2]");
    private final SelenideElement thirdBody  = $x("(//div[contains(@class,'accordion-body')])[3]");

    // Кнопки заголовков (по тексту)
    private final SelenideElement firstBtn  = $x("//button[text()='What is Lorem Ipsum?']");
    private final SelenideElement secondBtn = $x("//button[text()='Where does it come from?']");
    private final SelenideElement thirdBtn  = $x("//button[text()='Why do we use it?']");

    public AccordianPage checkOpened() {
        $("h1").shouldHave(text("Accordian"));
        return this;
    }

    // --- Действия ---

    public AccordianPage expandFirst() {
        firstBtn.click();
        firstBody.shouldBe(visible);
        return this;
    }

    public AccordianPage expandSecond() {
        secondBtn.click();
        secondBody.shouldBe(visible);
        return this;
    }

    public AccordianPage expandThird() {
        thirdBtn.click();
        thirdBody.shouldBe(visible);
        return this;
    }

    // --- Проверки содержимого ---

    public AccordianPage checkFirstBodyContains(String expectedText) {
        firstBody.shouldHave(text(expectedText));
        return this;
    }

    public AccordianPage checkSecondBodyContains(String expectedText) {
        secondBody.shouldHave(text(expectedText));
        return this;
    }

    public AccordianPage checkThirdBodyContains(String expectedText) {
        thirdBody.shouldHave(text(expectedText));
        return this;
    }

    // --- Проверка, что блок свёрнут ---

    public AccordianPage checkFirstBodyHidden() {
        firstBody.shouldNotBe(visible);
        return this;
    }

    public AccordianPage checkSecondBodyHidden() {
        secondBody.shouldNotBe(visible);
        return this;
    }

    public AccordianPage checkThirdBodyHidden() {
        thirdBody.shouldNotBe(visible);
        return this;
    }
}