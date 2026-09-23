package ui;

import com.aqa.course.ui.pages.MainPage;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;

import static com.codeborne.selenide.Selenide.sleep;

public class WidgetsTests extends BaseTest {
    //Accordian
    @Test
    public void accordianPageOpensTest() {
        new MainPage()
                .openWidgets()
                .openAccordian()
                .checkOpened();
    }

    @Test
    public void firstSectionOpenedByDefaultTest() {
        new MainPage()
                .openWidgets()
                .openAccordian()
                .checkOpened()
                .checkFirstBodyContains("Lorem Ipsum is simply dummy text")
                .checkSecondBodyHidden()
                .checkThirdBodyHidden();
    }

    @Test
    public void secondSectionExpandsOnClickTest() {
        new MainPage()
                .openWidgets()
                .openAccordian()
                .checkOpened()
                .expandSecond()
                .checkSecondBodyContains("Contrary to popular belief");
    }

    @Test
    public void thirdSectionExpandsOnClickTest() {
        new MainPage()
                .openWidgets()
                .openAccordian()
                .checkOpened()
                .expandThird()
                .checkThirdBodyContains("long established fact");
    }

    @Test
    public void onlyOneSectionOpenedAtATimeTest() {
        new MainPage()
                .openWidgets()
                .openAccordian()
                .checkOpened()
                // Изначально открыт первый
                .checkFirstBodyContains("Lorem Ipsum is simply dummy text")
                // Открываем второй — первый должен закрыться
                .expandSecond()
                .checkSecondBodyContains("Contrary to popular belief")
                .checkFirstBodyHidden()
                .checkThirdBodyHidden()
                // Открываем третий — второй закрывается
                .expandThird()
                .checkThirdBodyContains("long established fact")
                .checkFirstBodyHidden()
                .checkSecondBodyHidden();
    }

    //Slider
    @Test
    public void sliderPageOpensTest() {
        new MainPage()
                .openWidgets()
                .openSlider()
                .checkOpened();
    }

    @Test
    public void defaultSliderValueIs25Test() {
        new MainPage()
                .openWidgets()
                .openSlider()
                .checkOpened()
                .shouldHaveValue(25);
    }

    @Test
    public void increaseSliderValueTest() {
        new MainPage()
                .openWidgets()
                .openSlider()
                .checkOpened()
                .setSliderValue(50)
                .shouldHaveValue(50);
    }

    @Test
    public void decreaseSliderValueTest() {
        new MainPage()
                .openWidgets()
                .openSlider()
                .checkOpened()
                .setSliderValue(10)
                .shouldHaveValue(10);
    }

    @Test
    public void setSliderToMaximumTest() {
        new MainPage()
                .openWidgets()
                .openSlider()
                .checkOpened()
                .setSliderValue(100)
                .shouldHaveValue(100);
    }

    @Test
    public void setSliderToMinimumTest() {
        new MainPage()
                .openWidgets()
                .openSlider()
                .checkOpened()
                .setSliderValue(0)
                .shouldHaveValue(0);
    }

    //Progress Bar Tests
    @Test
    public void progressBarPageOpensTest() {
        new MainPage()
                .openWidgets()
                .openProgressBar()
                .checkOpened()
                .shouldBeAtZero();
    }

    @Test
    public void startButtonChangesToStopTest() {
        new MainPage()
                .openWidgets()
                .openProgressBar()
                .checkOpened()
                .start();
        // Проверяем, что кнопка теперь показывает "Stop"
        com.codeborne.selenide.Selenide.$("#startStopButton")
                .shouldHave(com.codeborne.selenide.Condition.text("Stop"));
    }

    @Test
    public void progressIncreasesAfterStartTest() {
        var page = new MainPage()
                .openWidgets()
                .openProgressBar()
                .checkOpened()
                .start();

        // Ждём, пока значение превысит 10
        com.codeborne.selenide.Selenide.Wait()
                .until(wd -> page.getCurrentValue() > 10);

        page.stop();
    }

    @Test
    public void progressStopsAfterStopClickTest() {
        var page = new MainPage()
                .openWidgets()
                .openProgressBar()
                .checkOpened()
                .start();

        // Ждём, пока прогресс дойдёт хотя бы до 20
        com.codeborne.selenide.Selenide.Wait()
                .until(wd -> page.getCurrentValue() >= 20);

        page.stop();

        int valueAfterStop = page.getCurrentValue();

        // Спим секунду и убеждаемся, что значение не изменилось
        sleep(1000);

        org.junit.jupiter.api.Assertions.assertEquals(
                valueAfterStop, page.getCurrentValue(),
                "Значение прогресса изменилось после клика Stop"
        );
    }

    @Test
    public void progressReachesHundredTest() {
        var page = new MainPage()
                .openWidgets()
                .openProgressBar()
                .checkOpened()
                .start();

        // Ждём 100%. Максимум ~11 секунд (100 * 400ms) + запас
        page.shouldBeAtHundred();
    }

    //Tabs tests
    @Test
    public void tabsPageOpensTest() {
        new MainPage()
                .openWidgets()
                .openTabs()
                .checkOpened();
    }

    @Test
    public void whatTabIsActiveByDefaultTest() {
        new MainPage()
                .openWidgets()
                .openTabs()
                .checkOpened()
                .shouldHaveWhatTabActive()
                .shouldHaveWhatPanelVisible()
                .shouldHaveWhatText("Lorem Ipsum is simply dummy text");
    }

    @Test
    public void switchToOriginTabTest() {
        new MainPage()
                .openWidgets()
                .openTabs()
                .checkOpened()
                .clickOrigin()
                .shouldHaveOriginTabActive()
                .shouldHaveOriginPanelVisible()
                .shouldHaveOriginText("Contrary to popular belief");
    }

    @Test
    public void switchToUseTabTest() {
        new MainPage()
                .openWidgets()
                .openTabs()
                .checkOpened()
                .clickUse()
                .shouldHaveUseTabActive()
                .shouldHaveUsePanelVisible()
                .shouldHaveUseText("long established fact");
    }

    @Test
    public void switchBetweenTabsKeepsOnlyOneActiveTest() {
        new MainPage()
                .openWidgets()
                .openTabs()
                .checkOpened()
                // Изначально What активен
                .shouldHaveWhatTabActive()
                // Переключаемся на Origin
                .clickOrigin()
                .shouldHaveOriginTabActive()
                // Переключаемся на Use
                .clickUse()
                .shouldHaveUseTabActive()
                // Возвращаемся на What
                .clickWhat()
                .shouldHaveWhatTabActive();
    }

    @Test
    public void moreTabIsDisabledTest() {
        new MainPage()
                .openWidgets()
                .openTabs()
                .checkOpened()
                .shouldHaveMoreTabDisabled();
    }
}
