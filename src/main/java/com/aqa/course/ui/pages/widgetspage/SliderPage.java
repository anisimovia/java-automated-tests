package com.aqa.course.ui.pages.widgetspage;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class SliderPage {

    private final SelenideElement slider       = $("#slider");
    private final SelenideElement sliderValue  = $("#sliderValue");
    private final SelenideElement tooltipLabel = $(".range-slider__tooltip__label");

    public SliderPage checkOpened() {
        $("h1").shouldHave(text("Slider"));
        return this;
    }

    // --- Чтение значения ---

    public int getSliderValue() {
        // Значение хранится в атрибуте value
        return Integer.parseInt(slider.getAttribute("value"));
    }

    public int getDisplayedValue() {
        // Значение в поле #sliderValue
        return Integer.parseInt(sliderValue.getAttribute("value"));
    }

    // --- Установка значения ---

    /** Устанавливает значение слайдера через стрелки клавиатуры.
     *  targetValue — желаемое значение от 0 до 100. */
    public SliderPage setSliderValue(int targetValue) {
        if (targetValue < 0 || targetValue > 100) {
            throw new IllegalArgumentException("Значение должно быть от 0 до 100, получено: " + targetValue);
        }

        slider.click();

        int maxAttempts = 200;
        for (int i = 0; i < maxAttempts; i++) {
            int current = getSliderValue();
            if (current == targetValue) {
                return this;
            }

            if (current < targetValue) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            } else {
                slider.sendKeys(Keys.ARROW_LEFT);
            }
        }

        throw new AssertionError(
                "Не удалось установить значение слайдера. Текущее: " + getSliderValue()
                        + ", целевое: " + targetValue
        );
    }

    // --- Проверки ---

    public SliderPage shouldHaveValue(int expected) {
        sliderValue.shouldHave(value(String.valueOf(expected)));
        return this;
    }

}