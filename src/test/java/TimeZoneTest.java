import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import Driver.LiveScoreDriver;
import Driver.SelectDriver;
import Pages.EventPage;
import Pages.EventsPage;
import Pages.SettingsPage;

public class TimeZoneTest {
    private LiveScoreDriver driver;

    @BeforeClass
    public void init() {
        driver = SelectDriver.setUpAndGetDriver();
    }

    @Test
    public void verifyEventStartDateAndTimeToNewTimeZone() {
        String automaticTimeZone = "Automatic";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy", Locale.ENGLISH);

        EventsPage eventsPage = new EventsPage(driver).get();
        driver.clickWhenReady(eventsPage.getCloseCookieButton());

         driver.getListOfElementsWhenVisible(eventsPage.getEventStatusOrTime()).stream()
                .filter(w -> w.getText()
                              .contains(":"))
                .findFirst()
                .ifPresent(WebElement::click);;

        EventPage eventPage = new EventPage(driver);

        String initialEventStartTime = driver.waitForElementAndGetText(eventPage.getEventScoreOrTime());
        String initialEventStartDate = driver.waitForElementAndGetText(eventPage.getEventStartDate());

        driver.clickWhenReady(eventPage.getBurgerMenu());
        driver.clickWhenReady(eventPage.getBurgerMenuSettings());

        SettingsPage settingsPage = new SettingsPage(driver);

        String currentTimeZone = driver.waitForElementAndGetText(settingsPage.getSelectedTimeZone());

        driver.clickWhenReady(settingsPage.getSelectedTimeZone());

        List<WebElement> timeZonesWithoutCurrentAndAuto =
                driver.getListOfElementsWhenVisible(settingsPage.getAvailableTimeZone())
                                                                .stream()
                                                                .filter(w -> !w.getText()
                                                                               .equals(currentTimeZone) && !w.getText()
                                                                                                             .equals(automaticTimeZone))
                                                                .toList();


        WebElement randomTimeZone = timeZonesWithoutCurrentAndAuto.get(new Random().nextInt(timeZonesWithoutCurrentAndAuto.size()));

        String newTimeZone = driver.waitForElementAndGetText(randomTimeZone)
                                   .replace(" ", "");

        driver.clickWhenReady(randomTimeZone);
        driver.clickWhenReady(settingsPage.getApplySettingButton());

        String eventStartTimeAfterTimeZoneChanged = driver.waitForElementAndGetText(eventPage.getEventScoreOrTime());
        String eventStartDateAfterTimeZoneChanged = driver.waitForElementAndGetText(eventPage.getEventStartDate());

        ZonedDateTime eventStartDateWithDefaultTimezone = LocalDateTime.parse(String.format("%s %s", initialEventStartTime,
                                                                               initialEventStartDate), formatter)
                                                                       .atZone(ZoneId.systemDefault());


        ZonedDateTime eventStartDateWithNewTimezone = LocalDateTime.parse(String.format("%s %s",
                                                                           eventStartTimeAfterTimeZoneChanged,
                                                                           eventStartDateAfterTimeZoneChanged),
                                                                           formatter)
                                                                   .atZone(ZoneId.of(newTimeZone));

        assertThat(eventStartDateWithNewTimezone.toLocalDateTime()).as("Event start date/time should be changed according to new time zone")
                                                                   .isEqualTo(eventStartDateWithDefaultTimezone.withZoneSameInstant(ZoneId.of(newTimeZone))
                                                                                                               .toLocalDateTime());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}