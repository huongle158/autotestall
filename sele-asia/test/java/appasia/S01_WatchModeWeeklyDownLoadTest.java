package appasia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mongodb.util.Util;

import common.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CalendarPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ReadingChartPage;
import utils.BasicTest;
import utils.Utils;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static pages.CalendarPage.getFirstDateOfLastWeek;

public class S01_WatchModeWeeklyDownLoadTest {
    LoginPage loginPage;
    HomePage homePage;
    CalendarPage calendarPage;
    ReadingChartPage readingChartPage;

    Calendar cal = Calendar.getInstance();

    public String cYear = String.valueOf((cal.getTime().getYear() + 1900));

    int monthIndex = calendarPage.getFirstDateOfLastWeek(cal.getTime()).getMonth();
    public String cMonth = calendarPage.getMonth(monthIndex);



    public String cDate = String.valueOf(getFirstDateOfLastWeek(cal.getTime()).getDate()); // first day last week
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

    @Test(dataProvider = "testdata")
    public void e2eAsiaWatchMode(int checkPage) throws InterruptedException {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        readingChartPage = new ReadingChartPage(driver);
        calendarPage = new CalendarPage(driver);

        loginPage.openLoginPage("https://app-asia.oceaview.com/login");
        homePage = loginPage.loginToPage();
        Thread.sleep(3000);
        
        // int screenshotNumber = 0;
        WebElement element = driver.findElement(By.xpath("//p-paginator//button[contains(@class,'p-paginator-next')]"));
        int counter = 0;
        boolean finishPage = false;
        while (!finishPage){
            String highlightPage = driver.findElement(By.xpath("//span[contains(@class,'p-paginator-pages')]/button[contains(@class,'p-highlight')]")).getText();
            if (highlightPage.equals(String.valueOf(checkPage))){
                // Download PDFs
                int totalDevices = driver.findElements(By.xpath("//div[@class='content']//mtv-watch-mode-sensors-tile-overview")).size();
                for (int i = 0; i < totalDevices; i++) {
                    counter++;
                    // if (counter == Constants.MaxItemPerRun) {return;}
                    homePage.selectContainer(i);
                    String device = homePage.getDevice();
                    calendarPage.selectStartDate();
                    Thread.sleep(3000);
                    calendarPage.selectStopDate();
                    Thread.sleep(3000);
                    calendarPage.downloadPDFFile();
                    // Assert.assertTrue(readingChartPage.isFileAvailable(downloadPath, FILE_NAME), "Downloaded document is not found");
                    Awaitility.await()
                        .atMost(30, TimeUnit.SECONDS)
                        .until(() -> readingChartPage.isFileAvailable(downloadPath, FILE_NAME));

                    Thread.sleep(3000);
                    readingChartPage.renameFileAfterDownload(downloadPath, FILE_NAME, device);
                    Thread.sleep(3000);
                    readingChartPage.clickCloseModal();
                    Thread.sleep(3000);
                    readingChartPage.clickCloseChartModel();
                    Thread.sleep(1000);
                }
                finishPage = true;
                Thread.sleep(3000);
            }  
            
            if (!element.isEnabled()){
                finishPage = true;
            } else {
                element.click();
                Thread.sleep(3000);
            }

        }
    }

    @DataProvider(name = "testdata")
    public Object[][] TestDataFeed() {
        //Create object array  n rows, 1 columns
        Object[][] testData = new Object[Constants.Pages.size()][1];

        for (int i=0; i < Constants.Pages.size(); i++) {
            testData[i][0] = Constants.Pages.get(i);
        }

        return testData;
    }

    @AfterMethod
    public void postCondition(){
        // Quit the Browser
        driver.quit();
    }

}
