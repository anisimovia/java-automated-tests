package ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
    }

    @AfterEach
    public void cleanup() {
        if (!WebDriverRunner.hasWebDriverStarted()) return;

        var driver = WebDriverRunner.getWebDriver();
        var handles = driver.getWindowHandles();
        if (handles.size() <= 1) return;

        String first = handles.iterator().next();
        for (String handle : handles) {
            if (!handle.equals(first)) {
                try {
                    driver.switchTo().window(handle).close();
                } catch (Exception ignored) {
                    // окно могло зависнуть — игнорируем и продолжаем
                }
            }
        }
        driver.switchTo().window(first);
    }
}