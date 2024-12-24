package appasia;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.util.Util;

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

import static pages.CalendarPage.getFirstDateOfWeek;

public class S00_TestTest extends BasicTest {
    LoginPage loginPage;
    HomePage homePage;
    CalendarPage calendarPage;
    ReadingChartPage readingChartPage;

    @Test
    public void e2eAsiaWatchMode() throws InterruptedException {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        readingChartPage = new ReadingChartPage(driver);
        calendarPage = new CalendarPage(driver);

        loginPage.openLoginPage("https://app-asia.oceaview.com/login");
        homePage = loginPage.loginToPage();
        Thread.sleep(3000);
        
       
    }

}
