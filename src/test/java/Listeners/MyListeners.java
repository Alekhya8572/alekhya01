package Listeners;

import BrowserFactory.BrowserFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ReportManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyListeners implements ITestListener, IRetryAnalyzer {

    static int count = 0;
    private static final int maxRetryCount = 1;
    private static final String FILE_NAME = "./src/test/resources/FailedTC.xlsx";

    private static String testType;
    ExtentReports extentReports;

    @Override
    public void onStart(ITestContext context) {
        String parallelMode = context.getCurrentXmlTest().getParallel().toString();
        if (parallelMode.equalsIgnoreCase("none")) {
            testType = "Sequential";
        } else if (parallelMode.equalsIgnoreCase("methods") || parallelMode.equalsIgnoreCase("tests") || parallelMode.equalsIgnoreCase("classes")) {
            testType = "Parallel";
        } else {
            testType = "Unknown";
        }

        System.out.println("Test execution type: " + testType);

        ReportManager.initializeReport(context);
    }

    @Override
    public void onTestStart(ITestResult result) {

        String browserName = result.getTestContext().getCurrentXmlTest().getParameter("browser");

        String testDescription = result.getMethod().getDescription();



        String[] groups = result.getMethod().getGroups();
        String assignCategory = String.join(", ", groups);

        ExtentTest extentTest = ReportManager.createTest(result.getMethod().getMethodName(), testDescription);
        extentTest.assignCategory(assignCategory);
        extentTest.assignDevice(browserName);

        ReportManager.setTest(extentTest);

        ReportManager.extentReports().setSystemInfo("Test Execution Type", testType);

        System.out.println("Test case started: " + result.getMethod().getMethodName() + " on Browser: " + browserName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = ReportManager.getTest();
        if (extentTest != null) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed: " + result.getMethod().getMethodName(), ExtentColor.GREEN));
        } else {
            System.out.println("ExtentTest is null.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = ReportManager.getTest();
        extentTest.fail(result.getThrowable());
        ReportManager.attachScreenshot(BrowserFactory.getDriver());

        Workbook workbook;
        Sheet sheet;
        final String FILE_NAME = "./src/test/resources/FailedTC.xlsx";
        File file = new File(FILE_NAME);

        try {
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                }
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Failed Tests");
                Row firstRow = sheet.createRow(0);
                firstRow.createCell(0).setCellValue("TEST NAME");
                firstRow.createCell(1).setCellValue("REASON");
            }

            sheet = workbook.getSheet("Failed Tests");
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row failedTestRow = sheet.createRow(rowCount);
            failedTestRow.createCell(0).setCellValue(result.getTestClass().getName());
            failedTestRow.createCell(1).setCellValue(result.getThrowable().getMessage());

            try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetryCount) {
            count++;
            return true;
        }
        return false;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = ReportManager.getTest();
        if (extentTest != null) {
            extentTest.skip("Test Case Skipped: " + result.getMethod().getMethodName());
        } else {
            System.out.println("ExtentTest is null.");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.flushReports();
        WebDriver driver = BrowserFactory.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
