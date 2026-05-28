package Assignments;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class Test3 extends BaseTest {

    @Description("Register New User")
    @Test
    public void RegisterNewUser(){

        page.navigate("https://freelance-learn-automation.vercel.app/login");

        //Validate Sign Button is displayed
        PlaywrightAssertions.assertThat(pages.loginPage().signUpButton()).isEnabled();

        //Create New User
        pages.loginPage().signUpButton().click();

        pages.signUpPage().RegisterNewUser();

        //Validate Successfully Toast is displayed
        PlaywrightAssertions.assertThat(page.locator(".Toastify")).containsText("Signup successfully");

    }
}
