package steps.common;

import com.google.inject.Inject;
//import io.cucumber.java.ru.Пусть;
import pages.CoursesCataloguePage;
import pages.MainPage;

public class CommonSteps {
    @Inject
    private CoursesCataloguePage coursesCataloguePage;

    @Inject
    private MainPage mainPage;

    @Пусть("^Открыта страница каталога курсов$")
    public void openPage() {
        System.out.println("here");
        coursesCataloguePage.open();
    }

    @Пусть("^Открыта главная страница$")
    public void openMainPage() {
        System.out.println("her");
        mainPage.open();
    }

}
