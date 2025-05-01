package common;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import support.GuiceScoped;

public class AbsPageObject<T> {
    protected WebDriver driver;
    protected Waiters waiters;
    protected GuiceScoped guiceScoped;
    protected Actions actions;

    @Inject
    public AbsPageObject(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
        this.driver = guiceScoped.driver;
        this.waiters = new Waiters(driver);
        this.actions = new Actions(driver);
        PageFactory.initElements(guiceScoped.driver, this);
    }
}
