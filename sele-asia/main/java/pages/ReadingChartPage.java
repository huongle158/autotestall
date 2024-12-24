package pages;


import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReadingChartPage extends BasePage {

    @FindBy(xpath="//mtv-date-time-picker[@formcontrolname='minDate']//span//input")
    private WebElement startDate;
    @FindBy(xpath="(//span[@class='p-button-icon fas fa-search'])[2]")
    private WebElement searchIcon;
    @FindBy(xpath="//div[@class='margin-left-6px ng-star-inserted']//span[@class='x1-3 font-weight-bold fas fa-download']")
    private WebElement downloadIcon;
    @FindBy(xpath="//span[normalize-space()='PDF (.pdf)']")
    private WebElement downloadPdf;
    @FindBy(css="button.p-dialog-header-icon")
    private WebElement closeIcon;
    @FindBy(tagName="chevronlefticon")
    private WebElement backIcon;
    private static final String DATE_TO_CLICK = "//table//td//span[text()='%s']";
    @FindBy(xpath="//mtv-form-control-label[@fieldid='minDate']//following-sibling::mtv-date-time-picker//button")
    private WebElement dateTimePickerBtn;
    @FindBy(xpath="//div[@role='dialog']//mtv-form-control-label[@fieldid='minDate']//following-sibling::mtv-date-time-picker//input")
    private WebElement startDateiput;

    @FindBy(xpath = "//span[contains(@class,'close-btn')]")
    private WebElement closeChartModelIcon;



    public ReadingChartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void changeStartDate(String expectedDate) throws InterruptedException {
        clickByJS(startDateiput);
        Thread.sleep(3000);
        clickBackIcon();
        Thread.sleep(5000);
        WebElement dateToClick = driver.findElement(By.xpath(String.format(DATE_TO_CLICK, expectedDate)));
        clickAfterWaiting(dateToClick);
        Thread.sleep(10000);
        startDate.sendKeys(Keys.ENTER);
    }

    public void clickSearchIcon() {
        clickAfterWaiting(searchIcon);
    }

    public void clickDownloadPdf() throws InterruptedException {
        clickAfterWaiting(downloadIcon);
        clickAfterWaiting(downloadPdf);
        Thread.sleep(7000);
    }

    public void clickCloseModal() {
        clickAfterWaiting(closeIcon);
    }

    public void clickCloseChartModel() {
        clickAfterWaiting(closeChartModelIcon);
    }

    public void clickBackIcon() {
        clickAfterWaiting(backIcon);
    }
}
