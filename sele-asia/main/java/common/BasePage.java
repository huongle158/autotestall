package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openUrl(String url) {
        driver.get(url);
    }
    public void waitElementToBeClickAble(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementToBeDisplayed(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(element));
    }
    public void clickAfterWaiting(WebElement element) {
        waitElementToBeClickAble(element);
        element.click();
    }


    public void sendKeysToElement(WebElement element, String txt) {
        waitElementToBeDisplayed(element);
        element.sendKeys(txt);
    }

    public String getDevice() {

        String type = "Humidity"; 
        try {
            if (temp.isDisplayed()) {
                type = "Temperature";
            }
        } catch (Exception e) {
                // Do nothing
        }
        return deviceID.getText() + "_" + type;
    }

    public boolean isFileAvailable(String downloadPath, String expectedFileName){
        File folder = new File(downloadPath);
        File[] listOfFiles = folder.listFiles();
        boolean isFileAvailable = false;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.startsWith(expectedFileName)) {
                    // System.out.println("File: " + fileName);
                    isFileAvailable = true;
                }
            }
        }
        return isFileAvailable;
    }

    public void renameFileAfterDownload(String downloadPath, String rawName, String deviceName){
        File folder = new File(downloadPath);
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    String fileName = listOfFiles[i].getName();

                    if (fileName.startsWith(rawName) && !fileName.endsWith(".crdownload")) {
                        String newName = deviceName + "_" + fileName;
                        listOfFiles[i].renameTo(new File(downloadPath, newName));
                    }
                    if (fileName.endsWith(".crdownload") && fileName.startsWith(rawName)) {
                        System.out.println("fileName: " + fileName);
                        String newName = deviceName + "_" + fileName.substring(fileName.lastIndexOf(".") + 1);
                        listOfFiles[i].renameTo(new File(downloadPath, newName));
                    }

                }
            }
        }
    }

    public void deleteExistingFile(String downloadPath) {
        File folder = new File(downloadPath);
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            listOfFile.delete();
        }
    }

    public void deleteFilesAfterDownload(String pathName) {
        File path = new File(pathName);
        File[] files = path.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    public void clickByJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void moveAndClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click(element).build().perform();
    }

    @FindBy(xpath = "//div[contains(@class, 'name-container')]//span[contains(@class, 'font-weight-bold')]")
    private List<WebElement> ListDeviceID;

    @FindBy(xpath="//div[@class=\"name-container\"]//span[contains(@class, 'equipment-name')]")
    private WebElement deviceID;

    @FindBy(xpath="//div[@class='detail visible']//span[contains(@class, 'thermometer')]")
    private WebElement temp;
}
