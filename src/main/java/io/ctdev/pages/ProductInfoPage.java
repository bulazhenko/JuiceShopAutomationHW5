package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductInfoPage {

    private final WebDriver driver = SingletonWebDriver.getInstanceOfSingletonWebDriver();
    private By productTitleField = By.xpath("//mat-dialog-container//h1");
    private By productDescriptionField = By.xpath("//mat-dialog-container//h1/parent::div/div[1]");
    private By productPriceField = By.xpath("//mat-dialog-container//p");

    @Step("Get product tittle")
    public String getProductTitle() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitleField));
        return driver.findElement(productTitleField).getText();
    }

    @Step("Get product description")
    public String getProductDescription() {
        return driver.findElement(productDescriptionField).getText();
    }

    @Step("Get product price")
    public String getProductPrice() {
        return driver.findElement(productPriceField).getText();
    }
}
