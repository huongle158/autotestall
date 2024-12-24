package pages;


import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomePage extends BasePage {

    @FindBy(id = "alarms")
    private WebElement presentationIcon;
    @FindBy(id = "p-accordiontab-1")
    private WebElement filterIcon;
    @FindBy(xpath = "//label[@for='minDate']//parent::mtv-form-control-label/following-sibling::mtv-date-time-picker//input")
    private WebElement startDate;
    @FindBy(xpath = "//label[@for='maxDate']//parent::mtv-form-control-label/following-sibling::mtv-date-time-picker//input")
    private WebElement endDate;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement filterSearchButton;
    private static final String TOGGLE_ICON = "//tbody//tr[%s]//td[@class='row-toggler']//i";
    @FindBy(xpath = "//tbody//tr//td[@class='row-toggler']//i")
    private List<WebElement> toggleSize;
    @FindBy(xpath = "//span[contains(@class,'mdi-chart-line')]")
    private WebElement readingChart;

    @FindBy(xpath = "//div[@class='content']//mtv-watch-mode-sensors-tile-overview")
    List<WebElement> listContainer;

    @FindBy(xpath = "//div[@class='graph-container-overlay']")
    WebElement cardGraph;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickPresentationIcon() {
        clickAfterWaiting(presentationIcon);
    }

    public void clickFilters() {
        clickAfterWaiting(filterIcon);
    }

    public void inputStartDate(String value) {
        sendKeysToElement(startDate, value);
        startDate.sendKeys(Keys.ENTER);
    }

    public void inputToEndDate(String value) {
        sendKeysToElement(endDate, value);
        endDate.sendKeys(Keys.ENTER);
    }

    public void clickFilterSearchBtn() {
        clickAfterWaiting(filterSearchButton);
    }

    public void clickToggleIcon(String index) {
        waitElementToBeClickAble(driver.findElement(By.xpath(String.format(TOGGLE_ICON, index))));
        driver.findElement(By.xpath(String.format(TOGGLE_ICON, index))).click();
    }

    public void openReadingChart(String index) {
        clickToggleIcon(index);
        clickAfterWaiting(readingChart);
    }

    public int getToggleSize() {
      return  toggleSize.size();
    }

    public void selectContainer(int i){
        try {
            waitElementToBeDisplayed(listContainer.get(i));
            clickAfterWaiting(listContainer.get(i));
            waitElementToBeDisplayed(cardGraph);
            clickAfterWaiting(cardGraph);
        } catch (Exception e) {
            System.out.println("No device available to select");
        }
    }
}
