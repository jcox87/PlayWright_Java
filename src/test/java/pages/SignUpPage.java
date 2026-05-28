package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Step;
import net.datafaker.Faker;

import java.util.Random;

public class SignUpPage{

        private Page page;

        private Locator nameBox;
        private Locator emailBox;
        private Locator password;
        private Locator allInterests;
        private Locator femaleRadioButton;
        private Locator maleRadioButton;
        private Locator statePicker;
        private Locator hobbies;
        private Locator signUpSubmitButton;

    public SignUpPage(Page page) {
            this.page = page;

        nameBox = page.getByPlaceholder("Name");
        emailBox = page.getByPlaceholder("Email");
        password = page.getByPlaceholder("Password");
        allInterests = page.locator("//div[@class='interests-div']//label");
        femaleRadioButton = page.locator("xpath=//input[@value='Female']");
        maleRadioButton = page.locator("xpath=//input[@value='Male']");
        statePicker = page.locator("#state");
        hobbies = page.locator("#hobbies");
        signUpSubmitButton = page.locator(".submit-btn");


     }

    // Getter locator for assertions
    public Locator nameBox() { return nameBox; }
    public Locator emailBox() { return emailBox; }
    public Locator password() { return password; }
    public Locator femaleRadioButton() { return femaleRadioButton; }
    public Locator statePicker() { return statePicker; }
    public Locator hobbies() { return hobbies; }
    public Locator signUpSubmitButton() { return signUpSubmitButton; }


    //Method to Register New User
    @Step("Register new user form")
    public void RegisterNewUser(){
        Faker faker = new Faker();

        nameBox.fill(faker.name().firstName());
        emailBox.fill(faker.name().firstName()+"_"+faker.name().lastName()+"@email.com");
        password.fill("email@123fds");

        //Get all Interest
        int count = allInterests.count();
        String[] interestArray = new String[count];
        for(int i=0; i < count; i++){
            interestArray[i] = allInterests.nth(i).innerText().trim();
        }

        // Pick a random interest
        Random random = new Random();
        int randomIndex = random.nextInt(count);
        String randomInterest = interestArray[randomIndex];

        //Select Interest
        page.locator("//label[text()='"+randomInterest+"']//preceding::input[1]").click();
        System.out.println("Interests selected: "+ randomInterest);

        //Select Gender
        maleRadioButton.click();

        int stateCount = page.locator("//select[@name='state']//option").count();
        String[] statesArray = new String[stateCount - 1];
        for(int i = 1; i < stateCount; i++){
            statesArray[i - 1] = page.locator("//select[@name='state']//option").nth(i).innerText().trim();
        }

        // Pick a random State
        Random random2 = new Random();
        int randomStateIndex = random2.nextInt(stateCount);
        String randomState = statesArray[randomStateIndex];

        //Select State
        statePicker.selectOption(randomState);
        System.out.println("State Selected: "+randomState);

        //Select Hobbies
        String hobbies[] ={"Playing","Swimming"};
        page.locator("#hobbies").selectOption(hobbies);

        //Validate Sign Up Button is Enable and click it
        PlaywrightAssertions.assertThat(signUpSubmitButton).isEnabled();
        signUpSubmitButton.click();

    }

}