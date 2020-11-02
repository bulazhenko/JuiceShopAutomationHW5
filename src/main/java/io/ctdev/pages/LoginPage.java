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

    @Step("User click not yet customer link")
    public void clickNotYetCustomerLink() {
        driver.findElement(notYetCustomerLink).click();
    }

    @Step("User fill email field")
    public void fillInEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("User fill in password field")
    public void fillInPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("User click login button")
    public void clickLoginButton() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(clickLoginButton));
        driver.findElement(clickLoginButton).click();
    }

    @Step("Is inbaild password or email Erorr present?")
    public boolean isInvalidPasswordOrEmailErrorPresent() {
        return !driver.findElements(invalidPasswordOrEmailError).isEmpty();
    }

    @Step("Is please provide a paswword Error present?")
    public boolean isPleaseProvidePasswordErrorPresent() {
        return !driver.findElements(errorMessagePleaseProvidePassword).isEmpty();
    }
}
