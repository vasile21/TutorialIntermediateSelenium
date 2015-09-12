package automationFramework;

import junit.framework.Assert;
import org.junit.*;
import pageObjects.HeaderPage;
import pageObjects.LoginPage;
import pageObjects.RegisterUserPage;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by VASILE on 8/30/2015.
 */

public class LoginTest extends TestBase{

    private String validEmail = "vasile_vetisan@yahoo.com";
    private String wrongEmail = "popoo@yahho.com";
    private String validPassword = "Tinichigiu21@";
    private String wrongPassword = "MyNewName";
    private String invalidEmail = "qazwee";

    private String validMediaUsername = "vetisanvasile@gmail.com";
    private String validFacebookPassword = "Tinichigiu21@";
    private String validGooglePassword = "tinichigiu21@";

    private String currentURL;
    private String registrationPageURL = "https://www.emag.ro/user/register?ref=ssi_login";

    /**
     * @parentWindowHandler will take main browser window as parnet of pop-ups
     */
    //private String parentWindowHandler = null;
    //private String subWindowHandler = null;

    @BeforeClass
    public static void setUpTestClass(){
        beforeTestClass();
    }

    @Before
    public void setUpTestCase(){
        beforeTestCase();
    }

    @Test
    public void wrongPassword()throws InterruptedException{
        navigateToLogin(true, false, false);
        completeLoginFrom(true, false);  }

    @Test
    public void wrongUsername()throws InterruptedException{
        navigateToLogin(false, true, false);
        completeLoginFrom(false, true);
    }

    @Test
    public void invalidUsername() throws InterruptedException {
        navigateToLogin(false, true, false);
        invalidUser(true, false);
    }

    @Test
    public void emptyUsername() throws InterruptedException {
        navigateToLogin(false, true, false);
        invalidUser(false, true);
    }

    @Test
    public void emailValidationOnClient() throws InterruptedException {
        navigateToLogin(false, true, false);
        invalidUser(false, true);
    }

    @Test
    public void validLogin()throws InterruptedException{
        navigateToLogin(true, false, false);
        completeLoginFrom(true, true);
    }

    @Test
    public void loginWithFacebook() throws InterruptedException {
        navigateToLogin(true, false, false);
        userLoginMedia(true, false);
    }

    @Test
    public void loginWithGoogle() throws InterruptedException {
        navigateToLogin(true, false, false);
        userLoginMedia(false, true);
    }


    @Test
    public void logout() throws InterruptedException {
        navigateToLogin(false, false, true);
        completeLoginFrom(true, true);
        userLogout();
    }

    @After
    public void tearDownTestCase(){
        afterTestCase();
    }

    @AfterClass
    public static void tearDownClass(){
        afterTestClass();
    }

    /**
     * HELPER METHODS
     */
    public void navigateToLogin(boolean fromHomePage, boolean fromURL, boolean fromRegisterPage) throws InterruptedException {

        if(fromHomePage){
            //user is on home page and navigates to login page
            HeaderPage.displayUserMenu(driver).click();
        }
        else if(fromURL){
            //user opens login page directly from the link
            driver.get("https://www.emag.ro/user/login?ref=ssi_register");
        }
        else if(fromRegisterPage){
            //user needs to be on registration page !!!!
            currentURL = driver.getCurrentUrl();
            if(currentURL != registrationPageURL) {
                driver.get(registrationPageURL);
            }
            currentURL = driver.getCurrentUrl();
            Assert.assertEquals("URL from web page is not correct",registrationPageURL,
                    currentURL);
            RegisterUserPage.redirectToLogin(driver).click();
        }
        else if (fromHomePage != true && fromURL != true && fromRegisterPage !=true){
            System.out.println("AT LEAST ONE PARAMETER MUST BE SET ON TRUE");
        }
    }

    public void assertUsernameAfterLogin(){
        Assert.assertEquals("Username is not displayed after valid login"
                , HeaderPage.welcomeName(driver).getText(), "Vetisan Vasile");
    }

    public void completeEmailField(String email){
        LoginPage.txtUserName(driver).click();
        LoginPage.txtUserName(driver).sendKeys(email);
    }

    public void completePasswordField(String password){
        LoginPage.txtPassword(driver).click();
        LoginPage.txtPassword(driver).sendKeys(password);
    }

