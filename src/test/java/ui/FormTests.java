package ui;

import com.aqa.course.data.Generator;
import com.aqa.course.data.PracticeFormData;
import com.aqa.course.ui.pages.formspage.PracticeFormPage;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;

import static com.codeborne.selenide.Selenide.open;

public class FormTests extends BaseTest {

    @Test
    public void practiceFormTest() {
        PracticeFormData data = new Generator().getPracticeFormData();

        open("/automation-practice-form");   // сразу на форму, минуя главную
        new PracticeFormPage()
                .checkOpened()
                .fillForm(data)
                .submit()
                .checkResult(data);
    }
}
