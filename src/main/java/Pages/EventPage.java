package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

@Getter
public class EventPage extends BasePage {

    @FindBy(id = "score-or-time")
    private WebElement eventScoreOrTime;

    @FindBy(xpath = ".//button[@data-testid='match-info-row_root-startTime']")
    private WebElement eventStartDate;

    public EventPage(WebDriver driver) {
        super(driver);
    }
}