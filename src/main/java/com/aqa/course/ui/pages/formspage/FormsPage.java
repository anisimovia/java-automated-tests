package com.aqa.course.ui.pages.formspage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class FormsPage {

    private final SelenideElement practiceFormLink = $(byAttribute("href", "/automation-practice-form"));

    public FormsPage checkOpened() {
        $("h1").shouldHave(text("Practice Form"));
        return this;
    }

    public PracticeFormPage openPracticeForm() {
        practiceFormLink.click();
        return new PracticeFormPage();
    }
}