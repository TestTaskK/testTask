package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import lombok.Getter;

@Getter
public class EventsPage extends BasePage {
    private final String ScoresUrl = "https://www.livescore.com/";

    private final WebDriver driver;

    @FindBy(xpath = ".//span[contains(@id,'status-or-time')]")
    private List<WebElement> eventStatusOrTime;

    public EventsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public EventsPage get() {
        driver.get(ScoresUrl);
        return this;
    }
}
