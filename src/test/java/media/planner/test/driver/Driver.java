package media.planner.test.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import media.planner.test.cucumber.steps.LoginStep;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Driver {

    private static WebDriver driver;
    private static Dimension dimension;
    private static WebDriverWait wait;

    public static void setup() throws Exception {
        System.out.println("Initializing driver setup...");
        System.out.println("# Configuration:");
        System.out.println("- driverClass: "+ System.getProperty("driverClass"));
        System.out.println("- timeOutInSeconds: "+ System.getProperty("timeOutInSeconds"));
        System.out.println("- implicitWait: "+ System.getProperty("implicitWait"));
        System.out.println("- windowMaximize: "+ System.getProperty("windowMaximize"));
        System.out.println("- browserModeType: "+ System.getProperty("browserModeType"));

        Class<WebDriver> driverClass = (Class<WebDriver>) Class.forName(System.getProperty("driverClass"));
        long timeOutInSeconds = Long.parseLong(System.getProperty("timeOutInSeconds", "15"));
        long implicitWait = Long.parseLong(System.getProperty("implicitWait", "20"));
        Boolean windowMaximize = Boolean.parseBoolean(System.getProperty("windowMaximize", "false"));
        String browserModeType = System.getProperty("browserModeType","");

        WebDriverManager.getInstance(driverClass).setup();

        switch (browserModeType) {
            case "local":
                driver = driverClass.newInstance();
                break;
            case "remote":
                URL url = new URL("http://" + System.getProperty("remoteWebDriverHost") + "/wd/hub");
                System.out.println(url);
                driver = new RemoteWebDriver(url, new ChromeOptions());
                break;
            default:
                System.out.println("Invalid browserModeType");
                throw new RuntimeException();
        }

        if(windowMaximize) {
            driver.manage().window().maximize();
        }

        wait = new WebDriverWait(driver, timeOutInSeconds, 1000);
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }

    public static void exit()
    {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static void setUrl(String url){
        driver.get(url);
    }

    public static String getURL() {
        return driver.getCurrentUrl();
    }

    public static void setDimension(int x,int y){
        dimension = new Dimension(x,y);
        driver.manage().window().setSize(dimension);
    }

    public static WebElement waitClickableElement(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static WebElement waitVisibleElement(WebElement webElement){
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

}