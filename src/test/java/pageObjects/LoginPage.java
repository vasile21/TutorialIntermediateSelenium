package pageObjects;

/**
 * Created by VASILE on 8/30/2015.
 */

import automationFramework.TestBase;
import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginPage extends TestBase{

    private static WebElement element = null;
    public static WebDriverWait wait = new WebDriverWait(driver, 10);


    public static WebElement txtUserName(WebDriver driver){
        wait.until(presenceOfElementLocated(By.id("r_email")));
        element = driver.findElement(By.id("r_email"));
        return element;
    }

    public static WebElement txtPassword(WebDriver driver){
        element = driver.findElement(By.id("r_password"));
        return element;
    }

    public static WebElement btnLogin(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"auth_form\"]/input[5]"));
        return element;
    }

    public static  WebElement invalidCredentialsMessage(WebDriver driver){
        wait.until(presenceOfElementLocated(By.id("ll_single_error")));
        element = driver.findElement(By.id("ll_single_error"));
        return element;
    }

    public static WebElement facebookPlugin(WebDriver driver){
        element = driver.findElement(By.cssSelector("a.linkItem.facebookV2Login"));
        return element;
    }

    public static WebElement facebookUsername(WebDriver driver){
        wait.until(presenceOfElementLocated(By.id("email")));
        element = driver.findElement(By.id("email"));
        return element;
    }

    public static WebElement facebookPassword(WebDriver driver){
        wait.until(presenceOfElementLocated(By.id("pass")));
        element = driver.findElement(By.id("pass"));
        return element;
    }

    public static WebElement facebookLoginBtn(WebDriver driver){
        element = driver.findElement(By.id("u_0_2"));
        return element;
    }

    public static WebElement googlePlugin(WebDriver driver){
        element = driver.findElement(By.cssSelector("a.linkItem.googleV2Login"));
        return element;
    }

    public static WebElement googleUsername(WebDriver driver){
        wait.until(presenceOfElementLocated(By.id("Email")));
        element = driver.findElement(By.id("Email"));
        return element;
    }

    public static  WebElement googleNextButton(WebDriver driver){
        element = driver.findElement(By.id("next"));
        return element;
    }

    public static WebElement googlePassword(WebDriver driver){
        wait.until(presenceOfElementLocated(By.id("Passwd")));
        element = driver.findElement(By.id("Passwd"));
        return element;
    }

    public static WebElement googleLoginBtn(WebDriver driver){
        element = driver.findElement(By.id("signIn"));
        return element;
    }

    public static WebElement invalidMailMessage(WebDriver driver){
        wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"auth_form\"]/div[2]")));
        element = driver.findElement(By.xpath("//*[@id=\"auth_form\"]/div[2]"));
        return element;
    }

}
