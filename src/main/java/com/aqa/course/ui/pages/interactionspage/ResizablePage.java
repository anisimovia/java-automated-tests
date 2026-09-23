package com.aqa.course.ui.pages.interactionspage;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResizablePage {

    private final SelenideElement restrictedBox = $("#resizableBoxWithRestriction");
    private final SelenideElement freeBox       = $("#resizable");

    // Ручки resize внутри каждого бокса
    private final SelenideElement restrictedHandle =
            $("#resizableBoxWithRestriction .react-resizable-handle");
    private final SelenideElement freeHandle =
            $("#resizable .react-resizable-handle");

    public ResizablePage checkOpened() {
        $("h1").shouldHave(text("Resizable"));
        return this;
    }

    // --- Чтение размеров ---

    public int getRestrictedBoxWidth() {
        return extractPx(restrictedBox.getAttribute("style"), "width");
    }

    public int getRestrictedBoxHeight() {
        return extractPx(restrictedBox.getAttribute("style"), "height");
    }

    public int getFreeBoxWidth() {
        return extractPx(freeBox.getAttribute("style"), "width");
    }

    public int getFreeBoxHeight() {
        return extractPx(freeBox.getAttribute("style"), "height");
    }

    // --- Действия resize ---

    /** Тянет ручку ограниченного бокса на dx/dy пикселей. */
    public ResizablePage resizeRestrictedBox(int dx, int dy) {
        dragHandle(restrictedHandle, dx, dy);
        return this;
    }

    /** Тянет ручку свободного бокса на dx/dy пикселей. */
    public ResizablePage resizeFreeBox(int dx, int dy) {
        dragHandle(freeHandle, dx, dy);
        return this;
    }

    // --- Проверки ---

    public ResizablePage shouldHaveRestrictedBoxSize(int w, int h) {
        // Проверяем через attribute — ждёт, пока значение не совпадёт
        restrictedBox.shouldHave(com.codeborne.selenide.Condition.attributeMatching(
                "style", ".*width:\\s*" + w + "px.*"));
        restrictedBox.shouldHave(com.codeborne.selenide.Condition.attributeMatching(
                "style", ".*height:\\s*" + h + "px.*"));
        return this;
    }

    public ResizablePage shouldHaveFreeBoxSize(int w, int h) {
        freeBox.shouldHave(com.codeborne.selenide.Condition.attributeMatching(
                "style", ".*width:\\s*" + w + "px.*"));
        freeBox.shouldHave(com.codeborne.selenide.Condition.attributeMatching(
                "style", ".*height:\\s*" + h + "px.*"));
        return this;
    }

    // --- Вспомогательные ---

    private void dragHandle(SelenideElement handle, int dx, int dy) {
        handle.scrollTo();
        new Actions(WebDriverRunner.getWebDriver())
                .clickAndHold(handle)
                .moveByOffset(dx, dy)
                .release()
                .perform();
        // Небольшая пауза, чтобы React успел применить resize
        com.codeborne.selenide.Selenide.sleep(200);
    }

    /** Извлекает число из style="...width: 200px;...". */
    private int extractPx(String style, String property) {
        if (style == null) return -1;
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile(property + ":\\s*(\\d+)px")
                .matcher(style);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return -1;
    }
}