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

       //Start Tracing
       context.tracing().start(
               new Tracing.StartOptions()
                       .setScreenshots(true)
                       .setSnapshots(true)
                       .setSources(true)
       );

       page = context.newPage();
       pages = new PageManager(page);
   }

   @AfterMethod
   public void teardown(ITestResult result){

       boolean failed = result.getStatus() == ITestResult.FAILURE;

       // Screenshot on failure
       if (failed) {
           AllureAttachments.attachScreenshot(page, result.getName() + "_failure");
       }

       // Stop tracing BEFORE closing context/page
       if (failed) {
           Path tracePath = Paths.get("traces/" + result.getName() + "_trace.zip");
           context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
           AllureAttachments.attachTrace(tracePath);
       } else {
           context.tracing().stop();
       }

       // Close page (this finalizes video)
       page.close();

       // Attach video on failure
       if (failed) {
           Path videoPath = page.video().path();
           AllureAttachments.attachVideo(videoPath);
       }

       // Now safe to close context + browser
       context.close();
       browser.close();
   }
}
