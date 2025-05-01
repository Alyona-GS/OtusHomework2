package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import support.GuiceScoped;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Path("/catalog/courses")
public class CoursesCataloguePage extends AbsBasePage<CoursesCataloguePage> {
    private String category;
    private String minDate;
    private String maxDate;
    private List<String> minDateCourses;
    private List<String> maxDateCourses;

    @Inject
    public CoursesCataloguePage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public CoursesCataloguePage(String category, GuiceScoped guiceScoped) {
        super(guiceScoped);
        this.category = category;
    }

    public CoursesCataloguePage findCoursePlateByCourseName(String courseName) {
        WebElement buttonShowMore = findElement(By.xpath("//*[contains(@class, 'sc-1qig7zt-0 bYRRHi sc-prqxfo-0 cXVWAS')]"));
        boolean courseNotVisible = true;
        while (courseNotVisible) {
            this.waiters.waitForElementVisible(driver.findElement(By.tagName("h6")));
            List<String> visibleCourseNames = driver.findElements(By.tagName("h6")).stream().map(WebElement::getText).toList();
            for (String course : visibleCourseNames) {
                if (course.equals(courseName)) {
                    courseNotVisible = false;
                    break;
                }
            }
            if (courseNotVisible) {
                this.waiters.waitForElementToBeClickable(buttonShowMore);
                buttonShowMore.click();
            } else {
                break;
            }
        }
        return this;
    }

    public AbsCoursePage clickCoursePlate(String courseName) {
        WebElement plate = driver.findElement(By.xpath("//section/div/div/a[contains(@class, 'sc-zzdkm7-0') and contains(.//h6, '" + courseName + "')]"));
        this.waiters.waitForElementToBeClickable(plate);
        plate.click();
        return new AbsCoursePage(guiceScoped);
    }

    public CoursesCataloguePage findMinMaxDateCourses() {
        WebElement buttonShowMore = findElement(By.xpath("//*[contains(@class, 'sc-1qig7zt-0 bYRRHi sc-prqxfo-0 cXVWAS')]"));
        while (this.waiters.waitForElementToBeClickable(buttonShowMore)) {
            buttonShowMore.click();
        }
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM, yyyy", new Locale("ru"));
        List<Date> dates = driver
                .findElements(By.xpath("//section/div/div/a/div/div/div[contains(@class, 'sc-hrqzy3-1 jEGzDf') and not(descendant::img)]"))
                .stream().map(WebElement::getText)
                .map((date) -> date.substring(0, date.indexOf("·")).trim())
                .filter((date) -> (date.length() - date.replace(" ", "").length()) == 2)
                .map((date) -> {
                    try {
                        return format.parse(date);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        String minDate = format.format(dates.stream().min(Date::compareTo).get());
        String maxDate = format.format(dates.stream().max(Date::compareTo).get());

        minDateCourses = driver
                .findElements(By.xpath("//section/div/div/a[contains(@class, 'sc-zzdkm7-0')]"))
                .stream().map(WebElement::getText)
                .filter((plate) -> plate.contains(minDate)).map((plate) -> {
                    String text = plate.split("\n")[1];
                    if (text.contains("Успеть!") || text.contains("Скидка")) {
                        return plate.split("\n")[2];
                    } else {
                        return text;
                    }
                }).toList();

        maxDateCourses = driver
                .findElements(By.xpath("//section/div/div/a[contains(@class, 'sc-zzdkm7-0')]"))
                .stream().map(WebElement::getText)
                .filter((plate) -> plate.contains(maxDate)).map((plate) -> {
                    String text = plate.split("\n")[1];
                    if (text.contains("Успеть!") || text.contains("Скидка")) {
                        return plate.split("\n")[2];
                    } else {
                        return text;
                    }
                }).toList();
        return this;
    }

    public void nameAndDateOnMinMaxPlateCoursesIsRight() {
        Document document = null;
        try {
            document = Jsoup.connect("https://otus.ru").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements courseNames = document.select("h6 > div.sc-hrqzy3-1 jEGzDf");
        Elements courseDates = document.select("a > div:nth-child(2) > div > div");
        String minDateCourse = courseNames.get(0).text();
        String minDateJsoup = courseNames.get(0).text();
        if (!(minDateCourses.contains(minDateCourse) && (minDateJsoup.contains(minDate)))) {
            throw new RuntimeException("Course with min date is not the first course");
        }
        String maxDateCourse = courseNames.get(courseNames.size() - 1).text();
        String maxDateJsoup = courseNames.get(courseNames.size() - 1).text();
        if (!(maxDateCourses.contains(maxDateCourse) && (maxDateJsoup.contains(maxDate)))) {
            throw new RuntimeException("Course with max date is not the last course");
        }
    }

    public CoursesCataloguePage checkCatalogueUrl() {
        if (category != null) {
            String param = switch (category) {
                case "Программирование" -> "programming";
                case "Архитектура" -> "architecture";
                case "Data Science" -> "data-science";
                case "Инфраструктура" -> "operations";
                case "GameDev" -> "gamedev";
                case "Безопасность" -> "information-security-courses";
                case "Управление" -> "marketing-business";
                case "Аналитика и анализ" -> "analytics";
                case "Тестирование" -> "testing";
                case "Корпоративные курсы" -> "corporate";
                case "IT без программирования" -> "it-bez-programmirovanija";
                case "OTUS Kids" -> "kids";
                case "Специализации" -> "specialization";
                default -> "programming";
            };
            String urlShouldBe = String.format("https://otus.ru/catalog/courses?categories=%s", param);
            String urlBrowser = driver.getCurrentUrl();
            if (!urlShouldBe.equals(urlBrowser)) {
                try {
                    throw new Exception("Url is not right");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return this;
    }

    @FindBy(xpath = "//div[text()='Каталог']")
    private WebElement header;

    @FindBy(xpath = "//*[text()='Направление']/../following-sibling::div//input")
    private List<WebElement> checkBoxInputs;

    public CoursesCataloguePage checkCatalogPageVisibility(){
        Assertions.assertThat(waiters.waitForElementVisible(header)).isTrue();
        return this;
    }

    public CoursesCataloguePage checkCheckBoxInput(int index, boolean isChecked){
        WebElement checkBoxInput = checkBoxInputs.get(--index);
        Assertions.assertThat(isChecked).isEqualTo(checkBoxInput.isSelected());
        return this;
    }
}
