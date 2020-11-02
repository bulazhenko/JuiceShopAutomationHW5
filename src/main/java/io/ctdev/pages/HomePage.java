package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import io.ctdev.TestProperties;
import io.ctdev.entities.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver = SingletonWebDriver.getInstanceOfSingletonWebDriver();

    private By loginButton = By.xpath("//div//button[@routerlink='/login']");
    private By dismissButton = By.cssSelector("button[aria-label='Close Welcome Banner']");
    private By accountButton = By.cssSelector("button#navbarAccount");
    private By yourBasketButton = By.xpath("//button[@routerlink='/basket']");
    private By nextPageButton = By.xpath("//button[@aria-label='Next page']");
    private By addSoldOutProductButton = By.xpath("//mat-card[.//div[contains(@class,'ribbon-sold')]]//button");
    private By soldOutErrorMessage = By.xpath("//simple-snack-bar[contains(.,'We are out of stock! Sorry for the inconvenience.')]");
    private By meWantItDismiss = By.xpath("//a[@aria-label='dismiss cookie message']");
    private By cookiePopup = By.xpath("//div[@aria-describedby='cookieconsent:desc']");
    private By logoutButton = By.xpath("//button[@id='navbarLogoutButton']");
    private By productAddedPopup = By.xpath("//snack-bar-container[contains(.,'basket')]");
    private By basketFrame = By.xpath("//app-basket//mat-row");

    private String productInfoButton = "//mat-grid-list//mat-grid-tile[contains(.,'%s')]";
    private String addProductToBasketButton = productInfoButton + "//button";

    @Step("User click Account Button")
    public void clickAccountButton() {
        WebElement element = driver.findElement(accountButton);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Step("User click LoginButton")
    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginButton));

        WebElement element = driver.findElement(loginButton);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Step("User click Dismiss Button")
    public void clickDismissButton() {
        if (!driver.findElements(dismissButton).isEmpty()) {
            driver.findElement(dismissButton).click();
        }
    }

    @Step("Is user basket present?")
    public boolean isYourBasketPresent() {
        return !driver.findElements(yourBasketButton).isEmpty();
    }

    @Step("User open product info page")
    public void openProductInfoPage(Product product) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath(String.format(productInfoButton, product.getTitle()))).click();
    }

    @Step("Add soldout item to the basket")
    public void addSoldOutProductToBasket(Product product) {
        driver.findElement(By.xpath(String.format(addProductToBasketButton, product.getTitle()))).click();
    }

    @Step("Add product to the basket")
    public void addProductToBasket(Product product) {
        driver.findElement(By.xpath(String.format(addProductToBasketButton, product.getTitle()))).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productAddedPopup));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(productAddedPopup));

    }

    @Step("User open basket page")
    public void openBasketPage() {
        driver.findElement(yourBasketButton).click();
        new WebDriverWait(driver, 5).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(basketFrame));
    }

    @Step("Next page")
    public void nextPage() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
        driver.findElement(nextPageButton).click();
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Is soldout error message present?")
    public boolean isSoldOutErrorMessagePresent() {
        return !driver.findElements(soldOutErrorMessage).isEmpty();
    }

    @Step("User click dismiss Me Want button")
    public void clickDismissMeWantButton() {
        if (driver.findElement(cookiePopup).isDisplayed()) {
            driver.findElement(meWantItDismiss).click();
        }
    }

    @Step("User click logout button")
    public void clickLogoutButton() {
        driver.get(TestProperties.config.juiceShopUrl());
        clickAccountButton();
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(logoutButton));
        driver.findElement(logoutButton).click();
    }
}

