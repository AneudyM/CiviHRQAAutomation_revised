import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by aneudy on 07/06/17.
 */
public class Login extends DriverManager {
    private String username;
    private String password;

    Login(String u, String p){
        username = u;
        password = p;
    }

    public void logIn() throws Exception {
        WebElement userField = driver.findElement(By.id("edit-name"));
        WebElement passField = driver.findElement(By.id("edit-pass"));
        WebElement loginButton = driver.findElement(By.id("edit-submit"));
        userField.sendKeys(username);
        passField.sendKeys(password);
        loginButton.click();
    }
}
