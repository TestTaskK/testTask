package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import lombok.Getter;

@Getter
public class SettingsPage extends BasePage {

    @FindBy(xpath = ".//button[@data-testid='settings-form_apply-button']")
    private WebElement applySettingButton;

    @FindBy(xpath = ".//label[@id='TZ_SELECT-label']")
    private WebElement selectedTimeZone;

    @FindBy(xpath = ".//div[@class ='Xj selectItems']//div[contains(@id,'TZ_SELECT')]")
    private List<WebElement> availableTimeZone;

    public SettingsPage(WebDriver driver) {
        super(driver);
    }
}
