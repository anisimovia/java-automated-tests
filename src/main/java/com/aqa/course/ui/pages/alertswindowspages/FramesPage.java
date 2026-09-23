package com.aqa.course.ui.pages.alertswindowspages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class FramesPage {

    private final SelenideElement frame1 = $("#frame1");
    private final SelenideElement frame2 = $("#frame2");

    public FramesPage checkOpened() {
        $("h1").shouldHave(text("Frames"));
        return this;
    }

    /** Переключается в iframe #frame1, проверяет содержимое. */
    public FramesPage checkFrame1Content() {
        switchTo().frame(frame1);
        $("h1").shouldHave(text("This is a sample page"));
        switchTo().defaultContent();   // вернуться в основную страницу
        return this;
    }

    /** Переключается в iframe #frame2, проверяет содержимое. */
    public FramesPage checkFrame2Content() {
        switchTo().frame(frame2);
        $("h1").shouldHave(text("This is a sample page"));
        switchTo().defaultContent();
        return this;
    }
}