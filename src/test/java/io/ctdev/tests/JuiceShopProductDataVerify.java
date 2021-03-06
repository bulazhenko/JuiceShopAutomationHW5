package io.ctdev.tests;

import com.arakelian.faker.service.RandomData;
import io.ctdev.BaseTest;
import io.ctdev.TestProperties;
import io.ctdev.actions.AccountActions;
import io.ctdev.entities.Product;
import io.ctdev.entities.SecurityQuestion;
import io.ctdev.entities.User;
import io.ctdev.pages.HomePage;
import io.ctdev.pages.ProductInfoPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Product data")
@Story("Product data verification")

public class JuiceShopProductDataVerify extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final ProductInfoPage productInfoPage = new ProductInfoPage();

    private String email;
    private String password;
    private String securityQuestionAnswer;
    private User testUser;

    private String TITLE;
    private String DESCRIPTION;
    private String PRICE;

    private Product product;

    @BeforeMethod
    public void beforeTest() {
        email = RandomString.make(7) + "@jsshop.com";
        password = RandomString.make(5) + RandomData.get().nextInt(1000, 9999);
        securityQuestionAnswer = RandomString.make();
        testUser = new User(email, password, SecurityQuestion.randomQuestion(), securityQuestionAnswer);

        TITLE = "Green Smoothie";
        DESCRIPTION = "Looks poisonous but is actually very good for your health! Made from green cabbage, spinach, kiwi and grass.";
        PRICE = "1.99";

        product = new Product(TITLE, DESCRIPTION, PRICE);


        openUrl(TestProperties.config.juiceShopUrl());
        AccountActions.registration(testUser);
        AccountActions.login(testUser);
    }

    @AfterMethod
    public void afterTest() {
        homePage.clickLogoutButton();
    }

    @Test()
    @Description("Product title, description verification")
    public void productDataVerify() {
        homePage.openProductInfoPage(product);
        Assert.assertEquals(productInfoPage.getProductTitle(), product.getTitle());
        Assert.assertEquals(productInfoPage.getProductDescription(), product.getDescription());
        Assert.assertTrue(productInfoPage.getProductPrice().contains(product.getPrice()));
    }
}
