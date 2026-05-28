package utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureAttachments {

    public static void attachScreenshot(Page page, String name) {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        Allure.getLifecycle().addAttachment(
                name,
                "image/png",
                "png",
                screenshot
        );
    }

    public static void attachVideo(Path videoPath) {
        try {
            Allure.getLifecycle().addAttachment(
                    "Test Video",
                    "video/webm",
                    "webm",
                    Files.readAllBytes(videoPath)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
