package pageObjects;

/**
 * Created by VASILE on 8/30/2015.
 */

import automationFramework.TestBase;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class HeaderPage extends TestBase {
    private static WebElement element = null;
    public static WebDriverWait wait = new WebDriverWait(driver, 10);

    public static WebElement welcomeName(WebDriver driver){
        wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"emg-user-menu\"]/figcaption/span[1]")));
        element = driver.findElement(By.xpath("//*[@id=\"emg-user-menu\"]/figcaption/span[1]"));
        return element;
    }

    public static WebElement displayUserMenu(WebDriver driver){
        wait.until(presenceOfElementLocated(By.cssSelector("figcaption > span.icon-i23-down")));
        element = driver.findElement(By.cssSelector("figcaption > span.icon-i23-down"));
        return element;
    }

    public static WebElement btnLogout(WebDriver dirver){
        wait.until(presenceOfElementLocated(By.linkText("Log out")));
        element = dirver.findElement((By.linkText("Log out")));
        return element;
    }

    public static WebElement notLoggedMessage(WebDriver driver){
        wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"emg-user-menu\"]/figcaption/span[1]")));
        element = driver.findElement((By.xpath("//*[@id=\"emg-user-menu\"]/figcaption/span[1]")));
        return element;
    }
}
