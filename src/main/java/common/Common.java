package common;

import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Common {
    protected WebDriver driver;
    WebDriverFactory factory = new WebDriverFactory();
    protected Waiters waiters;

    public Common(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
        PageFactory.initElements(driver, this);
    }
}
