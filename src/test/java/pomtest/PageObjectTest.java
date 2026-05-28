package pomtest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;
import pages.LoginPage;

public class PageObjectTest {

    @Test
    public void login(){
        Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://freelance-learn-automation.vercel.app/login");

        LoginPage loginPage = new LoginPage(page);
        loginPage.LogintoApplication("admin@email.com","admin@123");

        page.close();
        browser.close();
    }


}
