package com.aqa.course.ui.pages.widgetspage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProgressBarPage {

    private final SelenideElement startStopBtn  = $("#startStopButton");
    private final SelenideElement progressBar   = $(".progress-bar");

    public ProgressBarPage checkOpened() {
        $("h1").shouldHave(text("Progress Bar"));
        return this;
    }

    // --- Управление ---

    public ProgressBarPage start() {
        startStopBtn.shouldHave(text("Start")).click();
        return this;
    }

    public ProgressBarPage stop() {
        startStopBtn.shouldHave(text("Stop")).click();
        return this;
    }

    public ProgressBarPage clickStartStop() {
        startStopBtn.click();
        return this;
    }

    // --- Чтение значения ---

    public int getCurrentValue() {
        return Integer.parseInt(progressBar.getAttribute("aria-valuenow"));
    }

    // --- Проверки ---

    public ProgressBarPage shouldHaveValue(int expected) {
        progressBar.shouldHave(
                com.codeborne.selenide.Condition.attribute("aria-valuenow", String.valueOf(expected))
        );
        return this;
    }

    public ProgressBarPage shouldBeAtZero() {
        return shouldHaveValue(0);
    }

    public ProgressBarPage shouldBeAtHundred() {
        return shouldHaveValue(100);
    }
}