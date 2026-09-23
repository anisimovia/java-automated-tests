package com.aqa.course.ui.pages.formspage;

import com.aqa.course.data.PracticeFormData;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PracticeFormPage {

    // --- Поля формы ---
    private final SelenideElement firstName      = $("#firstName");
    private final SelenideElement lastName       = $("#lastName");
    private final SelenideElement userEmail      = $("#userEmail");
    private final SelenideElement userNumber     = $("#userNumber");
    private final SelenideElement dateOfBirth    = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput  = $("#subjectsInput");
    private final SelenideElement uploadPicture  = $("#uploadPicture");
    private final SelenideElement currentAddress = $("#currentAddress");
    private final SelenideElement stateContainer = $("#state");
    private final SelenideElement cityContainer  = $("#city");
    private final SelenideElement submitBtn      = $("#submit");

    // --- Модальное окно результата ---
    private final SelenideElement resultModal    = $(".modal-content");
    private final SelenideElement resultTitle    = $("#example-modal-sizes-title-lg");

    public PracticeFormPage checkOpened() {
        $("h1").shouldHave(text("Practice Form"));
        return this;
    }

    public PracticeFormPage fillForm(PracticeFormData data) {
        firstName.setValue(data.getFirstName());
        lastName.setValue(data.getLastName());
        userEmail.setValue(data.getEmail());
        userNumber.setValue(data.getMobile());

        // Gender — radio, ищем по label с текстом Male/Female/Other
        $x("//label[text()='" + data.getGender() + "']").click();

        // Date of Birth — через календарь
        dateOfBirth.click();

        $("select.react-datepicker__year-select").selectOption("1990");
        $("select.react-datepicker__month-select").selectOption("May");
        $x("//div[contains(@class,'react-datepicker__day') and text()='10' and not(contains(@class,'outside-month'))]").click();

        // Subjects — react-select: вводим текст, ждём выпадающий список, жмём Enter
        subjectsInput.setValue(data.getSubject());
        $x("//div[contains(@class,'subjects-auto-complete__option') and text()='"
                + data.getSubject() + "']").click();

        // Hobbies — checkbox, ищем по label
        $x("//label[text()='" + data.getHobby() + "']").click();

        // Picture — загрузка файла
        uploadPicture.uploadFile(new java.io.File(data.getPicturePath()));

        // Current Address
        currentAddress.setValue(data.getAddress());

        // State — react-select. Кликаем по контейнеру, потом по появившейся опции
        stateContainer.click();
        $x("//div[contains(@id,'react-select') and text()='" + data.getState() + "']").click();

        // City — станет доступен после выбора State
        cityContainer.click();
        $x("//div[contains(@id,'react-select') and text()='" + data.getCity() + "']").click();

        return this;
    }

    public PracticeFormPage submit() {
        submitBtn.click();
        return this;
    }

    public PracticeFormPage checkResult(PracticeFormData data) {
        resultModal.shouldBe(visible);
        resultTitle.shouldHave(text("Thanks for submitting the form"));

        // Проверяем строки таблицы результатов: "Label" -> "Value"
        shouldHaveResultRow("Student Name",   data.getFullName());
        shouldHaveResultRow("Student Email",  data.getEmail());
        shouldHaveResultRow("Gender",         data.getGender());
        shouldHaveResultRow("Mobile",         data.getMobile());
        shouldHaveResultRow("Date of Birth",  data.getDateOfBirth());
        shouldHaveResultRow("Subjects",       data.getSubject());
        shouldHaveResultRow("Hobbies",        data.getHobby());
        shouldHaveResultRow("Address",        data.getAddress());
        shouldHaveResultRow("State and City", data.getState() + " " + data.getCity());

        return this;
    }

    private void shouldHaveResultRow(String label, String expectedValue) {
        String actualValue = $x("//td[text()='" + label + "']/following-sibling::td").getText();
        String actualNormalized = actualValue.replace(",", " ")
                .replaceAll("\\s+", " ")
                .trim();
        String expectedNormalized = expectedValue.replace(",", " ")
                .replaceAll("\\s+", " ")
                .trim();

        org.junit.jupiter.api.Assertions.assertEquals(
                expectedNormalized,
                actualNormalized,
                "Строка '" + label + "' содержит другое значение"
        );
    }
}