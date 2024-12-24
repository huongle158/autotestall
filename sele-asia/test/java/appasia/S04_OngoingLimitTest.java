package appasia;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.LoginPage;
import pages.ReadingChartPage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class S04_OngoingLimitTest {
    // WebDriver driver;
    // LoginPage loginPage;
    // HomePage homePage;
    // ReadingChartPage readingChartPage;
    // private final String downloadPath = System.getProperty("user.dir") + File.separator + "src/test/resources/download";
    // private final String FILE_NAME = "Readings";


    // @BeforeClass
    // public void setup() {
    //     WebDriverManager.chromedriver().setup();
    //     ChromeOptions options = new ChromeOptions();
    //     Map<String, Object> prefs = new HashMap<>();
    //     prefs.put("download.default_directory", downloadPath);
    //     options.setExperimentalOption("prefs", prefs);
    //     driver = new ChromeDriver(options);
    //     driver.manage().window().maximize();

    //     loginPage = new LoginPage(driver);
    //     homePage = new HomePage(driver);
    //     readingChartPage = new ReadingChartPage(driver);
    // }

    // @Test
    // public void e2eAsiaOnGoingLimit() throws InterruptedException {
    //     loginPage.openLoginPage("https://app-asia.oceaview.com/login");
    //     homePage = loginPage.loginToPage();
    //     homePage.clickPresentationIcon();
    //     homePage.clickFilters();
    //     homePage.inputStartDate("01/03/2024 12:00 AM");
    //     homePage.inputToEndDate("01/03/2024 11:59 PM");
    //     homePage.clickFilterSearchBtn();
    //     Thread.sleep(5000);
    //     for (int i = 1; i < homePage.getToggleSize() + 1; i++) {
    //         homePage.openReadingChart(String.valueOf(i));
    //         Thread.sleep(5000);
    //         readingChartPage.changeStartDate("23");
    //         readingChartPage.clickSearchIcon();
    //         readingChartPage.clickDownloadPdf();
    //         Assert.assertTrue(readingChartPage.isFileAvailable(downloadPath, FILE_NAME), "Downloaded document is not found");
    //         readingChartPage.clickCloseModal();
    //         homePage.clickToggleIcon(String.valueOf(i));
    //     }
    // }

    // @AfterClass(alwaysRun = true)
    // public void tearDown() {
    //     driver.quit();
    // }
}
