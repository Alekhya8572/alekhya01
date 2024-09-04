package utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties pro = new Properties();

    static {
        try {
            String path = "./src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(path);

        } catch (IOException e) {
            System.out.println("error at ConfigReader.");
        }
    }

    public static String getProperty(String key) {
        return pro.getProperty(key);
    }
}
