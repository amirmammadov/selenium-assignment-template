import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {

    private By searchInput = By.xpath("//div[@id='search-modal']//input[@id='desktop-search' and @placeholder='Axtar']");
    private By searchButton = By.xpath("//button[contains(@class, 'search') and .//*[local-name()='svg']]");
    private By popularEventsHeader = By.xpath("//div[contains(@class, 'promotion-block')]//div[contains(text(), 'Populyar tədbirlər')]");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void openSearchModal() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        Thread.sleep(1500);
    }

    public void typeSearchQuery(String query) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(query);
    }

    public boolean isPopularEventsHeaderVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(popularEventsHeader)).isDisplayed();
    }
}