package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage {

    private final WebDriver driver = SingletonWebDriver.getInstanceOfSingletonWebDriver();

    private String basketProductContainer = "//mat-row[contains(.,'%s')]";

    public boolean isProductPresentInBasket(String title) {
      return !driver.findElements(By.xpath(String.format(basketProductContainer,title))).isEmpty();
    }
}
