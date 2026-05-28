package Assignments;

import base.BaseTest;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Order;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test5 extends BaseTest {

    @Description("Add a Category")
    @Test
    @Order(1)
    public void AssignmentAddCategory() {

        page.navigate("https://freelance-learn-automation.vercel.app/login");
        PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
        //Login
        pages.loginPage().LogintoApplication("admin@email.com","admin@123");

        PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");

        pages.navMenu().manageButton().hover();

        Page newPage = context.waitForPage(() ->{
            pages.navMenu().manageCategoriesButton().click();
        });


        newPage.onDialog(dialog ->{
            String msg = dialog.message();
            Assert.assertTrue(msg.contains("Enter a Category Name"));
            dialog.accept("Test 1");
        });

        newPage.locator("//button[normalize-space()='Add New Category']").click();

        PlaywrightAssertions.assertThat(newPage.locator("xpath=//td[normalize-space()='Test 1']")).isVisible();

        newPage.close();
        browser.close();
    }

    @Description("Update and Delete Category")
    @Test
    @Order(2)
    public void AssignmentUpdateAndDeleteCategory() {

        page.navigate("https://freelance-learn-automation.vercel.app/login");
        PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
        //Login
        pages.loginPage().LogintoApplication("admin@email.com","admin@123");

        PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");

        pages.navMenu().manageButton().hover();

        Page newPage = context.waitForPage(() ->{
            pages.navMenu().manageCategoriesButton().click();
        });

        PlaywrightAssertions.assertThat(newPage.locator("xpath=//td[normalize-space()='Test 1']")).isVisible();

        newPage.onDialog(dialog ->{
            String msg = dialog.message();
            Assert.assertTrue(msg.contains("Update the category"));
            dialog.accept("Test 2");
        });

        newPage.locator("xpath=//td[normalize-space()='Test 1']/following::button[normalize-space()='Update']").click();
        newPage.waitForTimeout(6000);
        PlaywrightAssertions.assertThat(newPage.locator("xpath=//td[normalize-space()='Test 2']")).isVisible();


        newPage.onDialog(dialog ->{
            String msg = dialog.message();
            Assert.assertTrue(msg.contains("Update the category"));
            dialog.accept("Test 2");
        });

        newPage.locator("xpath=//td[normalize-space()='Test 2']/following::button[normalize-space()='Delete'][1]").click();

        newPage.locator("//button[@class='action-btn'][normalize-space()='Delete']").click();

        PlaywrightAssertions.assertThat(newPage.locator("xpath=//td[normalize-space()='Test 2']")).not().isVisible();

        newPage.close();
        browser.close();
    }
}
