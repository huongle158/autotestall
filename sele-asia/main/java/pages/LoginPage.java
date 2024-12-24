package pages;


import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id="userName")
    private WebElement username;
    @FindBy(id="password")
    private WebElement password;
    @FindBy(xpath="//button[@type='submit']")
    private WebElement logibBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage(String url) {
        openUrl(url);
    }
    public HomePage loginToPage() {
        sendKeysToElement(username,"derek_qrt_cordlife@qrt-services.com");
        sendKeysToElement(password,"1qaz2wsx#EDC");
        clickAfterWaiting(logibBtn);
        return new HomePage(driver);
    }



}
