package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ExtentSparkReporter sparkReporter;

    // Initialize the Extent Report
    public static void initializeReport(ITestContext testContext) {
//        if (extent == null) {
//            Date dt = new Date();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm");
//            String date = dateFormat.format(dt);
//
//            String reportPath = "./Reports/reportOn_" + date + ".html";
//            System.out.println("Report is generated at: " + reportPath);
//
//            sparkReporter = new ExtentSparkReporter(reportPath);
//            sparkReporter.config().setDocumentTitle("Automation Test Report");
//            sparkReporter.config().setReportName("Automation Test Execution Report");
//            sparkReporter.config().setTheme(Theme.DARK);
//            sparkReporter.config().setEncoding("utf-8");
//            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
//            sparkReporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.TEST, ViewName.CATEGORY, ViewName.DASHBOARD}).apply();
//
//            extent = new ExtentReports();
//            extent.attachReporter(sparkReporter);
//
//
//            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
//            extent.setSystemInfo("Environment", "QA");
//            String browser = testContext.getCurrentXmlTest().getParameter("browser");
//            extent.setSystemInfo("Browser", browser);
//
//
//            List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
//            if (!includedGroups.isEmpty()) {
//                extent.setSystemInfo("Groups", includedGroups.toString());
//            }
//
//
//        }
        if (extent == null) {
            Date dt = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm");
            String date = dateFormat.format(dt);

            // Use File.separator to ensure compatibility across different OS
            String reportDirPath = "." + File.separator + "Reports";
            File reportDir = new File(reportDirPath);

            // Ensure the directory exists
            if (!reportDir.exists()) {
                boolean created = reportDir.mkdirs();
                if (!created) {
                    System.out.println("Failed to create report directory!");
                    return; // Early exit if directory creation fails
                }
            }

            String reportPath = reportDirPath + File.separator + "AutomationTestReport.html";
            System.out.println("Report is generated at: " + new File(reportPath).getAbsolutePath());

            sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Automation Test Execution Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setEncoding("utf-8");
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            sparkReporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.TEST, ViewName.CATEGORY, ViewName.DASHBOARD}).apply();

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Set system info
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Environment", "QA");
            String browser = testContext.getCurrentXmlTest().getParameter("browser");
            extent.setSystemInfo("Browser", browser);

            List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
            if (!includedGroups.isEmpty()) {
                extent.setSystemInfo("Groups", includedGroups.toString());
            }
        }

    }


    public static void attachScreenshot(WebDriver driver) {
        try {
            String base64Screenshot = Screenshots.attachScreenshot(driver);
            test.get().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest testInstance = extent.createTest(testName, description);
        test.set(testInstance);
        System.out.println("ExtentTest created for: " + testName);
        return testInstance;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        } else {
            System.out.println("ExtentReports instance is null.");
        }

        try {
            URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\");
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("add763764@gmail.com","vwqa sadt khne tcru"));
            email.setSSLOnConnect(true);
            email.setFrom("alekhya8572@gmail.com");
            email.setSubject("Test Results");
            email.setMsg("Please find the Attached Report");
            email.addTo("add763764@gmail.com");
            email.attach(url,"extent report","check report");
            email.send();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void logInfo(String details) {
        ExtentTest testInstance = getTest();
        if (testInstance != null) {
            testInstance.log(Status.INFO, details);
        }
    }

    public static void logPass(String message) {
        ExtentTest testInstance = getTest();
        if (testInstance != null) {
            testInstance.pass(message);
        }
    }

    public static void logFail(String message) {
        ExtentTest testInstance = getTest();
        if (testInstance != null) {
            testInstance.fail(message);
        }
    }

    public static ExtentTest getTest() {
        ExtentTest testInstance = test.get();
        if (testInstance == null) {
            System.out.println("ExtentTest is null!.");
        }
        return testInstance;
    }

    public static void setTest(ExtentTest testInstance) {
        test.set(testInstance);
    }

    public static ExtentReports extentReports() {
        return extent;
    }
}
