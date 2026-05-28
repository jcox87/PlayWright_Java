package base;

import com.microsoft.playwright.*;
import factory.PageManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.AllureAttachments;

import java.nio.file.Path;
import java.nio.file.Paths;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class BaseTest {

   protected Browser browser;
   protected BrowserContext context;
   protected Page page;
   protected PageManager pages;

   @BeforeMethod
   public void setup(){
       Playwright playwright = Playwright.create();
//       browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
       browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(1000));
       context = browser.
               newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")).setRecordVideoSize(800,600));
       page = context.newPage();
       pages = new PageManager(page);
   }

   @AfterMethod
   public void teardown(ITestResult result){

       // Take screenshot on failure
       if (ITestResult.FAILURE == result.getStatus()) {
           AllureAttachments.attachScreenshot(page, result.getName() + "_failure");
       }

       // Close page first so video is finalized
       page.close();

       // Attach video on failure
       if (ITestResult.FAILURE == result.getStatus()) {
           Path videoPath = page.video().path();
           AllureAttachments.attachVideo(videoPath);
       }

       context.close();
       browser.close();
   }
}
