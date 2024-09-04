package BrowserFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utilities.ConfigReader;
//import utilities.ReportsManager;

public class BrowserFactory {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();


    private WebDriver createDriver(String browser) {
        WebDriver driver = null;
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser is not supported: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }


    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(String browser) {
        WebDriver driver = createDriver(browser);
        webDriver.set(driver);
        driver.get("https://www.saucedemo.com/v1/index.html");
    }


    public static WebDriver getDriver() {

        return webDriver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            webDriver.remove();
        }
    }
}
