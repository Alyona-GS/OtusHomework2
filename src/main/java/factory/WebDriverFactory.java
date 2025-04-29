package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import factory.settings.FirefoxSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private final String browserName = "firefox";//System.getProperty("browser.name");

    public WebDriver create() {
        switch (browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver((FirefoxOptions) new FirefoxSettings().settings());
            }
            default -> throw new BrowserNotFoundException();
        }
    }
}
