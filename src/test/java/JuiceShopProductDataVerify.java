import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.pages.HomePage;
import io.ctdev.pages.LoginPage;
import io.ctdev.pages.ProductInfoPage;
import io.ctdev.pages.RegistrationPage;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JuiceShopProductDataVerify extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final ProductInfoPage productInfoPage = new ProductInfoPage();

    private String email = RandomString.make(7) + "@jsshop.com";
    private String password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
    private int securityQuestionIndex = RandomData.get().nextInt(1, 13);
    private String securityQuestionAnswer = RandomString.make();

    private final String PRODUCT_TITLE = "Green Smoothie";
    private final String PRODUCT_DESCRIPTION = "Looks poisonous but is actually very good for your health! Made from green cabbage, spinach, kiwi and grass.";
    private final String PRODUCT_PRICE = "1.99";

    @Test
    public void productDataVerify() {
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

        homePage.openProductInfoPage(7);
        Assert.assertEquals(productInfoPage.getProductTitle(),PRODUCT_TITLE);
        Assert.assertEquals(productInfoPage.getProductDescription(),PRODUCT_DESCRIPTION);
        Assert.assertTrue(productInfoPage.getProductPrice().contains(PRODUCT_PRICE));
    }
}
