import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursesCataloguePage;

public class CoursesCataloguePageTest {
    @Inject
    CoursesCataloguePage coursesCataloguePage;

    @Test
    public void checkCourseOpenedByCoursePlateTest() {
        coursesCataloguePage
                .open()
                .findCoursePlateByCourseName("Machine Learning")
                .clickCoursePlate("Machine Learning")
                .pageHeaderShouldBeSameAs("Machine Learning");
    }

    @Test
    public void checkNameAndDateOnCoursePlateTest() {
        coursesCataloguePage
                .open()
                .findMinMaxDateCourses()
                .nameAndDateOnMinMaxPlateCoursesIsRight();
    }
}
