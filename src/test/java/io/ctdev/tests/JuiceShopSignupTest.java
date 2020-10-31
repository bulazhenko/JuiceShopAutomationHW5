package io.ctdev.tests;

import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.pages.HomePage;
import io.ctdev.pages.LoginPage;
import io.ctdev.pages.RegistrationPage;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JuiceShopSignupTest extends BaseTest {

    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private RegistrationPage registrationPage = new RegistrationPage();

    private String email;
    private String password;
    private int securityQuestionIndex;
    private String securityQuestionAnswer;

    @BeforeMethod
    public void beforeTest() {
        email = RandomString.make(7) + "@jsshop.com";
        password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
        securityQuestionAnswer = RandomString.make();
        securityQuestionIndex = RandomData.get().nextInt(1, 13);
        ;

        openUrl(TestProperties.config.juiceShopUrl());
    }

    @Test
    public void signUp() {
        homePage.clickDismissButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();
        loginPage.clickNotYetCustomerLink();
        registrationPage.fillEmailField(email);
        registrationPage.fillPasswordField(password);
        registrationPage.fillRepeatPasswordField(password);
        registrationPage.selectSecurityQuestion(securityQuestionIndex);
        registrationPage.fillAnswerField(securityQuestionAnswer);
        registrationPage.clickRegisterButton();
        Assert.assertTrue(registrationPage.isSuccessfulRegisterMessagePresent());
    }
}

