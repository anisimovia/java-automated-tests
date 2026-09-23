package com.aqa.course.ui.pages.alertswindowspages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class SamplePage {

    public SamplePage checkOpened() {
        webdriver().shouldHave(urlContaining("/sample"));
        $("h1").shouldHave(text("This is a sample page"));
        return this;
    }

    public BrowserWindowsPage switchBack() {
        switchTo().window(0);   // вернуться на исходную вкладку
        return new BrowserWindowsPage();
    }
}