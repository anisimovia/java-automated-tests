package ui;

import com.aqa.course.ui.pages.MainPage;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InteractionTests extends BaseTest {
    //Selectable tests
    @Test
    public void selectablePageOpensTest() {
        new MainPage()
                .openInteractions()
                .openSelectable()
                .checkOpened()
                .shouldHaveListTabActive()
                .shouldHaveListPanelVisible();
    }

    @Test
    public void selectSingleListItemTest() {
        new MainPage()
                .openInteractions()
                .openSelectable()
                .checkOpened()
                .selectListItem("Cras justo odio")
                .shouldHaveListItemSelected("Cras justo odio")
                .shouldHaveListItemNotSelected("Dapibus ac facilisis in");
    }

    @Test
    public void selectMultipleListItemsTest() {
        new MainPage()
                .openInteractions()
                .openSelectable()
                .checkOpened()
                .selectListItem("Cras justo odio")
                .selectListItem("Morbi leo risus")
                .selectListItem("Porta ac consectetur ac")
                .shouldHaveListItemSelected("Cras justo odio")
                .shouldHaveListItemSelected("Morbi leo risus")
                .shouldHaveListItemSelected("Porta ac consectetur ac")
                .shouldHaveListItemNotSelected("Dapibus ac facilisis in");
    }

    @Test
    public void switchToListAndGridTabsTest() {
        new MainPage()
                .openInteractions()
                .openSelectable()
                .checkOpened()
                .shouldHaveListTabActive()
                .clickGridTab()
                .shouldHaveGridTabActive()
                .shouldHaveGridPanelVisible()
                .clickListTab()
                .shouldHaveListTabActive()
                .shouldHaveListPanelVisible();
    }

    @Test
    public void selectSingleGridItemTest() {
        new MainPage()
                .openInteractions()
                .openSelectable()
                .checkOpened()
                .clickGridTab()
                .selectGridItem("Five")
                .shouldHaveGridItemSelected("Five")
                .shouldHaveGridItemNotSelected("One");
    }

    @Test
    public void selectMultipleGridItemsTest() {
        new MainPage()
                .openInteractions()
                .openSelectable()
                .checkOpened()
                .clickGridTab()
                .selectGridItem("One")
                .selectGridItem("Five")
                .selectGridItem("Nine")
                .shouldHaveGridItemSelected("One")
                .shouldHaveGridItemSelected("Five")
                .shouldHaveGridItemSelected("Nine")
                .shouldHaveGridItemNotSelected("Two");
    }


    //Resizable tests
    @Test
    public void resizablePageOpensTest() {
        new MainPage()
                .openInteractions()
                .openResizable()
                .checkOpened();
    }

    @Test
    public void initialSizesAre200x200Test() {
        var page = new MainPage()
                .openInteractions()
                .openResizable()
                .checkOpened();

        assertEquals(200, page.getRestrictedBoxWidth(), "Restricted box width");
        assertEquals(200, page.getRestrictedBoxHeight(), "Restricted box height");
        assertEquals(200, page.getFreeBoxWidth(), "Free box width");
        assertEquals(200, page.getFreeBoxHeight(), "Free box height");
    }

    @Test
    public void resizeRestrictedBoxBiggerTest() {
        new MainPage()
                .openInteractions()
                .openResizable()
                .checkOpened()
                .resizeRestrictedBox(100, 50)
                .shouldHaveRestrictedBoxSize(300, 250);
    }

    @Test
    public void resizeFreeBoxBiggerTest() {
        new MainPage()
                .openInteractions()
                .openResizable()
                .checkOpened()
                .resizeFreeBox(150, 100)
                .shouldHaveFreeBoxSize(350, 300);
    }

    @Test
    public void resizeRestrictedBoxSmallerTest() {
        new MainPage()
                .openInteractions()
                .openResizable()
                .checkOpened()
                .resizeRestrictedBox(-50, -50)
                .shouldHaveRestrictedBoxSize(150, 150);   // min size
    }

    @Test
    public void resizeRestrictedBoxBeyondMaxIsClampedTest() {
        new MainPage()
                .openInteractions()
                .openResizable()
                .checkOpened()
                // Пытаемся растянуть на +400, но max 500x300
                .resizeRestrictedBox(400, 200)
                .shouldHaveRestrictedBoxSize(500, 300);   // max size
    }
}
