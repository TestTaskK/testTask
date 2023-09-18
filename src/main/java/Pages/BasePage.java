package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

@Getter
public abstract class BasePage {

    private final WebDriver driver;

    @FindBy(id = "burger-menu-open")
    private WebElement burgerMenu;

    @FindBy(id = "simpleCookieBarCloseButton")
    private WebElement closeCookieButton;

    @FindBy(id = "burger-menu__settings")
    private WebElement burgerMenuSettings;

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
