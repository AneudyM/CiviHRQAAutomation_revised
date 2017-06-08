import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by aneudy on 07/06/17.
 */
public class DriverManager {

    public static WebDriver driver = null;

    public WebDriver getDriver(String browser) {
        // Select the browser
        if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Unrecognized specified browser.");
        }
        return driver;
    }

    public void waitUntilVisible(WebElement e, Integer s) throws Exception {
        new WebDriverWait(driver, s).until(ExpectedConditions.visibilityOf(e));
    }

    public boolean checkContract(){
        WebElement jobContractButton = driver.findElement(By.xpath("//*[@id=\"ui-id-3\"]"));
        jobContractButton.click();
        // I'll be using the "Current Contracts:" text label
        // as a condition for the existence of at least one
        // contract. If the text "Current Contracts:" is
        // displayed, then there is a high probability that
        // there is at least one Contract created.
        // Then I'll return this element
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.presenceOfElementLocated(By
                            .xpath("//*[@id=\"hrjob-contract\"]/div/h3[1]")));
        } catch (Exception e){
            System.out.println("Contract was not found.");
        }
        WebElement currentContractsText = driver.findElement(By
                .xpath("//*[@id=\"hrjob-contract\"]/div/h3[1]"));
        if (currentContractsText.getText().toString().equals("Current Contracts:")){
            return true;
        } else {
            return false;
        }
    }
}
