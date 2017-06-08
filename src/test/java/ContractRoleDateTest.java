import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.SocketException;
import java.util.concurrent.TimeUnit;

/**
 * Created by aneudy on 07/06/17.
 */
public class ContractRoleDateTest extends DriverManager {

    @Test
    public void testContractRoleDates() throws Exception {
        driver = new DriverManager().getDriver("chrome");
        try {
            driver.navigate().to("http://demo.civihrhosting.co.uk/");
            // driver.manage().window().maximize();
            new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.findElement(By.id("block-welcome-sitename")).getText().toLowerCase()
                            .startsWith("civihr demo site");
                }
            });
        } catch (Exception e){
            driver.quit();
            throw new Exception("Page could not be reached.");
        }
        Login civihrLogin = new Login("civihr_admin", "civihr_admin");
        try {
            civihrLogin.logIn();
            new WebDriverWait(driver, 40).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.findElement(By.xpath("//*[@id=\"ct-dashboard\"]/div[1]/div/div[1]/ol/li[2]"))
                            .getText().toLowerCase().startsWith("dashboard");
                }
            });
        } catch (Exception e){
            System.out.println("Could not log in.");
        }
    }
}
