package steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import pages.CoursesCataloguePage;
import pages.MainPage;

public class CommonSteps {
    @Inject
    private CoursesCataloguePage coursesCataloguePage;

    @Inject
    private MainPage mainPage;

    @Пусть("Открыта страница каталога курсов")
    public void openPage() {
        coursesCataloguePage.open();
    }

    @Пусть("Открыта главная страница в браузере")
    public void openMainPage() {
        mainPage.open();
    }

}
