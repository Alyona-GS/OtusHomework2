package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickMenuLearning() {
        findElement(By.xpath("//*[contains(@class, 'sc-1youhxc-1 cMNIlZ')]")).click();
        return this;
    }
    public CoursesCataloguePage clickRandomCategoryInMenu() {
        List<WebElement> categoriesInMenu = driver.findElements(By.xpath("//a[contains(@href, 'https://otus.ru/categories/')]")).stream().toList();
        int choice = (int) (Math.random() * categoriesInMenu.size());
        WebElement category = categoriesInMenu.get(choice);
        String categoryText = category.getText();
        category.click();
        return new CoursesCataloguePage(categoryText, driver);
    }
}
