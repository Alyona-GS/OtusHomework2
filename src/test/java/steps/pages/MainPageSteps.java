package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import pages.CoursesCataloguePage;
import pages.MainPage;

public class MainPageSteps {
    @Inject
    private MainPage mainPage;

    @И("^Нажата кнопка Обучение$")
    public void clickMenuLearningButton () {
        mainPage.clickMenuLearning();
    }

    @Если("^Переходим на случайную категорию курсов$")
    public void clickRandomMenuCategory () {
        mainPage.clickRandomCategoryInMenu();
    }
}
