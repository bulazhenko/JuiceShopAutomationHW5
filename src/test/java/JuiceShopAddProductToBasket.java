import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.actions.AccountActions;
import io.ctdev.entities.Product;
import io.ctdev.entities.SecurityQuestion;
import io.ctdev.entities.User;
import io.ctdev.pages.*;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JuiceShopAddProductToBasket extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final BasketPage basketPage = new BasketPage();

    private String email = RandomString.make(7) + "@jsshop.com";
    private String password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
    private String securityQuestionAnswer = RandomString.make();

    private final String TITLE = "Green Smoothie";
    private final String DESCRIPTION = "Looks poisonous but is actually very good for your health! Made from green cabbage, spinach, kiwi and grass.";
    private final String PRICE = "1.99";

    private final User testUser = new User(email, password, SecurityQuestion.randomQuestion(), securityQuestionAnswer);
    private final Product product = new Product(TITLE, DESCRIPTION, PRICE);

    @BeforeMethod
    public void beforeTest() {
        openUrl(TestProperties.config.juiceShopUrl());
        AccountActions.registration(testUser);
        AccountActions.login(testUser);
    }

    @Test
    public void verifyAddingProductToBasket() {
        homePage.addProductToBasket(product);
        homePage.openBasketPage();
        Assert.assertTrue(basketPage.isProductPresentInBasket(TITLE));
    }
}
