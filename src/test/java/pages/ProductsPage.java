package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage {
    Logger log = LogManager.getLogger(this.getClass());
    public ProductsPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(className = "product_sort_container")
    private WebElement selectFromDropdown;

    @FindBy(xpath = "(//button[contains(text(),'ADD TO CART')])[1]")
    private WebElement clickToAddTocart_1;

    @FindBy(xpath = "(//button[contains(text(),'ADD TO CART')])[2]")
    private WebElement clickToAddTocart_2;

    @FindBy(id = "shopping_cart_container")
    private WebElement clickToCart;

    public void selectFromDropdownByIndex(int index){
        Select dropdown = new Select(selectFromDropdown);
        dropdown.selectByIndex(2);
    }

    public void clickToAddTocart_1() {
        log.info("Adding product1 to cart");
        clickToAddTocart_1.click();
    }
    public void clickToAddTocart_2(){
        log.info("Adding product2 to cart");
        clickToAddTocart_2.click();
    }
    public void clickToCart() {
        log.info("Click on the cart");
        clickToCart.click();
    }
}
