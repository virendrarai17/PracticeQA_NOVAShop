package practiceQA.NovaShop;

import NovaShop.PageFactory.CartPage;
import NovaShop.PageFactory.CheckoutPage;
import NovaShop.PageFactory.LoginPage;
import NovaShop.PageFactory.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    CartPage cartPage;

    @BeforeMethod
    public void initializePageFactoryPages(){
     loginPage =new LoginPage(page);
     productPage =new ProductPage(page);
    checkoutPage =new CheckoutPage(page);
    cartPage =new CartPage(page);
    }

    @Test(enabled = true)
    public void verifyLoginWithValidStandardUser(){
    loginPage.signIn("demo_user","Demo@123");
    }



}
