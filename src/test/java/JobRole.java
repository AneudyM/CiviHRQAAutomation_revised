import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by aneudy on 07/06/17.
 */
public class JobRole extends DriverManager {
    private String title;
    private String contract;
    private String startDate;
    private String endDate;

    public JobRole(String title, String contract, String startDate, String endDate){
        this.title = title;
        this.contract = contract;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void create(){
        WebElement jobRoleButton = driver.findElement(By.id("ui-id-5"));
        jobRoleButton.click();
        new WebDriverWait(driver, 40)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//*[@id=\"hrjobroles\"]/div/button")));
        WebElement addRoleButton = driver.findElement(By.xpath("//*[@id=\"hrjobroles\"]/div/button"));
        addRoleButton.click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.name("form")));
        WebElement titleField = driver.findElement(By.id("inputTitle"));
        titleField.sendKeys(title);
        Select contractField = new Select(driver.findElement(By.id("newContractId")));
        contractField.selectByIndex(1);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContract() {
        return contract;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
