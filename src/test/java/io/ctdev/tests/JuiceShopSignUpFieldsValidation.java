package io.ctdev.tests;

import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.pages.HomePage;
import io.ctdev.pages.LoginPage;
import io.ctdev.pages.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("SignUp validation")
@Epic("User signUo fields validation")
public class JuiceShopSignUpFieldsValidation extends BaseTest {

    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private RegistrationPage registrationPage = new RegistrationPage();

    private String email;
    private String password;
    private int securityQuestionIndex;
    private String securityQuestionAnswer;

    @BeforeMethod
    public void beforeTest() {
        openUrl(TestProperties.config.juiceShopUrl());
        email = RandomString.make(7) + "@jsshop.com";
        password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
        securityQuestionIndex = RandomData.get().nextInt(1, 13);
        securityQuestionAnswer = RandomString.make();
    }

    @Test
    @Description("User signUp email field validation with invalid email")
    public void signUpEmailValidation() {
        homePage.clickDismissButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();
        loginPage.clickNotYetCustomerLink();
        registrationPage.fillEmailField("1");
        registrationPage.fillPasswordField(password);
        registrationPage.fillRepeatPasswordField(password);
        registrationPage.selectSecurityQuestion(securityQuestionIndex);
        registrationPage.fillAnswerField(securityQuestionAnswer);
        registrationPage.clickRegisterButton();
        Assert.assertFalse(registrationPage.isSuccessfulRegisterMessagePresent());
        Assert.assertTrue(registrationPage.isEmailValidationErrorPresent());
    }

    @Test
    @Description("User signUp repeat password field validation with wrong password")
    public void signUpRepeatPasswordValidation() {
        homePage.clickDismissButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();
        loginPage.clickNotYetCustomerLink();
        registrationPage.fillEmailField(email);
        registrationPage.fillPasswordField(password);
        registrationPage.fillRepeatPasswordField("1");
        registrationPage.selectSecurityQuestion(securityQuestionIndex);
        registrationPage.fillAnswerField(securityQuestionAnswer);
        registrationPage.clickRegisterButton();
        Assert.assertFalse(registrationPage.isSuccessfulRegisterMessagePresent());
        Assert.assertTrue(registrationPage.isRepeatPasswordValidationErrorPresent());
    }

    @Test
    @Description("User signUp secret question field validation with empty data")
    public void signUpQuestionValidation() {
        homePage.clickDismissButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();
        loginPage.clickNotYetCustomerLink();
        registrationPage.fillEmailField(email);
        registrationPage.fillPasswordField(password);
        registrationPage.fillRepeatPasswordField(password);
        registrationPage.selectSecurityQuestion(securityQuestionIndex);
        registrationPage.fillAnswerField("");
        registrationPage.clickRegisterButton();
        Assert.assertTrue(registrationPage.isSecurityQuestionAnswerErrorPresent());
    }
}

