//package utilities;
//
//import BrowserFactory.BrowserFactory;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//public class ReportsManager implements ITestListener {
//    private ExtentSparkReporter sparkReporter;
//    private ExtentReports extent;
//    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    private String repname;
//
//    @Override
//    public void onStart(ITestContext testContext) {
//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
//        repname = "Test-Report-" + timeStamp + ".html";
//        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + repname);
//
//        sparkReporter.config().setDocumentTitle("Automation Report");
//        sparkReporter.config().setReportName("Functional Testing");
//        sparkReporter.config().setTheme(Theme.DARK);
//
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//        extent.setSystemInfo("Environment", "QA");
//
//        String browser = testContext.getCurrentXmlTest().getParameter("browser");
//        extent.setSystemInfo("Browser", browser);
//
//        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
//        if (!includedGroups.isEmpty()) {
//            extent.setSystemInfo("Groups", includedGroups.toString());
//        }
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        String description = result.getMethod().getDescription(); // Get description from @Test annotation
//        test.set(extent.createTest(result.getMethod().getMethodName(), description));
//        test.get().assignCategory(result.getMethod().getGroups());
//        test.get().log(Status.PASS, result.getName() + " executed successfully");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        String description = result.getMethod().getDescription(); // Get description from @Test annotation
//        test.set(extent.createTest(result.getMethod().getMethodName(), description));
//        test.get().assignCategory(result.getMethod().getGroups());
//        test.get().log(Status.FAIL, result.getName() + " got failed");
//        test.get().log(Status.INFO, result.getThrowable().getMessage());
//
//        String base64Screenshot = Screenshots.attachScreenshot(BrowserFactory.getDriver());
//        test.get().info(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        String description = result.getMethod().getDescription(); // Get description from @Test annotation
//        test.set(extent.createTest(result.getMethod().getMethodName(), description));
//        test.get().assignCategory(result.getMethod().getGroups());
//        test.get().log(Status.SKIP, result.getName() + " got skipped");
//        if (result.getThrowable() != null) {
//            test.get().log(Status.INFO, result.getThrowable().getMessage());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        extent.flush();
//
////        String pathOfReport = System.getProperty("user.dir") + "/Reports/" + repname;
////        File extentReport = new File(pathOfReport);
////
////        try {
////            Desktop.getDesktop().browse(extentReport.toURI());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//        try {
//            URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repname);
//            ImageHtmlEmail email = new ImageHtmlEmail();
//            email.setDataSourceResolver(new DataSourceUrlResolver(url));
//            email.setHostName("smtp.googlemail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator("add763764@gmail.com","vwqa sadt khne tcru"));
//            email.setSSLOnConnect(true);
//            email.setFrom("alekhya8572@gmail.com");
//            email.setSubject("Test Results");
//            email.setMsg("Please find the Attached Report");
//            email.addTo("add763764@gmail.com");
//            email.attach(url,"extent report","check report");
//            email.send();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
////    @Override
////    public void onFinish(ITestContext context) {
////        extent.flush();
////        //String pathOfReport = System.getProperty("user.dir") + "/Reports/" + repname;
////        //File extentReport = new File(pathOfReport);
////
////        String reportFilePath = System.getProperty("user.dir") + "/utils/Reports/index.html";
////        String toEmail = "add763764@gmail.com";
////        String subject = "Automation Test Report";
////        String body = "Please find the attached automation test report.";
////        Emails.sendReportByEmail(toEmail, subject, body, reportFilePath);
////        System.out.println("Email sent successfully");
////
//////        try {
//////            Desktop.getDesktop().browse(extentReport.toURI());
//////        } catch (IOException e) {
//////            e.printStackTrace();
//////        }
////    }
//
//
//
//
//}

