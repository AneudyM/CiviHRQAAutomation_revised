import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by aneudy on 07/06/17.
 */
public class Contact extends DriverManager {
    private String firstname;
    private String lastname;
    private String email;

    public Contact(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public void create(){
        WebElement contactButton = driver.findElement(By.xpath("//*[@id=\"civicrm-menu\"]/li[4]"));
        contactButton.click();
        WebElement newIndividual = driver.findElement(By.linkText("New Individual"));
        newIndividual.click();
        WebElement firstNameField = driver.findElement(By.id("first_name"));
        WebElement lastNameField = driver.findElement(By.id("last_name"));
        WebElement emailField = driver.findElement(By.id("email_1_email"));
        WebElement saveButton = driver.findElement(By.id("_qf_Contact_upload_view-bottom"));
        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastname);
        emailField.sendKeys(email);
        saveButton.click();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
