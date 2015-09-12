package automationFramework;

import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.NoSuchElementException;


/**
 * Created by VASILE on 8/30/2015.
 */
public class TestBase {
    //private String browser_type;
    public static WebDriver driver;
    Actions action = new Actions(driver);

    //public StringBuffer verificationErrors = new StringBuffer();

    /*defines needed time for the full load of the page after refresh event*/
    public static int pageLoadingTime = 5500;
    /*variable defines the response time of the GUI after a certain action*/
    public int waitForElement = 5000;

    protected static String baseUrl = "http://www.emag.ro/homepage";

    public static WebDriver browserSetUp(String browserType, Boolean maximaze) {
        switch (browserType) {
            case "internet_explorer":
                System.setProperty("webdriver.ie.driver", "src\\test\\Java\\Drivers\\IEdriver\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;

            case "google_chrome":
                System.setProperty("webdriver.chrome.driver", "src\\test\\Java\\Drivers\\chromedriver\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver(options);
                break;

            case "mozilla_firefox":
                driver = new FirefoxDriver();
                break;

            default:
                driver = new FirefoxDriver();
                break;
        }

        if (maximaze == true) {
            driver.manage().window().maximize();
        } else {
            driver.manage().window().setSize(new Dimension(1000, 700));
        }

        return driver;
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public static void beforeTestClass() {
        driver = browserSetUp("google_chrome", true);

    }

    public void beforeTestCase() {
        driver.get(baseUrl);
    }

    public void afterTestCase() {
        //deleting cache after each test case
        if (driver instanceof FirefoxDriver || driver instanceof ChromeDriver) {
            driver.manage().deleteAllCookies();
        }else if (driver instanceof InternetExplorerDriver) {
            DesiredCapabilities ieCapabilities = new DesiredCapabilities();
            ieCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        }
    }

    public static void afterTestClass() {
        driver.quit();
    }

    public void checkPageRefresh(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        String refrehCheck = (String) jsExecutor.executeScript(
                "var s1 = 'pageNotRefreshed'; var s2 = 'pageIsRefreshed'; " +
                        "if(document.readyState === 'complete'){return s1;} else {return s2;}");
        Assert.assertEquals("Refresh event occurred on page", "pageNotRefreshed", refrehCheck);
    }
}
