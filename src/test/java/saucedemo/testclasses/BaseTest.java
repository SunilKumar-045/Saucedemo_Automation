package saucedemo.testclasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import saucedemo.utils.ConfigReader;

public class BaseTest {
    protected static WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void setup() {
        // load config file
        ConfigReader.loadProperties("src/test/resources/config.properties");

        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("url");

        switch (browser.toLowerCase()) {
        case "edge":
            System.setProperty("webdriver.edge.driver", "E:\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
            break;

        case "chrome":
//            System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);

        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
