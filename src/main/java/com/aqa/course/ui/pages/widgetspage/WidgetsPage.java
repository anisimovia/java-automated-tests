package com.aqa.course.ui.pages.widgetspage;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class WidgetsPage {

    public WidgetsPage checkOpened() {
        // Раздел открыт, если видна хотя бы одна ссылка из него
        $(byAttribute("href", "/accordian")).shouldBe(Condition.visible);
        return this;
    }

    public AccordianPage openAccordian() {
        $(byAttribute("href", "/accordian")).click();
        return new AccordianPage();
    }

    public SliderPage openSlider() {
        $(byAttribute("href", "/slider")).click();
        return new SliderPage();
    }

    public ProgressBarPage openProgressBar() {
        $(byAttribute("href", "/progress-bar")).click();
        return new ProgressBarPage();
    }

    public TabsPage openTabs() {
        $(byAttribute("href", "/tabs")).click();
        return new TabsPage();
    }
}