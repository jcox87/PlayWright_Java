package pomtest;

import base.BaseTest;
import org.testng.annotations.Test;

public class PageObjectTest2 extends BaseTest {

    @Test
    public void login(){

        page.navigate("https://freelance-learn-automation.vercel.app/login");
        pages.loginPage().LogintoApplication("admin@email.com","admin@123");

    }


}
