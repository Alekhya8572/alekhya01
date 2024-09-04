package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    Logger log = LogManager.getLogger(this.getClass());
    public CheckoutPage(WebDriver driver)
    {
        super(driver);
    }

    //checkout:Your Information
    @FindBy(id = "first-name")
    private WebElement firstname;

    @FindBy(id = "last-name")
    private WebElement lastname;

    @FindBy(id = "postal-code")
    private WebElement postalcode;

    @FindBy(xpath = "//input[@value='CONTINUE']")
    private WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(),'FINISH')]")
    private WebElement finish;

    public void firstname(String id) {
        log.info("entering firstname: " + id);
        firstname.sendKeys(id);
    }

    public void lastname(String id) {
        log.info("entering lastname: " + id);
        lastname.sendKeys(id);
    }
    public void postalcode(String id) {
        log.info("entering postalcode: " + id);
        postalcode.sendKeys(id);
    }
    public void continueButton(){
        log.info("clicking continue button");
        continueButton.click();
    }
    public void finish(){
        log.info("Finished successfully");
        finish.click();
    }
}
