package Assignments;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends BaseTest {

    @Description("Validate Page Title")
    @Test
    public void PageValidation(){

        page.navigate("https://freelance-learn-automation.vercel.app/");

        String title = page.title();

        //Validate Page Title
        Assert.assertTrue(title.contains("Courses"));
        PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");

        page.waitForTimeout(1500);
        //Validate Course Count > 0
        Assert.assertTrue(page.locator("//div[@class='course-card row']").count()>0);

        //Validate Footer Icons > 0
        Assert.assertTrue(page.locator("//div[@class='social-btns']//a").count()>0);
    }
}
