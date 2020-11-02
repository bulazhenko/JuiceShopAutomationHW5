package io.ctdev.cucumber.steps;

import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.ctdev.pages.HomePage;
import io.ctdev.pages.LoginPage;
import io.ctdev.pages.RegistrationPage;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;

public class SignUpStepdefs extends BaseTest  {

    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private RegistrationPage registrationPage = new RegistrationPage();

    private String email = RandomString.make(7) + "@jsshop.com";
    private String password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
    private int securityQuestionIndex = RandomData.get().nextInt(1, 13);
    private String securityQuestionAnswer = RandomString.make();


    @Given("User is open the homepage")
    public void userIsOpenTheHomepage() {
        openUrl(TestProperties.config.juiceShopUrl());
    }

    @And("User close the popup")
    public void userCloseThePopup() {
        homePage.clickDismissButton();
    }

    @And("User click Account button")
    public void userClickAccountButton() {
        homePage.clickAccountButton();
    }

    @And("User click Login button")
    public void userClickLoginButton() {
        homePage.clickLoginButton();
    }

    @And("User close Me Want popup")
    public void userCloseMeWantPopup() {
        homePage.clickDismissMeWantButton();
    }

    @And("User clicks Not yet a customer? button")
    public void userClicksNotYetACustomerButton() {
        loginPage.clickNotYetCustomerLink();
    }

    @Then("User is redirected to SignUp page")
    public void userIsRedirectedToSignUpPage() {
        Assert.assertEquals(getPageUrl(),"http://3.18.213.48/#/register");
    }

    @And("User fills the email field")
    public void userFillsTheEmailField() {
        registrationPage.fillEmailField(email);
    }

    @And("User fills the password field")
    public void userFillsThePasswordField() {
        registrationPage.fillPasswordField(password);
    }

    @And("User fills repeat password field")
    public void userFillsRepeatPasswordField() {
        registrationPage.fillRepeatPasswordField(password);
    }

    @And("User click sequrity question dropdown")
    public void userClickSequrityQuestionDropdown() {
        registrationPage.selectSecurityQuestion(securityQuestionIndex);
    }

    @And("User fill sequrity question answer")
    public void userFillSequrityQuestionAnswer() {
        registrationPage.fillAnswerField(securityQuestionAnswer);
    }

    @And("User clock Registration button")
    public void userClockRegistrationButton() {
        registrationPage.clickRegisterButton();
    }

    @Then("User is successfully registrated - pop up is displayed")
    public void userIsSuccessfullyRegistratedPopUpIsDisplayed() {
        Assert.assertTrue(registrationPage.isSuccessfulRegisterMessagePresent());
    }

}

