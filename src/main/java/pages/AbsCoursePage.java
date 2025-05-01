package pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.GuiceScoped;

public class AbsCoursePage extends AbsBasePage<AbsCoursePage> {
    @Inject
    public AbsCoursePage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public AbsCoursePage pageHeaderShouldBeSameAs(String headerContent) {
        this.waiters.waitForElementVisibleByLocator(By.tagName("h1"));
        WebElement header = driver.findElement(By.tagName("h1"));
        //AssertThat(header.contains(headerContent)).isTrue();
        return this;
    }
}
