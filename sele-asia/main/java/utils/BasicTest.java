package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CalendarPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ReadingChartPage;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import static pages.CalendarPage.getFirstDateOfWeek;

public abstract class BasicTest {
    
    Calendar cal = Calendar.getInstance();
    public String cYear = String.valueOf((getFirstDateOfWeek(cal.getTime()).getYear() + 1900));
    public String cMonth = CalendarPage.getMonth(getFirstDateOfWeek(cal.getTime()).getMonth());
    // public String cDate = String.valueOf(getFirstDateOfWeek(cal.getTime()).getDate());
    public String cDate = String.valueOf(cal.get(Calendar.DATE));
    public static final Logger logger = LogManager.getLogger();
    public static WebDriver driver;
    public final String downloadPath = System.getProperty("user.dir") + File.separator + "Reports"
                                        + File.separator + cYear + File.separator + cMonth + File.separator + cDate;
    public final String monthPath = System.getProperty("user.dir") + File.separator + "Reports"
                                        + File.separator + cYear + File.separator + cMonth;
    
    public final String FILE_NAME = "Readings";

    @BeforeMethod
    public void preCondition() {
        WebDriverManager.edgedriver().setup();
        // ChromeOptions options = new ChromeOptions();
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.automatic_downloads",1);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("disable-popup-blocking", true);prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.automatic_downloads",1);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("disable-popup-blocking", true);
        options.setExperimentalOption("prefs", prefs);
        // options.addArguments("--incognito");
        options.addArguments("disable-features=DownloadBubble,DownloadBubbleV2");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void postCondition(){
        // Quit the Browser
        driver.quit();
    }
}