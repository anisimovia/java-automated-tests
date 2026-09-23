package com.aqa.course.ui.pages;

import com.aqa.course.ui.pages.alertswindowspages.AlertsWindowsPage;
import com.aqa.course.ui.pages.elementspages.ElementsPage;
import com.aqa.course.ui.pages.formspage.FormsPage;
import com.aqa.course.ui.pages.interactionspage.InteractionsPage;
import com.aqa.course.ui.pages.widgetspage.WidgetsPage;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    public ElementsPage openElements() {
        open("");
        $(byAttribute("href", "/elements")).click();
        return new ElementsPage();
    }

    public FormsPage openForms() {
        open("/");
        $(byAttribute("href", "/forms")).click();
        return new FormsPage();
    }

    public AlertsWindowsPage openAlertsWindows() {
        open("/");
        $(byAttribute("href", "/alertsWindows")).click();
        return new AlertsWindowsPage();
    }

    public WidgetsPage openWidgets() {
        open("/");
        $(byAttribute("href", "/widgets")).click();
        return new WidgetsPage();
    }

    public InteractionsPage openInteractions() {
        open("/");
        $(byAttribute("href", "/interaction")).click();
        return new InteractionsPage();
    }
}