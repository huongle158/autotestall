package pages;

import java.util.Date;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class CalendarPage extends BasePage {

    public CalendarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'p-datepicker-title')]//button[1]")
    private WebElement currentMonth;

    @FindBy(xpath = "//div[contains(@class,'p-datepicker-title')]//button[2]")
    private WebElement currentYear;

    @FindBy(xpath = "//mtv-date-time-picker[@formcontrolname='minDate']//input")
    private WebElement startDateBtn;

    @FindBy(xpath = "//div[@role='dialog']//mtv-form-control-label[@fieldid='maxDate']//following-sibling::mtv-date-time-picker//input")
    private WebElement stopDateBtn;

    @FindBy(xpath = "//mtv-download-file/mtv-action-button/div/button")
    private WebElement downloadBtn;

    @FindBy(xpath = "//span[normalize-space()='PDF (.pdf)']")
    private WebElement PDCFile;

    @FindBy(xpath = "//button[contains(@class,'p-datepicker-next')]//chevronrighticon")
    private WebElement rightArrow;
    
    @FindBy(xpath = "//button[contains(@class,'p-datepicker-prev')]//chevronlefticon")
    private WebElement leftArrow;

    @FindBy(xpath = "//div[contains(@class,'p-hour-picker')]//span")
    private WebElement hour;

    @FindBy(xpath = "//div[contains(@class,'p-minute-picker')]//span")
    private WebElement minute;

    @FindBy(xpath = "//div[contains(@class,'p-ampm-picker')]//span")
    private WebElement AMPM;

    @FindBy(xpath = "//div[contains(@class,'p-hour-picker')]//button//chevronupicon")
    private WebElement selectHourBtn;

    @FindBy(xpath = "//div[contains(@class,'p-minute-picker')]//button//chevronupicon")
    private WebElement selectMinuteBtn;

    @FindBy(xpath = "//div[contains(@class,'p-ampm-picker')]//button//chevronupicon")
    private WebElement selectAMPMBtn;

    //get current day
    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    //get first day of current week
    public static Date getFirstDateOfWeek(Date date) {
        int d;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // cal.add(Calendar.DATE, -7);
        d = getDayNumberOld(date) - 2;
        if (d == -1) {
            cal.add(Calendar.DATE, -6);
        } else cal.add(Calendar.DATE, d * -1);
        // System.out.println(cal.getTime().toString());
        return cal.getTime();
    }

    //get first day of last week
    public static Date getFirstDateOfLastWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7); 
        return getFirstDateOfWeek(cal.getTime()); 
    }

    //get last date of last week
    public static Date getLastDateOfLastWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7); 
        return getLastDateOfWeek(cal.getTime()); 
    }

    //get last date of current week
    public static Date getLastDateOfWeek(Date date)
    {
        int d;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // cal.add(Calendar.DATE, -7);
        d = getDayNumberOld(date) - 1;
        if (d == 1) {
            cal.add(Calendar.DATE, 6);
        } else {
            cal.add(Calendar.DATE, d * -1);
            if (d != 0)
                cal.add(Calendar.DATE, 7);
        }
        // System.out.println(cal.getTime().toString());
        return cal.getTime();
    }

    //get last day of month
    public static Date getLastDateOfMonth(Date date) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        //cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static String getMonth(int month) {
        String monthString;
        switch (month) {
            case 0:
                monthString = "January";
                break;
            case 1:
                monthString = "February";
                break;
            case 2:
                monthString = "March";
                break;
            case 3:
                monthString = "April";
                break;
            case 4:
                monthString = "May";
                break;
            case 5:
                monthString = "June";
                break;
            case 6:
                monthString = "July";
                break;
            case 7:
                monthString = "August";
                break;
            case 8:
                monthString = "September";
                break;
            case 9:
                monthString = "October";
                break;
            case 10:
                monthString = "November";
                break;
            case 11:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        return monthString;
    }

    public void selectStartDate() throws InterruptedException {
        clickAfterWaiting(startDateBtn);
        Thread.sleep(3000);
        Calendar cal = Calendar.getInstance();
        String cMonth = getMonth(getFirstDateOfLastWeek(cal.getTime()).getMonth());
        String cYear = String.valueOf((getFirstDateOfLastWeek(cal.getTime()).getYear() + 1900));
        String cDate = String.valueOf(getFirstDateOfLastWeek(cal.getTime()).getDate());


        while (!(cMonth.equals(currentMonth.getText())) && (cYear.equals(currentYear.getText()))) {
            waitElementToBeClickAble(leftArrow);
            clickAfterWaiting(leftArrow);
            currentMonth.getText();
            currentYear.getText();
        }
        driver.findElement(By.xpath("//td /span[text()=" + cDate + "and not(contains(@class,'p-disabled'))]")).click();
    }

    private void selectMonthYear(String cMonth, String cYear)
    {
        waitElementToBeClickAble(leftArrow);
        clickAfterWaiting(leftArrow);
        //while (!(cMonth.equals(currentMonth.getText())) && (cYear.equals(currentYear.getText()))) {
        //    waitElementToBeClickAble(rightArrow);
        //    clickAfterWaiting(rightArrow);
        //    currentMonth.getText();
        //    currentYear.getText();
        //}
        while (!(hour.getText().equals("12"))) {
            selectHourBtn.click();
            hour.getText();
        }
        while (!(minute.getText().equals("00"))) {
            selectMinuteBtn.click();
            minute.getText();
        }
        while (!(AMPM.getText().equals("AM"))) {
            selectAMPMBtn.click();
            AMPM.getText();
        }
    }

    @SuppressWarnings("deprecation")
    public void selectStopDate() throws InterruptedException
    {
        clickAfterWaiting(stopDateBtn);
        Thread.sleep(3000);
        Calendar cal = Calendar.getInstance();
        String cMonth = getMonth(getLastDateOfLastWeek(cal.getTime()).getMonth());
        String cYear = String.valueOf((getFirstDateOfLastWeek(cal.getTime()).getYear() + 1900));
        String cDate = String.valueOf(getLastDateOfLastWeek(cal.getTime()).getDate());
        while (!(cMonth.equals(currentMonth.getText())) && (cYear.equals(currentYear.getText()))) {
            waitElementToBeClickAble(rightArrow);
            clickAfterWaiting(rightArrow);
            currentMonth.getText();
            currentYear.getText();
        }
        while (!(hour.getText().equals("11"))) {
            selectHourBtn.click();
            hour.getText();
        }
        while (!(minute.getText().equals("59"))) {
            selectMinuteBtn.click();
            minute.getText();
        }
        while (!(AMPM.getText().equals("PM"))) {
            selectAMPMBtn.click();
            AMPM.getText();
        }
        driver.findElement(By.xpath("//td /span[text()=" + cDate + "and not(contains(@class,'p-disabled'))]")).click();
    }

    //select start date of month
    @SuppressWarnings("deprecation")
    public void selectStartDateOfMonth() {
        waitElementToBeClickAble(startDateBtn);
        clickAfterWaiting(startDateBtn);
        Calendar cal = Calendar.getInstance();
        String cMonth = getMonth(getFirstDateOfWeek(cal.getTime()).getMonth());
        String cYear = String.valueOf((getFirstDateOfWeek(cal.getTime()).getYear() + 1900));
        selectMonthYear(cMonth, cYear);
        driver.findElement(By.xpath("//td /span[text()='1']")).click();
    }

    // get 
    public void selectStartDateOfLastMonth() {
        // TODO: ...
    }

    //select stop date of month
    @SuppressWarnings("deprecation")
    public void selectStopDateOfMonth() {
        waitElementToBeClickAble(stopDateBtn);
        clickAfterWaiting(stopDateBtn);
        Calendar cal = Calendar.getInstance();
        //String cMonth = getMonth(getFirstDateOfWeek(cal.getTime()).getMonth());
        //String cYear = String.valueOf((cal.getTime()).getYear() + 1900);
        String cDate = String.valueOf(getLastDateOfMonth(cal.getTime()).getDate());
        waitElementToBeClickAble(leftArrow);
        clickAfterWaiting(leftArrow);
        //while (!(cMonth.equals(currentMonth.getText())) && (cYear.equals(currentYear.getText()))) {
        //    waitElementToBeClickAble(rightArrow);
        //    clickAfterWaiting(rightArrow);
        //    currentMonth.getText();
        //    currentYear.getText();
        //}
        while (!(hour.getText().equals("11"))) {
            selectHourBtn.click();
            hour.getText();
        }
        while (!(minute.getText().equals("59"))) {
            selectMinuteBtn.click();
            minute.getText();
        }
        while (!(AMPM.getText().equals("PM"))) {
            selectAMPMBtn.click();
            AMPM.getText();
        }
        driver.findElement(By.xpath("//td /span[text()=" + cDate + "and not(contains(@class,'p-disabled'))]")).click();
    }

    public void downloadPDFFile() throws InterruptedException {
        waitElementToBeClickAble(downloadBtn);
        clickAfterWaiting(downloadBtn);
        Thread.sleep(1000);
        waitElementToBeClickAble(PDCFile);
        clickAfterWaiting(PDCFile);
        // Thread.sleep(15000);
        waitElementToBeDisplayed(downloadBtn);
    }
}
