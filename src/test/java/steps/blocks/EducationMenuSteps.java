package steps.blocks;

import com.google.inject.Inject;
import components.EducationMenuBlock;
import io.cucumber.java.ru.И;

public class EducationMenuSteps {
    @Inject
    private EducationMenuBlock educationMenuBlock;

    @И("^Выбрать категорию (.*)$")
    public void selectCategory(String text){
        educationMenuBlock.clickCategoryLinkByText(text);
    }
}
