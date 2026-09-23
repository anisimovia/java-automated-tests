package com.aqa.course.ui.pages.alertswindowspages;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class AlertsWindowsPage {

    public AlertsWindowsPage checkOpened() {
        // Хотя бы одна из ссылок раздела видна
        $(byAttribute("href", "/browser-windows")).shouldBe(
                com.codeborne.selenide.Condition.visible
        );
        return this;
    }

    public BrowserWindowsPage openBrowserWindows() {
        $(byAttribute("href", "/browser-windows")).click();
        return new BrowserWindowsPage();
    }

    public FramesPage openFrames() {
        $(byAttribute("href", "/frames")).click();
        return new FramesPage();
    }

    public ModalDialogsPage openModalDialogs() {
        $(byAttribute("href", "/modal-dialogs")).click();
        return new ModalDialogsPage();
    }
}