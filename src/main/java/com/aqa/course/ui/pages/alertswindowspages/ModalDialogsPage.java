package com.aqa.course.ui.pages.alertswindowspages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ModalDialogsPage {

    private final SelenideElement smallModalBtn = $("#showSmallModal");
    private final SelenideElement largeModalBtn = $("#showLargeModal");

    // Сама модалка — общие классы Bootstrap
    private final SelenideElement modal      = $(".modal-content");
    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement modalBody  = $(".modal-body");
    private final SelenideElement closeBtn   = $(".modal-footer button");
    private final SelenideElement closeIcon = $(".modal-header .btn-close");

    public ModalDialogsPage checkOpened() {
        $("h1").shouldHave(text("Modal Dialogs"));
        return this;
    }

    // --- Small Modal ---

    public ModalDialogsPage openSmallModal() {
        smallModalBtn.click();
        modal.shouldBe(visible);
        return this;
    }

    public ModalDialogsPage checkSmallModalContent() {
        modalTitle.shouldHave(text("Small Modal"));
        modalBody.shouldHave(text("This is a small modal"));
        return this;
    }

    // --- Large Modal ---

    public ModalDialogsPage openLargeModal() {
        largeModalBtn.click();
        modal.shouldBe(visible);
        return this;
    }

    public ModalDialogsPage checkLargeModalContent() {
        modalTitle.shouldHave(text("Large Modal"));
        modalBody.shouldHave(text("Lorem Ipsum"));
        return this;
    }

    // --- Закрытие ---

    public ModalDialogsPage closeByButton() {
        closeBtn.click();
        modal.shouldNotBe(visible);
        return this;
    }

    public ModalDialogsPage closeByIcon() {
        closeIcon.click();
        modal.shouldNotBe(visible);
        return this;
    }
}