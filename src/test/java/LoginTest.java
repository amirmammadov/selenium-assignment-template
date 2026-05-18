import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

public class LoginTest {
    private WebDriver driver;
    private String baseUrl;
    private String emailDomain;

    @Before
    public void setup() throws Exception {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                baseUrl = "https://iticket.az/";
                emailDomain = "@elte.hu";
            } else {
                prop.load(input);
                baseUrl = prop.getProperty("base.url");
                emailDomain = prop.getProperty("invalid.email.domain");
            }
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testIticketLoginWithInvalidCredentials() throws Exception {
        driver.get(baseUrl);

        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("iTicket.AZ"));

        driver.navigate().to("https://iticket.az/en/events/concerts");
        Thread.sleep(1500);
        driver.navigate().back();
        Thread.sleep(1500);

        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.isPopularEventsHeaderVisible());

        searchPage.openSearchModal();
        searchPage.typeSearchQuery("Jazz");
        Thread.sleep(1500);
        driver.navigate().refresh(); 

        String randomEmail = "student-" + UUID.randomUUID().toString().substring(0, 8) + emailDomain;
        String randomPassword = "Pass" + UUID.randomUUID().toString().substring(0, 8) + "!";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickHeaderLogin();
        loginPage.enterCredentials(randomEmail, randomPassword);
        loginPage.submitLogin();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}