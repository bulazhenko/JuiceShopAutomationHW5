package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver = SingletonWebDriver.getInstanceOfSingletonWebDriver();

    private By notYetCustomerLink = By.cssSelector("div#newCustomerLink a");
    private By emailField = By.cssSelector("input#email");
    private By passwordField = By.cssSelector("input#password");
    private By clickLoginButton = By.cssSelector("button#loginButton");
    private By invalidPasswordOrEmailError = By.xpath("//div[@class='error ng-star-inserted']");
    private By errorMessagePleaseProvidePassword = By.xpath("//mat-error[@id='mat-error-2']");

    public void clickNotYetCustomerLink() {
        driver.findElement(notYetCustomerLink).click();
    }

    public void fillInEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillInPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(clickLoginButton));
        driver.findElement(clickLoginButton).click();
    }

    public boolean isInvalidPasswordOrEmailErrorPresent() {
        return !driver.findElements(invalidPasswordOrEmailError).isEmpty();
    }

    public boolean isPleaseProvidePasswordErrorPresent() {
        return !driver.findElements(errorMessagePleaseProvidePassword).isEmpty();
    }
}
