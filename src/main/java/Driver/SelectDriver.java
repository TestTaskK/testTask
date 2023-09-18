package Driver;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class SelectDriver {

    public static LiveScoreDriver setUpAndGetDriver() {
        new ChromeDriverManager().setup();
        return new LiveScoreDriver(new ChromeDriver());
    }
}
