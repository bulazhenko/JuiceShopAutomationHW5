import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.actions.AccountActions;
import io.ctdev.entities.SecurityQuestion;
import io.ctdev.entities.User;
import io.ctdev.pages.HomePage;
import io.ctdev.pages.LoginPage;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JuiceShopLoginTest extends BaseTest {

    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();

    private String email = RandomString.make(7) + "@jsshop.com";
    private String password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
    private String securityQuestionAnswer = RandomString.make();

    private final User testUser = new User(email, password, SecurityQuestion.randomQuestion(), securityQuestionAnswer);

    @BeforeMethod
    public void beforeTest() {
        openUrl(TestProperties.config.juiceShopUrl());
        AccountActions.registration(testUser);
    }

    @Test
    public void loginTest() {
        loginPage.fillInEmailField(email);
        loginPage.fillInPasswordField(password);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isYourBasketPresent());
    }

    //Negative Tests
    @Test
    public void loginWithInvalidPassword() {
        loginPage.fillInEmailField(email);
        loginPage.fillInPasswordField("12");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isInvalidPasswordOrEmailErrorPresent());
    }

    @Test
    public void loginWithEmptyEmailAndPassword() {
        loginPage.fillInEmailField("");
        loginPage.fillInPasswordField("");
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isInvalidPasswordOrEmailErrorPresent());

        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isPleaseProvidePasswordErrorPresent());
    }

    @Test
    public void loginWithEmptyEmailField() {
        loginPage.fillInEmailField("");
        loginPage.fillInPasswordField(password);
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isInvalidPasswordOrEmailErrorPresent());
    }
}
