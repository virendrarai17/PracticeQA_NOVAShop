package NovaShop.PageFactory;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    Page page;

    public LoginPage(Page page) {
        this.page=page;
    }

    public void signIn(String username, String password){
        page.getByTestId("username").fill(username);
        page.getByTestId("password").fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
    }
}
