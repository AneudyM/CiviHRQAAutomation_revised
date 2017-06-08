import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by aneudy on 07/06/17.
 */
public class JobContract extends DriverManager {
    private String position;
    private String title;
    private String type;
    private String startDate;
    private String endDate;
    private String endReason;

    public JobContract(String position, String title, String type, String startDate, String endDate, String endReason) {
        this.position = position;
        this.title = title;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endReason = endReason;
    }

    // Create a Job Contract in CiviHR
    public void create() throws Exception {
        WebElement jobContractButton = driver.findElement(By.xpath("//*[@id=\"ui-id-3\"]"));
        jobContractButton.click();
        try {
            new WebDriverWait(driver, 40)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By.xpath("//*[@id=\"hrjob-contract\"]/div/p/button")));
        } catch (Exception e){
            System.out.println("Contract Button WebElement not found!");
        }

        WebElement addContractButton = driver.findElement(By.xpath("//*[@id=\"hrjob-contract\"]/div/p/button"));
        addContractButton.click();
        try {
            new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.presenceOfElementLocated(By.name("contractForm")));
        } catch (Exception e){
            System.out.println("Contract Form not found!");
        }

        WebElement positionField = driver.findElement(By.id("hrjc-position"));
        positionField.sendKeys(position);

        Select contractTypeField = new Select(driver.findElement(By.id("hrjc-contract-type")));

        contractTypeField.selectByVisibleText(type);

        WebElement startDateField = driver.findElement(By.id("hrjc-period-start-date"));

        startDateField.sendKeys(startDate);

        WebElement endDateField = driver.findElement(By.id("hrjc-period-end-date"));

        endDateField.sendKeys(endDate);

        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By.id("hrjc-end-reason")));
        } catch (Exception e){
            System.out.println("End Reason Field WebElement not found!");
        }


        Select endReasonField = new Select(driver.findElement(By.id("hrjc-end-reason")));

        endReasonField.selectByVisibleText(endReason);
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By
                                    .xpath("//*[@id=\"hrjob-contract\"]/div/div[1]/div/div/form/div[3]/button[2]")));
        } catch (Exception e){
            System.out.println("Could not find Create Contract Button");
        }

        WebElement createContractButton = driver
                .findElement(By
                        .xpath("//*[@id=\"hrjob-contract\"]/div/div[1]/div/div/form/div[3]/button[2]"));

        createContractButton.click();
    }

}
