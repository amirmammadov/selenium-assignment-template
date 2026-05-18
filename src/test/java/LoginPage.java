import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private By headerLoginButton = By.xpath("//div[contains(@class, 'profile-parent')]//button[contains(@class, 'profile')]");
    
    private By emailInput = By.xpath("//div[@id='login-modal']//input[@id='login-email' and @name='login']");
    private By passwordInput = By.xpath("//div[@id='login-modal']//input[@name='password' and @type='password']");
    
    private By submitLoginButton = By.xpath("//div[@id='login-modal']//form//button[@type='submit' and contains(text(), 'Daxil ol')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickHeaderLogin() throws InterruptedException {
        Thread.sleep(3000); 
        WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(headerLoginButton));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", loginBtn);
    }

    public void enterCredentials(String email, String password) throws InterruptedException {
        Thread.sleep(3000); 
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passField.clear();
        passField.sendKeys(password);
    }

    public void submitLogin() throws InterruptedException {
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitLoginButton));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", submitBtn);
        Thread.sleep(3000); 
    }
}