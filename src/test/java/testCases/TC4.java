//package testCases;
//
//import BrowserFactory.BrowserFactory;
//import org.openqa.selenium.By;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import pages.CheckoutPage;
//import utilities.ExcelTReader;
//import utilities.ReportManager;
//
//
//public class TC4 extends BrowserFactory {
//
//
//    @Test(groups = "smoke", description = "Finishing the order")
//    public void testcase_04() throws Exception {
//        TC3 tc3 = new TC3();
//        tc3.testcase_03();
//
//        String filePath = "./src/test/resources/Testdata/Book1.xlsx";
//        String sheetName = "Sheet1";
//
//        ExcelTReader excel = new ExcelTReader(filePath, sheetName);
//        String firstname = excel.getCellData(1, 0);
//        String lastname = excel.getCellData(1, 1);
//        String postalcode = excel.getCellData(1, 2);
//
//        CheckoutPage cp = new CheckoutPage(BrowserFactory.getDriver());
//        ReportManager.logInfo("Entering the firstname");
//        cp.firstname("Alekhya");
//        ReportManager.logInfo("Entering the Lastname");
//        cp.lastname("Namburi");
//        ReportManager.logInfo("Entering the postalcode");
//        cp.postalcode("501301");
//        ReportManager.logInfo("Clicking on continue button ");
//        cp.continueButton();
//        ReportManager.logInfo("Finish the process");
//        cp.finish();
//        Boolean expected = true;
//        Boolean actual = getDriver().findElement(By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]")).isDisplayed();
//        Assert.assertEquals(actual, expected, "Finished checkout Successfully");
//    }
//}

package testCases;

import BrowserFactory.BrowserFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import utilities.ExcelTReader;
import utilities.ReportManager;

import java.io.IOException;

public class TC4 extends BrowserFactory {

    @DataProvider(name = "TC4")
    public Object[][] excelDataProvider() {
        String filePath = "D:\\seleniumAutomation\\SeleniumFramework\\src\\test\\resources\\Testdata\\Book1.xlsx";
        String sheetName = "Sheet1";
        try {
            ExcelTReader excel = new ExcelTReader(filePath, sheetName);
            Object[][] data = excel.getData();
            excel.close();
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file: " + e.getMessage());
        }
    }


    @Test(dataProvider = "TC4", groups = "smoke", description = "Finishing the order")
    public void testcase_04(String firstname, String lastname, String postalcode) throws Exception {
        TC3 tc3 = new TC3();
        tc3.testcase_03();

        CheckoutPage cp = new CheckoutPage(BrowserFactory.getDriver());
        ReportManager.logInfo("Entering the firstname");
        cp.firstname(firstname);
        ReportManager.logInfo("Entering the Lastname");
        cp.lastname(lastname);
        ReportManager.logInfo("Entering the postalcode");
        cp.postalcode(postalcode);
        ReportManager.logInfo("Clicking on continue button");
        cp.continueButton();
        ReportManager.logInfo("Finish the process");
        cp.finish();
        Boolean expected = true;
        Boolean actual = getDriver().findElement(By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]")).isDisplayed();
        Assert.assertEquals(actual, expected, "Finished checkout Successfully");
    }
}

