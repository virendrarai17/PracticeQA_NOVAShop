package practiceQA.NovaShop;

import NovaShop.PageFactory.CheckoutPage;
import NovaShop.PageFactory.LoginPage;
import NovaShop.PageFactory.ProductPage;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.JsonReader;

import java.io.IOException;

public class E2E_Test extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void initializeObjects(){
        loginPage = new LoginPage(page);
        productPage = new ProductPage(page);
        checkoutPage =new CheckoutPage(page);
    }

//    @Test(enabled = true)
//    public void verifyProductPurchase(){
//        loginPage.signIn("demo_user","Demo@123");
//        productPage.clickOnProduct("Bloom Indoor Plant Kit");
//        productPage.clickOnCartLink();
//        productPage.clickOnCheckoutButton();
//        checkoutPage.addContactAndShippingAddress("Virendra", "Rai", "Virendrarai17@gmail.com","9148222456","Quinton RD", "Birmingham","56007");
//        checkoutPage.addPaymentDetails("Virendra", "4242 4242 4242 4242", "12/30","123");
//        checkoutPage.clickOnPlaceOrderButton();
//        String name=checkoutPage.checkOutCompleteMessage();
//        System.out.println(name);
//        Assert.assertEquals("Thank you for your order!",name);
//    }

    @Test(enabled = true)
    public void verifyProductPurchase() throws IOException {
        // Read JSON file
        JSONObject testData = JsonReader.readJson(
                "TestData/E2E_Input_Data.json"
        );
        // Retrieve test scenario
        JSONObject purchaseData =
                testData.getJSONObject("verifyProductPurchase");
        // Retrieve login details
        String username = purchaseData.getString("username");
        String password = purchaseData.getString("password");

        // Retrieve product details
        String productName = purchaseData.getString("productName");

        // Retrieve shipping details
        JSONObject shippingData =
                purchaseData.getJSONObject("shippingDetails");

        String firstName = shippingData.getString("firstName");
        String lastName = shippingData.getString("lastName");
        String email = shippingData.getString("email");
        String phone = shippingData.getString("phone");
        String address = shippingData.getString("address");
        String city = shippingData.getString("city");
        String zipCode = shippingData.getString("zipCode");

        // Retrieve payment details
        JSONObject paymentData =
                purchaseData.getJSONObject("paymentDetails");

        String cardHolderName = paymentData.getString("cardHolderName");
        String cardNumber = paymentData.getString("cardNumber");
        String expiryDate = paymentData.getString("expiryDate");
        String cvv = paymentData.getString("cvv");

        // Retrieve expected message
        String expectedMessage =
                purchaseData.getString("expectedMessage");

        // Execute E2E test
        loginPage.signIn(username, password);

        productPage.clickOnProduct(productName);
        productPage.clickOnCartLink();
        productPage.clickOnCheckoutButton();

        checkoutPage.addContactAndShippingAddress(
                firstName, lastName, email, phone,
                address, city, zipCode
        );
        checkoutPage.addPaymentDetails(
                cardHolderName, cardNumber, expiryDate, cvv
        );
        checkoutPage.clickOnPlaceOrderButton();
        String name = checkoutPage.checkOutCompleteMessage();
        System.out.println(name);
        Assert.assertEquals(expectedMessage, name);
    }

}
