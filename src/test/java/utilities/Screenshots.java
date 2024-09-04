package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class Screenshots {
    public static void takeScreenshot(WebDriver driver, String path) throws Exception {
        File sc = new File("./Screenshots/");
        File SS = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(SS, new File(sc, path));
        System.out.println("Screenshot taken successfull");
    }

    public static String attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
