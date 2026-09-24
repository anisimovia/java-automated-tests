package ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.headless = System.getenv("CI") != null;

        // Chrome flags for headless CI (GitHub Actions)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        Configuration.browserCapabilities = options;
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