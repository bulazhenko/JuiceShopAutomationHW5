package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import io.ctdev.TestProperties;
import io.ctdev.entities.Product;
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
    private By accountDropdown = By.xpath("//div[@role='menu']/div[contains(@class,'mat-menu-content')]");
    private By productAddedPopup = By.xpath("//snack-bar-container[contains(.,'basket')]");
    private By basketFrame = By.xpath("//app-basket//mat-row");

    private String productInfoButton = "//mat-grid-list//mat-card[contains(.,'%s')]";
    private String addProductToBasketButton = productInfoButton + "//button";

    public void clickAccountButton() {
        WebElement element = driver.findElement(accountButton);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginButton));
        //driver.findElement(loginButton).click();
        WebElement element = driver.findElement(loginButton);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickDismissButton() {
        if (!driver.findElements(dismissButton).isEmpty()) {
            driver.findElement(dismissButton).click();
        }
    }

    public boolean isYourBasketPresent() {
        return !driver.findElements(yourBasketButton).isEmpty();
    }

    public void openProductInfoPage(Product product) {
        driver.findElement(By.xpath(String.format(productInfoButton, product.getTitle()))).click();
    }

    public void addSoldOutProductToBasket(Product product) {
        driver.findElement(By.xpath(String.format(addProductToBasketButton, product.getTitle()))).click();
    }

    public void addProductToBasket(Product product) {
        driver.findElement(By.xpath(String.format(addProductToBasketButton, product.getTitle()))).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productAddedPopup));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(productAddedPopup));

    }

    public void openBasketPage() {
        driver.findElement(yourBasketButton).click();
        new WebDriverWait(driver, 5).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(basketFrame));
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
        if (driver.findElement(cookiePopup).isDisplayed()) {
            driver.findElement(meWantItDismiss).click();
        }
    }

    public void clickLogoutButton() {
        driver.get(TestProperties.config.juiceShopUrl());
        driver.navigate().refresh();
        new WebDriverWait(driver, 15).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        clickAccountButton();
        // driver.findElement(accountDropdown).isDisplayed();
        driver.findElement(logoutButton).click();
    }
}

