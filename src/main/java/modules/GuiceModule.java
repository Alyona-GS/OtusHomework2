package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.AbsCoursePage;
import pages.CoursesCataloguePage;
import pages.MainPage;

public class GuiceModule extends AbstractModule {

    private final WebDriver driver;

    public GuiceModule() {
        driver = new WebDriverFactory().create();
    }

    @Provides
    private WebDriver getDriver() {
        return this.driver;
    }

    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Singleton
    @Provides
    public CoursesCataloguePage getCoursesCataloguePage() {
        return new CoursesCataloguePage(driver);
    }

    @Singleton
    @Provides
    public AbsCoursePage getCourseCardPage() {
        return new AbsCoursePage(driver);
    }
}