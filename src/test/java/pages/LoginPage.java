package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    Logger log = LogManager.getLogger(this.getClass());

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Login page

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement Login;

    public void enterUsername(String id) {
        log.info("entering firstname: " + id);
        username.sendKeys(id);
    }

    public void enterPassword(String id) {
        log.info("entering password: " + id);
        password.sendKeys(id);
    }

    public void clickLoginButton() {
        log.info("clicking the login button");
        Login.click();
    }
}
