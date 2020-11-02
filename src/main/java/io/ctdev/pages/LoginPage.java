package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import io.qameta.allure.Step;
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

    @Step
    public void clickNotYetCustomerLink() {
        driver.findElement(notYetCustomerLink).click();
    }

    @Step
    public void fillInEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step
    public void fillInPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step
    public void clickLoginButton() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(clickLoginButton));
        driver.findElement(clickLoginButton).click();
    }

    @Step
    public boolean isInvalidPasswordOrEmailErrorPresent() {
        return !driver.findElements(invalidPasswordOrEmailError).isEmpty();
    }

    @Step
    public boolean isPleaseProvidePasswordErrorPresent() {
        return !driver.findElements(errorMessagePleaseProvidePassword).isEmpty();
    }
}
