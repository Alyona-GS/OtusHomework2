package components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

public class EducationMenuBlock extends AbsComponent<EducationMenuBlock> {
    @FindBy(xpath = "//span[@title='Обучение']")
    private WebElement educationMenuButton;

    @FindBy(xpath = "//p[text()='Все курсы']/following-sibling::div")
    private WebElement allCoursesElement;

    @Inject
    public EducationMenuBlock(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public void clickCategoryLinkByText(String text){
        actions.moveToElement(educationMenuButton).build().perform();
        allCoursesElement.findElement(By.xpath(String.format(".//*[text()='%s']", text))).click();
    }
}
