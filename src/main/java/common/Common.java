package common;

import com.google.inject.Inject;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import support.GuiceScoped;

public abstract class Common {
    protected WebDriver driver;
    WebDriverFactory factory = new WebDriverFactory();
    protected Waiters waiters;

    @Inject
    public Common(GuiceScoped guiceScoped) {
        this.driver = guiceScoped.driver;
        this.waiters = new Waiters(driver);
        PageFactory.initElements(driver, this);
    }
}
