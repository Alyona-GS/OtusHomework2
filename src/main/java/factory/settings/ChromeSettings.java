package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings {

    public AbstractDriverOptions settings() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--start-fullscreen"
        );
        return chromeOptions;
    }
}
