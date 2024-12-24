package utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {

    public static String screenshotPath;
    public static String screenshotName;

    public static void hardWait(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
            // pass
        }
    }

    public static void hardWait(){
        hardWait(3000);
    }

    public static void captureScreenshot(WebDriver driver, String path, String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Date d = new Date();
        screenshotName = fileName + ".jpg";
        screenshotPath = path + File.separator + screenshotName;

        try {
            File finalDestination = new File(screenshotPath);
            FileUtils.copyFile(scrFile, finalDestination);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
