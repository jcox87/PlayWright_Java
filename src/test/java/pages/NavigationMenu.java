package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class NavigationMenu {

    private Page page;

    private Locator menuIconButton;
    private Locator homeButton;
    private Locator practiceButton;
    private Locator loginButton;
    private Locator logoutButton;
    private Locator closeButton;

    private Locator cartButton;

    private Locator manageButton;
    private Locator manageCourseButton;
    private Locator manageCategoriesButton;

    public NavigationMenu(Page page) {
        this.page = page;

        //Nav Side Menu
        menuIconButton = page.getByAltText("menu");
        homeButton = page.locator("//a[@class='nav-menu-item']//div[text()='Home']");
        practiceButton = page.locator("//a[@class='nav-menu-item']//div[text()='Practise']");
        loginButton = page.locator("//button[text()='Log in']");
        logoutButton = page.locator("//button[text()='Sign out']");
        closeButton = page.getByAltText("delete");

        //Cart
        cartButton = page.locator(".cartBtn");

        //Manage
        manageButton = page.locator(".nav-menu-item-manage");
        manageCourseButton = page.getByAltText("manage course");
        manageCategoriesButton = page.getByAltText("manage category");

    }

    // Getter locator for assertions
    public Locator menuIconButton() { return menuIconButton; }
    public Locator homeButton() { return homeButton; }
    public Locator practiceButton() { return practiceButton; }
    public Locator loginButton() { return loginButton; }
    public Locator logoutButton() { return logoutButton; }
    public Locator closeButton() { return closeButton; }

    public Locator cartButton() { return cartButton; }

    public Locator manageButton() { return manageButton; }
    public Locator manageCourseButton() { return manageCourseButton; }
    public Locator manageCategoriesButton() { return manageCategoriesButton; }

    //Method to Handle Navigation Menu
    @Step("Navigation menu")
    public void navigationMenu(String menu){

        //Open Menu
        menuIconButton.click();

        switch(menu.toUpperCase()){

            case "HOME":
                homeButton.click();
                break;

            case "PRACTICE":
                practiceButton.click();
                break;

            case "LOGIN":
                loginButton.click();
                break;

            case "LOGOUT":
                logoutButton.click();
                break;

            default:
                System.out.println("Invalid");

        }
        System.out.println("User click the "+menu+" Button");

    }
}
