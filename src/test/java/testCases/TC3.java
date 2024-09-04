package testCases;

import BrowserFactory.BrowserFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import utilities.ReportManager;
import utilities.Screenshots;

public class TC3 extends BrowserFactory {

    @Test(groups = "smoke", description = "Navigating to checkout page")
    public void testcase_03() throws Exception {
        TC2 tc2 = new TC2();
        tc2.testcase_02();
        CartPage c = new CartPage(BrowserFactory.getDriver());
        ReportManager.logInfo("Navigating to checkout page successfully");
        c.clickCheckout();
        Boolean expected = true;
        Boolean actual = getDriver().findElement(By.xpath("//div[contains(text(),'Checkout: Your Information')]")).isDisplayed();
        Assert.assertEquals(actual, expected, "Your information page has been opened");

    }
}
