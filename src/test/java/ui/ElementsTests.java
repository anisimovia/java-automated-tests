package ui;

import com.aqa.course.data.Generator;
import com.aqa.course.data.TextBoxData;
import com.aqa.course.ui.pages.MainPage;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;

public class ElementsTests extends BaseTest {

    @Test
    public void fillTextBoxFormTest() {
        TextBoxData data = new Generator().getTextBoxData();

        new MainPage()
                .openElements()
                .openTextBox()
                .checkOpened()
                .fillForm(data)
                .submit()
                .checkOutput(data);
    }

    @Test
    public void selectTopLevelNodesTest() {
        new MainPage()
                .openElements()
                .openCheckBox()
                .checkOpened()
                .expandNode("Home")
                .toggleNode("Desktop")
                .toggleNode("Documents")
                .toggleNode("Downloads")
                .shouldBeChecked("Desktop")
                .shouldBeChecked("Documents")
                .shouldBeChecked("Downloads")
                .shouldHaveResult("desktop")
                .shouldHaveResult("documents")
                .shouldHaveResult("downloads");
    }

    @Test
    public void selectDeepNodeTest() {
        new MainPage()
                .openElements()
                .openCheckBox()
                .checkOpened()
                .expandNode("Home")
                .expandNode("Documents")
                .expandNode("WorkSpace")
                .toggleNode("React")
                .shouldBeChecked("React")
                .shouldHaveResult("react");
    }

    @Test
    public void selectYesRadioTest() {
        new MainPage()
                .openElements()
                .openRadioButton()
                .checkOpened()
                .selectYes()
                .shouldBeSelectedYes()
                .shouldHaveResult("Yes");
    }

    @Test
    public void selectImpressiveRadioTest() {
        new MainPage()
                .openElements()
                .openRadioButton()
                .checkOpened()
                .selectImpressive()
                .shouldBeSelectedImpressive()
                .shouldHaveResult("Impressive");
    }

    @Test
    public void noRadioIsDisabledTest() {
        new MainPage()
                .openElements()
                .openRadioButton()
                .checkOpened()
                .shouldBeDisabledNo();
    }

    @Test
    public void buttonsTest() {
        new MainPage()
                .openElements()
                .openButtons()
                .checkOpened()
                .doubleClick()
                .checkDoubleClickMessage()
                .rightClick()
                .checkRightClickMessage()
                .dynamicClick()
                .checkDynamicClickMessage();
    }
}