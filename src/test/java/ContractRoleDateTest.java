import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by aneudy on 07/06/17.
 */
public class ContractRoleDateTest extends DriverManager {
    private String username = "civihr_admin";
    private String password = "civihr_admin";
    private String url = "http://demo.civihrhosting.co.uk/";
    private String firstname = "Aneudy";
    private String lastname = "Mota";
    private String email = "aneudy.motacatalino@gmail.com";
    private String contractPosition = "QA Automation Engineer";
    private String contractTitle = "QA Automation Engineer";
    private String contractType = "Intern";
    private String contractStartDate = "01/01/2018";
    private String contractEndDate = "31/12/2018";
    private String contractEndReason = "Voluntary";
    private String roleTitle = "";
    private String roleContract = "";
    private String roleStartDate = "";
    private String roleEndDate = "";

    @Test
    public void testContractRoleDates() throws Exception {

        Contact contact = new Contact(firstname, lastname, email);
        driver = new DriverManager().getDriver("chrome");
        try {
            driver.navigate().to(url);
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
        Login civihrLogin = new Login(username, password);
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
        WebElement myContact = contact.getContactByEmail(email);
        if(myContact == null){
            contact = new Contact(firstname, lastname, email);
            try {
                contact.create();
            } catch (Exception e){
                System.out.println("Could not create contact.");
            }
        } else {
            myContact.click();
        }
        try {
            new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.findElement(By.xpath("//*[@id=\"crm-contactname-content\"]/div/div[2]"))
                            .getText().toLowerCase().startsWith(firstname +" "+ lastname);
                }
            });
        } catch (Exception e){
            System.out.println("Contact with email: " + email + " not found.");
        }
        if(checkContract()){
            JobRole newRole = new JobRole(
                    roleTitle,
                    roleContract,
                    roleStartDate,
                    roleEndDate
            );
            try {
                newRole.create();
            } catch (Exception e){
                System.out.println("Could not create new job role.");
            }
        } else {
            // Create Job Contract
            JobContract newContract = new JobContract(
                    contractPosition,
                    contractTitle,
                    contractType,
                    contractStartDate,
                    contractEndDate,
                    contractEndReason
            );
            try {
                newContract.create();
            } catch (Exception e){
                System.out.println("Could not create new job contract.");
            }
        }
    }
}
