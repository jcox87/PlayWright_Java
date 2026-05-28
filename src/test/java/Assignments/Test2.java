package Assignments;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 extends BaseTest {

    @Description("Validate Login and Log Out")
    @Test
    public void ValidateLogInandLogOut(){

        page.navigate("https://freelance-learn-automation.vercel.app/");

        pages.navMenu().navigationMenu("login");

        //Validate user land on the Login Page
        Assert.assertTrue(page.url().contains("/login"));

        //Login
        pages.loginPage().LogintoApplication("admin@email.com","admin@123");

        //Validate Welcome Toast message
        PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");

        //Sign Out
        pages.navMenu().navigationMenu("logout");

        //Validate Sign Button is displayed
        PlaywrightAssertions.assertThat(pages.loginPage().signInButton()).isVisible();

    }
}
