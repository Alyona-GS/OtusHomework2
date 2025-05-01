package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import pages.CoursesCataloguePage;

public class CoursesCataloguePageSteps {
    @Inject
    private CoursesCataloguePage coursesCataloguePage;

    @И("^Откроется страница Каталог$")
    public void catalogedPageShouldBeOpened(){
        coursesCataloguePage.checkCatalogPageVisibility();
    }

    @И("^Чекбокс номер (\\d+) будет (Отмечен|Не отмечен)$")
    public void checkCheckBoxInput(int index, String isChecked){
        coursesCataloguePage.checkCheckBoxInput(index, isChecked.equals("Отмечен"));
    }
}
