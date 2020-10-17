import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.pages.*;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JuiceShopAddProductToBasket extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final BasketPage basketPage = new BasketPage();

    private String email = RandomString.make(7) + "@jsshop.com";
    private String password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
    private int securityQuestionIndex = RandomData.get().nextInt(1, 13);
    private String securityQuestionAnswer = RandomString.make();

    @Test
    public void verifyAddingProductToBasket() {
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

        homePage.addProductToBasket(7);
        homePage.openBasketPage();
        Assert.assertTrue(basketPage.isProductPresentInBasket("Green Smoothie"));
    }
}
