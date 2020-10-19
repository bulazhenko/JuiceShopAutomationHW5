package io.ctdev.actions;

import io.ctdev.entities.User;
import io.ctdev.pages.HomePage;
import io.ctdev.pages.LoginPage;
import io.ctdev.pages.RegistrationPage;
import org.testng.Assert;

public class AccountActions {

    private static HomePage homePage = new HomePage();
    private static LoginPage loginPage = new LoginPage();
    private static RegistrationPage registrationPage = new RegistrationPage();

    public static void registration(User user) {
        homePage.clickDismissButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();
        homePage.clickDismissMeWantButton();
        loginPage.clickNotYetCustomerLink();
        registrationPage.fillEmailField(user.getEmail());
        registrationPage.fillPasswordField(user.getPassword());
        registrationPage.fillRepeatPasswordField(user.getPassword());
        registrationPage.selectSecurityQuestion(user.getSecurityQuestion().getIndex());
        registrationPage.fillAnswerField(user.getAnswer());
        registrationPage.clickRegisterButton();
        Assert.assertTrue(registrationPage.isSuccessfulRegisterMessagePresent());
    }

    public static void login(User user) {
        loginPage.fillInEmailField(user.getEmail());
        loginPage.fillInPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isYourBasketPresent());
    }
}
