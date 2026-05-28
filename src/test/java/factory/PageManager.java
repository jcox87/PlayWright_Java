package factory;

import com.microsoft.playwright.Page;
import pages.LoginPage;
import pages.ManageCoursePage;
import pages.NavigationMenu;
import pages.SignUpPage;

public class PageManager {

    private final Page page;

    public PageManager(Page page) {
        this.page = page;
    }

    public LoginPage loginPage(){
        return new LoginPage(page);
    }

    public NavigationMenu navMenu(){
        return new NavigationMenu(page);
    }

    public SignUpPage signUpPage(){
        return new SignUpPage(page);
    }

    public ManageCoursePage manageCoursePage(){
        return new ManageCoursePage(page);
    }
}
