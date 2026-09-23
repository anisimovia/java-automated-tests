package com.aqa.course.ui.pages.elementspages;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class ElementsPage {

    public TextBoxPage openTextBox() {
        $(byAttribute("href", "/text-box")).click();
        return new TextBoxPage();
    }

    public CheckBoxPage openCheckBox() {
        $(byAttribute("href", "/checkbox")).click();
        return new CheckBoxPage();
    }

    public RadioButtonPage openRadioButton() {
        $(byAttribute("href", "/radio-button")).click();
        return new RadioButtonPage();
    }

    public ButtonsPage openButtons() {
        $(byAttribute("href", "/buttons")).click();
        return new ButtonsPage();
    }
}