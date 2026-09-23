package com.aqa.course.ui.pages.alertswindowspages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.switchTo;

public class BrowserWindowsPage {

    private final SelenideElement newTabButton       = $("#tabButton");
    private final SelenideElement newWindowButton    = $("#windowButton");
    private final SelenideElement newWindowMsgButton = $("#messageWindowButton");

    public BrowserWindowsPage checkOpened() {
        $("h1").shouldHave(text("Browser Windows"));
        return this;
    }

    public BrowserWindowsPage clickNewTab() {
        newTabButton.click();
        return this;
    }

    public BrowserWindowsPage clickNewWindow() {
        int before = WebDriverRunner.getWebDriver().getWindowHandles().size();
        newWindowButton.click();
        Wait().until(wd -> wd.getWindowHandles().size() > before);
        return this;
    }

    public BrowserWindowsPage clickNewWindowMessage() {
        int before = WebDriverRunner.getWebDriver().getWindowHandles().size();
        newWindowMsgButton.click();
        Wait().until(wd -> wd.getWindowHandles().size() > before);
        return this;
    }

    public SamplePage switchToNewWindow() {
        Wait().until(wd -> wd.getWindowHandles().size() >= 2);
        int lastIndex = WebDriverRunner.getWebDriver().getWindowHandles().size() - 1;
        switchTo().window(lastIndex);
        return new SamplePage();
    }
}