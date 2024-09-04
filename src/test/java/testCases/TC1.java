package testCases;

import BrowserFactory.BrowserFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ReportManager;


public class TC1 extends BrowserFactory {

    @Test(groups = "Sanity", description = "Login Functionality")
    public void testcase_01() throws Exception {
        LoginPage l = new LoginPage(BrowserFactory.getDriver());
        ReportManager.logInfo("Entering the usename");
        l.enterUsername("standard_user");
        ReportManager.logInfo("Entering the password");
        l.enterPassword("secret_sauce");
        ReportManager.logInfo("Click on Login button successfully");
        l.clickLoginButton();

        Boolean expected = true;
        Boolean actual = getDriver().findElement(By.xpath("//div[@class='product_label']")).isDisplayed();
        Assert.assertEquals(actual, expected, "Login Successful");
    }
}
