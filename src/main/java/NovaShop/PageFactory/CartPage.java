package NovaShop.PageFactory;

import com.microsoft.playwright.Page;

public class CartPage {

    Page page;
    public CartPage(Page page) {
        this.page = page;
    }

    public void applyCouponOnOrderSummary(String couponCode){
        page.getByTestId("coupon-input").fill(couponCode);
        page.getByTestId("apply-coupon").click();
    }

    public String getCouponMessage(){
        String message=page.getByTestId("coupon-message").textContent();
        return message;
    }



}
