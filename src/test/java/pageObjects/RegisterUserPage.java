package pageObjects;

import automationFramework.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
/**
 * Created by VASILE on 9/1/2015.
 */
public class RegisterUserPage  extends TestBase {

    private static WebElement element = null;
    public static WebDriverWait wait = new WebDriverWait(driver, 10);

    public static WebElement redirectToLogin(WebDriver driver){
        wait.until(presenceOfElementLocated(By.cssSelector("a[href='/user/login?ref=ssi_register'")));
        element = driver.findElement(By.cssSelector("a[href='/user/login?ref=ssi_register'"));
        return element;
    }

    public static WebElement regName(WebDriver driver){
        wait.until(presenceOfElementLocated(By.id("r_name")));
        element = driver.findElement(By.id("r_name"));
        return element;
    }

    public static WebElement regEmail(WebDriver driver){
        element = driver.findElement(By.id("r_email"));
        return element;
    }

    public static WebElement regPassword(WebDriver driver){
        element = driver.findElement(By.id("r_password"));
        return element;
    }

    public static WebElement confirmPassword(WebDriver driver){
        element = driver.findElement(By.id("rc_password"));
        return element;
    }

    public static WebElement passChecker(WebDriver driver){
        element = driver.findElement(By.id("pass_checker_progress"));
        return element;
    }

    public static WebElement termsAndConditionCheck(WebDriver driver){
        element = driver.findElement(By.id("ctrigger"));
        return element;
    }

    public static WebElement newsletterCheck(WebDriver driver){
        element = driver.findElement(By.id("cNlTrigger"));
        return element;
    }

    public static WebElement submitRegistration(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"register_form\"]/input[8]"));
        return element;
    }

    public static WebElement nameErrorMessage(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"register_form\"]/div[1]"));
        return element;
    }

    public static WebElement emailErrorMessage(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"register_form\"]/div[4]"));
        return element;
    }

    public static WebElement passwordErrorMessage(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"register_form\"]/div[4]"));
        return element;
    }

    public static WebElement passwordConfErrorMessage(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"register_form\"]/div[5]"));
        return element;
    }
    
    public static WebElement termsAndCondErrorMessage(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"r_terms_error\"]"));
        return element;
    }
}
