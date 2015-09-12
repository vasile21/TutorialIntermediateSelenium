package helperMethods;
import automationFramework.TestBase;
/**
 * Created by VASILE on 9/9/2015.
 */
public class LoginHelperMethods extends TestBase{
    public  void  waitForPopup() throws InterruptedException {
        int timeCount = 1;
        do
        {
            driver.getWindowHandles();
            Thread.sleep(100);
            timeCount++;
            if ( timeCount > 50 )
            {
                System.out.println("Pop-up window is not opened");
                break;
            }
        }
        while (driver.getWindowHandles().size() == 1);
    }
}
