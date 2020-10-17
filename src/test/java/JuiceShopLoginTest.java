import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.pages.HomePage;
import io.ctdev.pages.LoginPage;
import io.ctdev.pages.RegistrationPage;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JuiceShopLoginTest extends BaseTest {

    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private RegistrationPage registrationPage = new RegistrationPage();

    private String email = RandomString.make(7) + "@jsshop.com";
    private String password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
    private int securityQuestionIndex = RandomData.get().nextInt(1, 13);
    private String securityQuestionAnswer = RandomString.make();

    @Test
    public void loginTest() {
        openUrl(TestProperties.config.juiceShopUrl());
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

        loginPage.fillInEmailField(email);
        loginPage.fillInPasswordField(password);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isYourBasketPresent());
    }

    //Negative Tests
    @Test
    public void loginWithInvalidPassword() {
        openUrl(TestProperties.config.juiceShopUrl());
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

        loginPage.fillInEmailField(email);
        loginPage.fillInPasswordField("12");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isInvalidPasswordOrEmailErrorPresent());
    }

    @Test
    public void loginWithEmptyEmailAndPassword() {
        openUrl(TestProperties.config.juiceShopUrl());
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

        loginPage.fillInEmailField("");
        loginPage.fillInPasswordField("");
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isInvalidPasswordOrEmailErrorPresent());

        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isPleaseProvidePasswordErrorPresent());
    }

    @Test
    public void loginWithEmptyEmailField() {
        openUrl(TestProperties.config.juiceShopUrl());
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

        loginPage.fillInEmailField("");
        loginPage.fillInPasswordField(password);
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isInvalidPasswordOrEmailErrorPresent());
    }
}
