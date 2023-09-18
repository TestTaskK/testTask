package Waits;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public interface WaitUtils extends WebDriver {

    int TIMEOUT = 5;

    default boolean isElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(TIMEOUT));

        try {
            return wait.until(ExpectedConditions.visibilityOf(element))
                       .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    default List<WebElement> getListOfElementsWhenVisible(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(TIMEOUT));

        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (TimeoutException e) {
            e.getStackTrace();
        }
        return Collections.emptyList();
    }

    default void clickWhenReady(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(element))
            .click();
    }

    default String waitForElementAndGetText(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(TIMEOUT));
        try {
            return wait.until(ExpectedConditions.visibilityOf(element))
                       .getText();
        } catch (TimeoutException e) {
            e.getStackTrace();
        }

        return null;
    }
}