    public void completeLoginFrom(Boolean username, Boolean password){
        //user is already on login page and login form is displayed
        if(username == true && password == false){
            completeEmailField(validEmail);
            completePasswordField(wrongPassword);
        }
        else if(username == false && password == true){
            completeEmailField(wrongEmail);
            completePasswordField(validPassword);
        }
        else if(username == false && password == false){
            completeEmailField(wrongEmail);
            completePasswordField(wrongPassword);
        }
        else if(username == true && password == true){
            completeEmailField(validEmail);
            completePasswordField(validPassword);
        }
        LoginPage.btnLogin(driver).click();

        if(username == false || password == false){
            Assert.assertEquals("Message verification failed"
                    , LoginPage.invalidCredentialsMessage(driver).getText(), "Ai introdus gresit parola sau adresa de email." +
                    " Te rog completeaza din nou campurile de mai jos.");
        }
        else if (username == true && password == true){
            assertUsernameAfterLogin();
        }
    }

    public  void invalidEmailValidation(){
        String expectedText =  LoginPage.invalidMailMessage(driver).getText();
        //due to special char from "Adresa de email nu este validă" we need to use substring function to get rid of special characters
        expectedText = expectedText.substring(0,29);
        Assert.assertEquals("Expcted text: Adresa de email nu este valid",
                expectedText, "Adresa de email nu este valid");
    }

    public void invalidUser(Boolean invalidUsername, Boolean empty){
        if(invalidUsername == true){
            completeEmailField(invalidEmail);
            completePasswordField(validPassword);
            LoginPage.btnLogin(driver).click();
            checkPageRefresh();
            invalidEmailValidation();
        }
        else if(empty == true){
            completeEmailField("");
            completePasswordField(validPassword);
            LoginPage.btnLogin(driver).click();
            checkPageRefresh();
            invalidEmailValidation();
        }
    }

    public void fillMediaLoginForm(Boolean fckb, Boolean goo) throws InterruptedException {
        if(fckb == true) {
            LoginPage.facebookUsername(driver).click();
            LoginPage.facebookUsername(driver).sendKeys(validMediaUsername);
            LoginPage.facebookPassword(driver).click();
            LoginPage.facebookPassword(driver).sendKeys(validFacebookPassword);
            LoginPage.facebookLoginBtn(driver).click();
        }
        else if(goo == true){
            LoginPage.googleUsername(driver).click();
            LoginPage.googleUsername(driver).sendKeys(validMediaUsername);
            LoginPage.googleNextButton(driver).click();
            LoginPage.googlePassword(driver).click();
            LoginPage.googlePassword(driver).sendKeys(validGooglePassword);
            LoginPage.googleLoginBtn(driver).click();
        }
    }
    public Iterator<String> getBrowserWindows(){
         /*main browser window is focused and selected*/
        Set<String> mainWindow = driver.getWindowHandles();
        //we need to wait for the pop-up to be opened
        Iterator<String> itr = mainWindow.iterator();
        return itr;
    }
    public  void  waitForPopup(int windowsNumber, boolean opened) throws InterruptedException {
            int timeCount = 1;
            do {
                driver.getWindowHandles();
                Thread.sleep(100);
                timeCount++;
                if (timeCount > 50 && opened == true) {
                    System.out.println("Pop-up window is not opened");
                    break;
                }
                else if(timeCount > 50 && opened == true){
                    System.out.println("Pop-up window is not closed");
                    break;
                }
            }
            while (driver.getWindowHandles().size() == windowsNumber);
    }

    public void userLoginMedia(Boolean facebook, Boolean googlePlus) throws InterruptedException {
        if(facebook == true){
            LoginPage.facebookPlugin(driver).click();
            waitForPopup(1, true);
             /*main browser window is focused and selected*/
            Set<String> mainWindow = driver.getWindowHandles();
            Iterator<String> itr = mainWindow.iterator();
            int i=0;
            while (itr.hasNext()){
                i++;
                String parentWindow = itr.next();
                String childWindow = itr.next();
                driver.switchTo().window(childWindow);
                fillMediaLoginForm(true, false);
                waitForPopup(2,false);
                driver.switchTo().window(parentWindow);
                assertUsernameAfterLogin();
            }
        }
        else if(googlePlus == true) {
            LoginPage.googlePlugin(driver).click();
            waitForPopup(1, true);
             /*main browser window is focused and selected*/
            Set<String> mainWindow = driver.getWindowHandles();
            Iterator<String> itr = mainWindow.iterator();
            String parentWindow = itr.next();
            String childWindow = itr.next();
            //Switch to child window in order to perform login
            driver.switchTo().window(childWindow);
            fillMediaLoginForm(false, true);
            driver.switchTo().window(parentWindow);
            assertUsernameAfterLogin();
        }
    }

    public void userLogout(){
        action.moveToElement(HeaderPage.welcomeName(driver)).build().perform();
        action.moveToElement(HeaderPage.btnLogout(driver));
        HeaderPage.btnLogout(driver).click();
        Assert.assertEquals("User name is still displayed", HeaderPage.notLoggedMessage(driver).getText(), "Contul meu");
    }
}
