package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private final WebDriver driver = SingletonWebDriver.getInstanceOfSingletonWebDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, 5);

    private By registerButton = By.cssSelector("button#registerButton");
    private By emailField = By.cssSelector("input#emailControl");
    private By passwordField = By.cssSelector("input#passwordControl");
    private By repeatPasswordField = By.cssSelector("input#repeatPasswordControl");
    private By securityQuestionDropdown = By.cssSelector("div#registration-form div.security-container mat-form-field:nth-child(1)");
    private By answerField = By.cssSelector("input#securityAnswerControl");
    private By successfulRegisterMessage = By.xpath("//snack-bar-container[contains(.,'Registration completed successfully. You can now log in.')]");
    private By emailValidataionError = By.xpath("//mat-error[contains(.,'Email address is not valid.')]");
    private By repeatPasswordValidationError = By.xpath("//mat-error[contains(.,'Passwords do not match')]");
    private By securityQuestionValidationError = By.xpath("//mat-error[contains(.,' Please provide an answer to your security question.' )]");

    @Step("User fill the email field")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("User fill the password field")
    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("User fill the repeat password field")
    public void fillRepeatPasswordField(String repeatPassword) {
        driver.findElement(repeatPasswordField).sendKeys(repeatPassword);
    }

    @Step("User select security qustion")
    public void selectSecurityQuestion(int index) {
        driver.findElement(securityQuestionDropdown).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(String.format("[id^=mat-option]:nth-child(%s)", index))));
        driver.findElement(By.cssSelector(String.format("[id^=mat-option]:nth-child(%s)", index))).click();
    }

    @Step("User fill the answer field")
    public void fillAnswerField(String answer) {
        driver.findElement(answerField).sendKeys(answer);
    }

    @Step("User click registration button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Is successful registration message present?")
    public boolean isSuccessfulRegisterMessagePresent() {
        return !driver.findElements(successfulRegisterMessage).isEmpty();
    }

    @Step("Is email validation Error present?")
    public boolean isEmailValidationErrorPresent() {
        return !driver.findElements(emailValidataionError).isEmpty();
    }

    @Step("Is repeat password validation Error present?")
    public boolean isRepeatPasswordValidationErrorPresent() {
        return !driver.findElements(repeatPasswordValidationError).isEmpty();
    }

    @Step("Is security question answer Error present?")
    public boolean isSecurityQuestionAnswerErrorPresent() {
        return !driver.findElements(securityQuestionValidationError).isEmpty();
    }
}
