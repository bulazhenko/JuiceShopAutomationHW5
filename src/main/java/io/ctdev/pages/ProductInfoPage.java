package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductInfoPage {

    private final WebDriver driver = SingletonWebDriver.getInstanceOfSingletonWebDriver();
    private By productTitleField = By.xpath("//mat-dialog-container//h1");
    private By productDescriptionField = By.xpath("//mat-dialog-container//h1/parent::div/div[1]");
    private By productPriceField = By.xpath("//mat-dialog-container//p");

    public String getProductTitle() {
        return driver.findElement(productTitleField).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescriptionField).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPriceField).getText();
    }
}
