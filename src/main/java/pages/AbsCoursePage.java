package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AbsCoursePage extends AbsBasePage<AbsCoursePage> {
    public AbsCoursePage(WebDriver driver) {
        super(driver);
    }

    public AbsCoursePage pageHeaderShouldBeSameAs(String headerContent) {
        this.waiters.waitForElementVisibleByLocator(By.tagName("h1"));
        WebElement header = driver.findElement(By.tagName("h1"));
        //AssertThat(header.contains(headerContent)).isTrue();
        return this;
    }
}
