package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import pages.CoursesCataloguePage;

public class CoursesCataloguePageSteps {
    @Inject
    private CoursesCataloguePage coursesCataloguePage;

    @Тогда("^Проверяем, что открыта страница курсов верной категории$")
    public void checkCategory () {
        coursesCataloguePage.checkCatalogueUrl();
    }

    @И("^Виден курс (.*)$")
    public void findCourse (String text) {
        coursesCataloguePage.findCoursePlateByCourseName(text);
    }

    @Если("^Кликнуть на плитку курса (.*)$")
    public void clickOnCourse (String text) {
        coursesCataloguePage.clickCoursePlate(text);
    }

    @Если("Найдены самые ранние и поздние курсы")
    public void findCourses () {
        coursesCataloguePage.findMinMaxDateCourses();
    }

    @Тогда("Проверяем даты и названия курсов")
    public void checkDates () {
        coursesCataloguePage.nameAndDateOnMinMaxPlateCoursesIsRight();
    }
}
