package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.AbsCoursePage;

public class AbsCoursePageSteps {
    @Inject
    AbsCoursePage absCoursePage;

    @Тогда("^Откроется страница курса (.*)$")
    public void coursePageShouldBeOpened(String courseName) {
        absCoursePage.pageHeaderShouldBeSameAs(courseName);
    }
}
