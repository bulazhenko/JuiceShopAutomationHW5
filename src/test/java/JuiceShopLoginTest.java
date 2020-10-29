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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JuiceShopLoginTest extends BaseTest {

    private HomePage homePage = new HomePage();
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
        AccountActions.registration(testUser);
    }

    @AfterMethod
    public void afterTest() {
        homePage.clickLogoutButton();
    }

    @Test
    public void loginTest() {
        loginPage.fillInEmailField(email);
        loginPage.fillInPasswordField(password);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isYourBasketPresent());
    }
}
