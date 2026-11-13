package NovaShop.PageFactory;

import com.microsoft.playwright.Page;

public class CheckoutPage {

    Page page;
    public CheckoutPage(Page page){
        this.page = page;
    }

    private static final String First_Name = "First Name";
    private static final String Last_Name = "Last Name";
    private static final String Email_Address = "Email";
    private static final String Phone_NUM= "Phone";
    private static final String Address = "Street address";
    private static final String City_Name = "City";
    private static final String Zip_Code= "ZIP code";
    private static final String State = "state";


    public void addContactAndShippingAddress(String firstName, String lastName,String email,
                                             String Phone, String address, String city, String zipcode){
        page.getByLabel(First_Name).fill(firstName);
        page.getByLabel(Last_Name).fill(lastName);
        page.getByLabel(Email_Address).fill(email);
        page.getByLabel(Phone_NUM).fill(Phone);
        page.getByLabel(Address).fill(address);
        page.getByLabel(City_Name).fill(city);
        page.getByLabel(Zip_Code).fill(zipcode);
        page.getByTestId(State).selectOption("Texas");
    }



    public void addPaymentDetails(String nameOnCard, String cardNum, String Expiry, String CVV){
    page.getByLabel("Name on card").fill(nameOnCard);
    page.getByLabel("Card number").fill(cardNum);
    page.getByLabel("Expiry").fill(Expiry);
    page.getByLabel("CVV").fill(CVV);
    page.getByTestId("accept-terms").click();
    }

    public void clickOnPlaceOrderButton(){
        page.getByTestId("place-order").click();
    }

    public String checkOutCompleteMessage(){
        String textContent=page.getByTestId("order-success-title").textContent();
        return textContent;
    }



}
