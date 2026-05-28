package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ManageCoursePage {

    private Page page;

    private Locator addNewCourseButton;

    private Locator coursesNames;

    private Locator deleteCoursesButtons;

    //Add New Course Form
    private Locator addThumbnail;
    private Locator addNewCourseName;
    private Locator addDescription;
    private Locator addInstructorName;
    private Locator addPrice;
    private Locator startFromDate;
    private Locator endsONDate;
    private Locator permanentCheckbox;
    private Locator selectCategory;
    private Locator categoryList;
    private Locator cancelButton;
    private Locator saveButton;



    public ManageCoursePage(Page page){
        this.page = page;

        addNewCourseButton =  page.getByAltText("add");

        coursesNames = page.locator("//tr[contains(@id,'course')]//td[2]");

        deleteCoursesButtons = page.locator("//button[text()='Delete']");

        addThumbnail = page.locator("#thumbnail");

        addNewCourseName = page.locator("#name");

        addDescription = page.locator("#description");

        addInstructorName = page.locator("#instructorNameId");

        addPrice = page.locator("#price");

        startFromDate = page.locator("//input[@name='startDate']");

        endsONDate = page.locator("//input[@name='endDate']");

        permanentCheckbox = page.locator("#isPermanent");

        selectCategory = page.getByAltText("select category");

        categoryList = page.locator("//button[@class='menu-item']");

        cancelButton = page.getByText("Cancel");

        saveButton = page.getByText("Save");

    }

    // Getter locator for assertions
    public Locator addNewCourseButton() { return addNewCourseButton; }

    public Locator coursesNames() { return coursesNames; }

    public Locator deleteCoursesButtons() { return deleteCoursesButtons; }

    public Locator addThumbnail() { return addThumbnail; }

    public Locator getAddNewCourseName() { return addNewCourseName; }

    public Locator getAddDescription() { return addDescription; }

    public Locator getAddInstructorName() {
        return addInstructorName;
    }

    public Locator getAddPrice() {
        return addPrice;
    }

    public Locator getStartFromDate() {
        return startFromDate;
    }

    public Locator getEndsONDate() {
        return endsONDate;
    }

    public Locator getPermanentCheckbox() {
        return permanentCheckbox;
    }

    public Locator getSelectCategory() {
        return selectCategory;
    }

    public Locator getCategoryList() {
        return categoryList;
    }

    public Locator getCancelButton() {
        return cancelButton;
    }

    public Locator getSaveButton() {
        return saveButton;
    }
}
