package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class LoginPage {

    private Page page;

    private Locator emailBoxLocator;
    private Locator passwordBoxLocator;
    private Locator signInButtonLocator;
    private Locator signuplinkButton;

    public LoginPage(Page page){
        this.page = page;

      emailBoxLocator =  page.getByPlaceholder("Enter Email");
      passwordBoxLocator = page.getByPlaceholder("Enter Password");
      signInButtonLocator = page.locator("xpath=//button[text()='Sign in']");
      signuplinkButton = page.locator("//a[@href='/signup']");
    }

    // Getter locator for assertions
    public Locator emailBox() { return emailBoxLocator; }
    public Locator passwordBox() { return passwordBoxLocator; }
    public Locator signInButton() { return signInButtonLocator; }
    public Locator signUpButton() { return signuplinkButton; }



    //Method to Login to Account
    @Step("Login to account")
    public void LogintoApplication(String Email, String Password){

        //Add Email and Password
        emailBoxLocator.fill(Email);
        passwordBoxLocator.fill(Password);

        //Click Sign In Button
        signInButtonLocator.click();
    }
}
