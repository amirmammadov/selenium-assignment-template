import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testIticketLoginWithInvalidCredentials() throws Exception {
        driver.get("https://iticket.az/");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickHeaderLogin();
        loginPage.enterCredentials("teststudent@elte.hu", "WrongPassword123!");
        loginPage.submitLogin();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}