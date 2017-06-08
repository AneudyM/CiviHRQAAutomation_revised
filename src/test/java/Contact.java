import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public void create() throws Exception {
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

    public WebElement getContactByEmail(String email) throws Exception {
        WebElement searchField = driver.findElement(By.name("sort_name"));
        searchField.click();
        driver.findElement(By.xpath("//*[@id=\"root-menu-div\"]/div[1]/ul/li[6]/div/label/input")).click();
        searchField.sendKeys(email);
        WebElement results = driver.findElement(By.id("ui-id-1"));
        waitUntilVisible(results, 20);
        List<WebElement> list = results.findElements(By.tagName("li"));
        for (WebElement item : list) {
            if (item.getText().contains(email)){
                return item;
            } else {
                return null;
            }
        }
        return null;
    }
}
