package ui;

import com.aqa.course.ui.pages.MainPage;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;

public class AlertsWindowsTests extends BaseTest {
    //Windows tests
    @Test
    public void newTabOpensSamplePageTest() {
        new MainPage()
                .openAlertsWindows()
                .openBrowserWindows()
                .checkOpened()
                .clickNewTab()
                .switchToNewWindow()
                .checkOpened()
                .switchBack();
    }

    @Test
    public void newWindowOpensTest() {
        var browserWindowsPage = new MainPage()
                .openAlertsWindows()
                .openBrowserWindows()
                .checkOpened();

        int before = com.codeborne.selenide.WebDriverRunner
                .getWebDriver().getWindowHandles().size();
        browserWindowsPage.clickNewWindow();

        com.codeborne.selenide.Selenide.Wait()
                .until(wd -> wd.getWindowHandles().size() > before);

        browserWindowsPage.checkOpened();
    }

    @Test
    public void newWindowMessageOpensTest() {
        var browserWindowsPage = new MainPage()
                .openAlertsWindows()
                .openBrowserWindows()
                .checkOpened();

        int before = com.codeborne.selenide.WebDriverRunner
                .getWebDriver().getWindowHandles().size();
        browserWindowsPage.clickNewWindowMessage();

        com.codeborne.selenide.Selenide.Wait()
                .until(wd -> wd.getWindowHandles().size() > before);

        browserWindowsPage.checkOpened();
    }

    //Modal Dialogs tests
    @Test
    public void modalDialogsPageOpensTest() {
        new MainPage()
                .openAlertsWindows()
                .openModalDialogs()
                .checkOpened();
    }

    @Test
    public void smallModalOpensAndClosesTest() {
        new MainPage()
                .openAlertsWindows()
                .openModalDialogs()
                .checkOpened()
                .openSmallModal()
                .checkSmallModalContent()
                .closeByButton();
    }

    @Test
    public void largeModalOpensAndClosesTest() {
        new MainPage()
                .openAlertsWindows()
                .openModalDialogs()
                .checkOpened()
                .openLargeModal()
                .checkLargeModalContent()
                .closeByButton();
    }

    @Test
    public void smallModalClosesByIconTest() {
        new MainPage()
                .openAlertsWindows()
                .openModalDialogs()
                .checkOpened()
                .openSmallModal()
                .checkSmallModalContent()
                .closeByIcon();
    }

    //Frames Test
    @Test
    public void framesPageOpensTest() {
        new MainPage()
                .openAlertsWindows()
                .openFrames()
                .checkOpened();
    }

    @Test
    public void frame1ContainsSamplePageTest() {
        new MainPage()
                .openAlertsWindows()
                .openFrames()
                .checkOpened()
                .checkFrame1Content();
    }

    @Test
    public void frame2ContainsSamplePageTest() {
        new MainPage()
                .openAlertsWindows()
                .openFrames()
                .checkOpened()
                .checkFrame2Content();
    }
}