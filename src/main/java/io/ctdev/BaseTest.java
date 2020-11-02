package io.ctdev;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class BaseTest {

    WebDriver driver = SingletonWebDriver.getInstanceOfSingletonWebDriver();

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void logout() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
