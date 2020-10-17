package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver = SingletonWebDriver.getInstanceOfSingletonWebDriver();

    private By loginButton = By.cssSelector("button#navbarLoginButton");
    private By dismissButton = By.cssSelector("button[aria-label='Close Welcome Banner']");
    private By accountButton = By.cssSelector("button#navbarAccount");
    private By yourBasketButton = By.xpath("//button[@routerlink='/basket']");
    private By nextPageButton = By.xpath("//button[@aria-label='Next page']");
    private By addSoldOutProductButton = By.xpath("//mat-card[.//div[contains(@class,'ribbon-sold')]]//button");
    private By soldOutErrorMessage = By.xpath("//simple-snack-bar[contains(.,'We are out of stock! Sorry for the inconvenience.')]");
    private By meWantItDismiss = By.xpath("//a[@aria-label='dismiss cookie message']");

    private String productInfoButton = "//mat-grid-list//mat-grid-tile[%s]//mat-card";
    private String addProductToBasketButton = productInfoButton + "//button";

    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickDismissButton() {
        driver.findElement(dismissButton).click();
    }

    public boolean isYourBasketPresent() {
        return !driver.findElements(yourBasketButton).isEmpty();
    }

    public void openProductInfoPage(int productIndex) {
        driver.findElement(By.xpath(String.format(productInfoButton, productIndex))).click();
    }

    public void addProductToBasket(int productIndex) {
        driver.findElement(By.xpath(String.format(addProductToBasketButton, productIndex))).click();
    }

    public void openBasketPage() {
        driver.findElement(yourBasketButton).click();
    }

    public void nextPage() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
        driver.findElement(nextPageButton).click();
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void addSoldOutProduct() {
        driver.findElement(addSoldOutProductButton).click();
    }

    public boolean isSoldOutErrorMessagePresent() {
        return !driver.findElements(soldOutErrorMessage).isEmpty();
    }

    public void clickDismissMeWantButton() {
        driver.findElement(meWantItDismiss).click();
    }
}
