package testCases;

import BrowserFactory.BrowserFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;
import utilities.ReportManager;
import utilities.Screenshots;


public class TC2 extends BrowserFactory {

    @Test(groups = "Regression", description = "Adding Products to cart")
    public void testcase_02() throws Exception {
        TC1 tc1 = new TC1();
        tc1.testcase_01();
        ProductsPage p = new ProductsPage(BrowserFactory.getDriver());
        ReportManager.logInfo("Selecting the required option from dropdown");
        p.selectFromDropdownByIndex(2);
        ReportManager.logInfo("Add the product_1 to the cart");
        p.clickToAddTocart_1();
        ReportManager.logInfo("Add the product_2 to the cart");
        p.clickToAddTocart_2();
        ReportManager.logInfo("Navigating to the cart page successfully");
        p.clickToCart();
        Boolean expected =  true;
        Boolean actual = getDriver().findElement(By.xpath("//div[contains(text(),'Your Cart')]")).isDisplayed();
        Assert.assertEquals(actual,expected,"Products added to cart successfully");
    }

}