package Assignments;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Path;

public class Test4 extends BaseTest {

    @Description("Create and Delete new Course")
    @Test
    public void CreateAndDeleteNewCourse(){

        page.navigate("https://freelance-learn-automation.vercel.app/login");

        //Login
        pages.loginPage().LogintoApplication("admin@email.com","admin@123");

        //Click Manage Courses
        pages.navMenu().manageButton().hover();
        pages.navMenu().manageCourseButton().click();

        page.onDialog(dialog ->{
            String msg = dialog.message();
            Assert.assertTrue(msg.contains("File size should be less than 1MB"));
            dialog.accept();
        });

        //Click Add New Course
        pages.manageCoursePage().addNewCourseButton().click();

        pages.manageCoursePage().addThumbnail().setInputFiles(Path.of(System.getProperty("user.dir")+"/files/BigPic.jpeg"));

        pages.manageCoursePage().getAddNewCourseName().fill("Test Course");
        pages.manageCoursePage().getAddDescription().fill("Add Test Automation Course");
        pages.manageCoursePage().getAddInstructorName().fill("JC Test");
        pages.manageCoursePage().getAddPrice().fill("1500");
        pages.manageCoursePage().getStartFromDate().fill("05/09/2026");
        pages.manageCoursePage().getEndsONDate().fill("07/14/2026");

        PlaywrightAssertions.assertThat(pages.manageCoursePage().getPermanentCheckbox()).not().isChecked();

        pages.manageCoursePage().getSelectCategory().click();
        pages.manageCoursePage().getCategoryList().nth(4).click();

        pages.manageCoursePage().addThumbnail().setInputFiles(Path.of(System.getProperty("user.dir")+"/files/sample-clouds.png"));

        pages.manageCoursePage().getSaveButton().click();

        //Validate Course is added
        PlaywrightAssertions.assertThat(pages.manageCoursePage().coursesNames().nth(0)).containsText("Test Course");

        //Delete Course Just Added
        pages.manageCoursePage().deleteCoursesButtons().nth(0).click();

        //Validate Course is deleted
        PlaywrightAssertions.assertThat(pages.manageCoursePage().coursesNames().nth(0)).not().containsText("Test Course");

        //Log Out
        pages.navMenu().navigationMenu("logout");

        //Validate Sign Button is displayed
        PlaywrightAssertions.assertThat(pages.loginPage().signInButton()).isVisible();


    }
}
