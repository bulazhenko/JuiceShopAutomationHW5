package io.ctdev.pages;

import io.ctdev.SingletonWebDriver;
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

    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void fillRepeatPasswordField(String repeatPassword) {
        driver.findElement(repeatPasswordField).sendKeys(repeatPassword);
    }

    public void selectSecurityQuestion(int index) {
        driver.findElement(securityQuestionDropdown).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[@id='mat-select-1-panel']//mat-option[%s]", index))));
        driver.findElement(By.xpath(String.format("//div[@id='mat-select-1-panel']//mat-option[%s]", index))).click();
    }

    public void fillAnswerField(String answer) {
        driver.findElement(answerField).sendKeys(answer);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public boolean isSuccessfulRegisterMessagePresent() {
        return !driver.findElements(successfulRegisterMessage).isEmpty();
    }

    public boolean isEmailValidationErrorPresent() {
        return !driver.findElements(emailValidataionError).isEmpty();
    }

    public boolean isRepeatPasswordValidationErrorPresent() {
        return !driver.findElements(repeatPasswordValidationError).isEmpty();
    }

    public boolean isSecurityQuestionAnswerErrorPresent() {
        return !driver.findElements(securityQuestionValidationError).isEmpty();
    }
}
