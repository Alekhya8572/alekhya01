package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    Logger log = LogManager.getLogger(this.getClass());
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //Your cart
    @FindBy(xpath = "//a[contains(text(),'CHECKOUT')]")
    private WebElement clickCheckout;

    public void clickCheckout() {
        log.info("Clicking on checkout button");
        clickCheckout.click();
    }

}
