package com.aqa.course.ui.pages.elementspages;

import com.aqa.course.data.TextBoxData;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {

    private final SelenideElement userName       = $("#userName");
    private final SelenideElement userEmail      = $("#userEmail");
    private final SelenideElement currentAddress = $("#currentAddress");
    private final SelenideElement permanentAddress = $("#permanentAddress");
    private final SelenideElement submitBtn      = $("#submit");
    private final SelenideElement outputBlock    = $("#output");

    public TextBoxPage checkOpened() {
        $("h1").shouldHave(text("Text Box"));
        return this;
    }

    public TextBoxPage fillForm(TextBoxData data) {
        userName.setValue(data.getFullName());
        userEmail.setValue(data.getEmail());
        currentAddress.setValue(data.getCurrentAddress());
        permanentAddress.setValue(data.getPermanentAddress());
        return this;
    }

    public TextBoxPage submit() {
        submitBtn.click();
        return this;
    }

    public TextBoxPage checkOutput(TextBoxData data) {
        outputBlock.shouldBe(visible);
        $("#output #name").shouldHave(text(data.getFullName()));
        $("#output #email").shouldHave(text(data.getEmail()));
        $("#output #currentAddress").shouldHave(text(data.getCurrentAddress()));
        $("#output #permanentAddress").shouldHave(text(data.getPermanentAddress()));
        return this;
    }
}