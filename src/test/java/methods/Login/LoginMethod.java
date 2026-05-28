package methods.Login;

import com.microsoft.playwright.Page;
import pages.LoginPage;

public class LoginMethod extends LoginPage {


    public LoginMethod(Page page) {
        super(page);
        LoginPage loginPage = new LoginPage(page);
    }

    public void LogintoApplication(){


    }
}
