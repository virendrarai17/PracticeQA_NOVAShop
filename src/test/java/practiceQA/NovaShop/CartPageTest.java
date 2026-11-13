package practiceQA.NovaShop;

import NovaShop.PageFactory.CartPage;
import NovaShop.PageFactory.LoginPage;
import NovaShop.PageFactory.ProductPage;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.JsonReader;

import java.io.IOException;

public class CartPageTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void initializePageObject(){
        loginPage = new LoginPage(page);
        productPage = new ProductPage(page);
        cartPage = new CartPage(page);
    }

//    @Test
//    public void verifyApplyCoupon(){
//        loginPage.signIn("demo_user","Demo@123");
//        productPage.clickOnProduct("Drift Denim Jacket");
//        productPage.clickOnCartLink();
//        cartPage.applyCouponOnOrderSummary("SAVE10");
//        String message=cartPage.getCouponMessage();
//        System.out.println(message);
//        Assert.assertEquals("SAVE10 applied — 10% off your order",message);
//    }

    @Test
    public void verifyApplyCoupon() throws IOException {
        JSONObject testData = JsonReader.readJson(
                "TestData/Cart_Input_Data.json"
        );

        JSONObject couponData =
                testData.getJSONObject("verifyApplyCoupon");

        String username = couponData.getString("username");
        String password = couponData.getString("password");
        String productName = couponData.getString("productName");
        String couponCode = couponData.getString("couponCode");
        String expectedMessage = couponData.getString("expectedMessage");

        loginPage.signIn(username, password);

        productPage.clickOnProduct(productName);
        productPage.clickOnCartLink();

        cartPage.applyCouponOnOrderSummary(couponCode);

        String message = cartPage.getCouponMessage();

        System.out.println(message);

        Assert.assertEquals(expectedMessage, message);
    }
}
