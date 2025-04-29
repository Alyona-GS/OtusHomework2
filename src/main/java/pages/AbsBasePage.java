package pages;

import annotations.Path;
import exceptions.PathPageException;
import common.Common;
import org.openqa.selenium.*;

public abstract class AbsBasePage<T> extends Common {
    public String baseUrl = "https://otus.ru";//System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        super(driver);
        baseUrl = baseUrl;
    }

    public T open() {
        String path = getPath();
        if(path.isEmpty()) {
            throw new PathPageException();
        }
        driver.get(baseUrl + path);
        driver.manage().window().maximize();

        //By byBanner = By.xpath("//*[contains(@class, 'sticky-banner__close js-sticky-banner-close')]");
        By byBanner2 = By.xpath("//button[contains(@class, 'sc-bvhtwp-0 fyDiti')]");

        //this.waiters.waitForElementVisible(driver.findElement(byBanner));
        this.waiters.waitForElementVisible(driver.findElement(byBanner2));

        //findElement(byBanner).click();
        findElement(byBanner2).click();

        return (T)this;
    }

    public String getPath() {
        Class<? extends AbsBasePage> clazz = this.getClass();

        if(clazz.isAnnotationPresent(Path.class)) {
            Path path = clazz.getDeclaredAnnotation(Path.class);
            return path.value();
        } else {
            throw new PathPageException();
        }
    }

    // Draws a red border around the found element. Does not set it back anyhow.
    public WebElement findElement(By by) {
        WebElement elem = driver.findElement(by);
        // draw a border around the found element
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
        return elem;
    }

    // assuming JS is enabled
    private JavascriptExecutor js = (JavascriptExecutor)driver;
    private WebElement lastElem = null;
    private String lastBorder = null;

    private static final String SCRIPT_GET_ELEMENT_BORDER = "/*\n"
            + " * Returns all border properties of the specified element as String,\n"
            + " * in order of \"width style color\" delimited by ';' (semicolon) in the form of:\n"
            + " * \n"
            + " * \"2px inset #000000;2px inset #000000;2px inset #000000;2px inset #000000\"\n"
            + " * \"medium none #ccc;medium none #ccc;1px solid #e5e5e5;medium none #ccc\"\n"
            + " * etc.\n"
            + " */\n"
            + "var elem = arguments[0]; \n"
            + "if (elem.currentStyle) {\n"
            + "    // Branch for IE 6,7,8. No idea how this works on IE9, but the script\n"
            + "    // should take care of it.\n"
            + "    var style = elem.currentStyle;\n"
            + "    var border = style['borderTopWidth']\n"
            + "            + ' ' + style['borderTopStyle']\n"
            + "            + ' ' + style['borderTopColor']\n"
            + "            + ';' + style['borderRightWidth']\n"
            + "            + ' ' + style['borderRightStyle']\n"
            + "            + ' ' + style['borderRightColor']\n"
            + "            + ';' + style['borderBottomWidth']\n"
            + "            + ' ' + style['borderBottomStyle']\n"
            + "            + ' ' + style['borderBottomColor']\n"
            + "            + ';' + style['borderLeftWidth']\n"
            + "            + ' ' + style['borderLeftStyle']\n"
            + "            + ' ' + style['borderLeftColor'];\n"
            + "} else if (window.getComputedStyle) {\n"
            + "    // Branch for FF, Chrome, Opera\n"
            + "    var style = document.defaultView.getComputedStyle(elem);\n"
            + "    var border = style.getPropertyValue('border-top-width')\n"
            + "            + ' ' + style.getPropertyValue('border-top-style')\n"
            + "            + ' ' + style.getPropertyValue('border-top-color')\n"
            + "            + ';' + style.getPropertyValue('border-right-width')\n"
            + "            + ' ' + style.getPropertyValue('border-right-style')\n"
            + "            + ' ' + style.getPropertyValue('border-right-color')\n"
            + "            + ';' + style.getPropertyValue('border-bottom-width')\n"
            + "            + ' ' + style.getPropertyValue('border-bottom-style')\n"
            + "            + ' ' + style.getPropertyValue('border-bottom-color')\n"
            + "            + ';' + style.getPropertyValue('border-left-width')\n"
            + "            + ' ' + style.getPropertyValue('border-left-style')\n"
            + "            + ' ' + style.getPropertyValue('border-left-color');\n"
            + "}\n"
            + "// highlight the element\n"
            + "elem.style.border = '2px solid red';\n"
            + "return border;";
    private static final String SCRIPT_UNHIGHLIGHT_ELEMENT = "var elem = arguments[0];\n"
            + "var borders = arguments[1].split(';');\n"
            + "elem.style.borderTop = borders[0];\n"
            + "elem.style.borderRight = borders[1];\n"
            + "elem.style.borderBottom = borders[2];\n"
            + "elem.style.borderLeft = borders[3];";

    void highlightElement(WebElement elem) {
        unhighlightLast();

        // remember the new element
        lastElem = elem;
        lastBorder = (String)(js.executeScript(SCRIPT_GET_ELEMENT_BORDER, elem));
    }

    void unhighlightLast() {
        if (lastElem != null) {
            try {
                // if there already is a highlighted element, unhighlight it
                js.executeScript(SCRIPT_UNHIGHLIGHT_ELEMENT, lastElem, lastBorder);
            } catch (StaleElementReferenceException ignored) {
                // the page got reloaded, the element isn't there
            } finally {
                // element either restored or wasn't valid, nullify in both cases
                lastElem = null;
            }
        }
    }


}