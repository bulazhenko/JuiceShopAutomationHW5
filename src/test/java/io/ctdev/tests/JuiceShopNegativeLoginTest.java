package io.ctdev.tests;

import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.actions.AccountActions;
import io.ctdev.entities.SecurityQuestion;
import io.ctdev.entities.User;
import io.ctdev.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Negative login")
@Story("User login fields validation, error's validation")

public class JuiceShopNegativeLoginTest extends BaseTest {
    private LoginPage loginPage = new LoginPage();

    private String email;
    private String password;
    private String securityQuestionAnswer;
    private User testUser;

    @BeforeMethod
    public void beforeTest() {
        email = RandomString.make(7) + "@jsshop.com";
        password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
        securityQuestionAnswer = RandomString.make();
        testUser = new User(email, password, SecurityQuestion.randomQuestion(), securityQuestionAnswer);

        openUrl(TestProperties.config.juiceShopUrl());
        AccountActions.openLoginform(testUser);
    }

    @Test
    @Description("Invalid password present via - User login with empty password field")
    public void loginWithInvalidPassword() {
        loginPage.fillInEmailField(email);
        loginPage.fillInPasswordField("12");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isInvalidPasswordOrEmailErrorPresent());
    }

    @Test
    @Description("Invalid email&password present via - User login with empty data")
    public void loginWithEmptyEmailAndPassword() {
        loginPage.fillInEmailField("");
        loginPage.fillInPasswordField("");
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isInvalidPasswordOrEmailErrorPresent());

        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isPleaseProvidePasswordErrorPresent());
    }

    @Test
    @Description("User login with email field")
    public void loginWithEmptyEmailField() {
        loginPage.fillInEmailField("");
        loginPage.fillInPasswordField(password);
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isInvalidPasswordOrEmailErrorPresent());
    }
}