package Assignments;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test6 extends BaseTest {

    @Description("Validate Page Title")
    @Test
    public void FailScreeShotValidation(){

        page.navigate("https://freelance-learn-automation.vercel.app/");

        String title = page.title();

        //Validate Page Title
        Assert.assertTrue(title.contains("Courses"));
        PlaywrightAssertions.assertThat(page).hasTitle("Learnd Automation Courses");

        page.waitForTimeout(1500);

        //Validate Course Count > 7
        Assert.assertTrue(page.locator("//div[@class='course-card row']").count()>7,"Course Count should be greater than 7");

    }
}
