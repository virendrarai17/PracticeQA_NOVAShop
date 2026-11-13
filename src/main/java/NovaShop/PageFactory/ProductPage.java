package NovaShop.PageFactory;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductPage {
    Page page;

    public ProductPage(Page page){
        this.page = page;
    }

public void clickOnProduct(String productName){

        Locator product=page.getByTestId("product-name")
        .filter(new Locator.FilterOptions()
                .setHasText(productName));
        product.locator("xpath=following-sibling::div[2]/button")
                .click();
}

public void clickOnCartLink(){
        page.getByTestId("cart-link").click();
}

public void clickOnCheckoutButton(){
        page.getByTestId("checkout-button").click();
}




}
