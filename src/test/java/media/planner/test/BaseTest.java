package media.planner.test;

import media.planner.test.driver.Driver;
import net.bytebuddy.utility.RandomString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Logger log;

    @BeforeClass
    public void baseSetup() {
        //todo: consider supporting more browsers
        log = LogManager.getLogger(BaseTest.class);
        log.info("In before class");

        try {
            Driver.setup();
            driver = Driver.getDriver();
            wait = Driver.getWait();
        } catch (Exception e) {
            log.info("Driver setup failed");
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public static void waitForJStoLoad() {
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    @Deprecated
    public static void clickByJS(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();", element);
    }

    @Deprecated
    public static void selectDropDown(WebElement dropdown, String selection) {
        List<WebElement> options = dropdown.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText().equals(selection)) {
                option.click();
                break;
            }
        }
    }

    @Deprecated
    public static List<String> selectAllOptionsDropDown(WebElement dropdown) {
        List<WebElement> options = dropdown.findElements(By.tagName("li"));
        List<String> selections = new ArrayList<>();
        for (WebElement option : options) {
            if (!option.getText().equals("Select")) {
                option.click();
                selections.add(option.getText());
            }
        }
        return selections;
    }


    @Deprecated
     public boolean verifyDropdownElementIsDisabled(WebElement dropdown, String selection) {
         List<WebElement> options = dropdown.findElements(By.tagName("li"));
         for (WebElement option : options) {
             if (option.getText().equals(selection)) {
                 if (option.getAttribute("class").contains("jss60") ){
                     return true;
                 }
             }
         }
         return false;
     }

    @Deprecated
    public boolean checkElementPresent(WebElement element) {
        try {
            if (element.isDisplayed())
                return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Deprecated
    public static void blur(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].dispatchEvent(new Event('blur'))", element);
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

    @Deprecated
    public String randomString() {
        return RandomString.make().toLowerCase();
    }

}
