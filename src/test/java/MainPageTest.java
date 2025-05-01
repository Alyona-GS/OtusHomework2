import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

public class MainPageTest {
    @Inject
    MainPage mainPage;

    @Test
    public void checkCategoryOpenedAfterCheckBoxClickTest() {
        mainPage
                .open()
                .clickMenuLearning()
                .clickRandomCategoryInMenu()
                .checkCatalogueUrl();
    }
}
