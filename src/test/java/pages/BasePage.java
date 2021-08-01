package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public WebDriver driver;

    public static final String baseUrl = System.getProperty("UrlMediaPlannerBase");

    @FindBy(xpath = "//button[contains(., 'Switch Account')]")
    WebElement usermenuButton;

    @FindBy(css = ".jss63")
    WebElement usernameMenuButton;

    @FindBy(xpath = "//li[contains(., 'Log out')]")
    WebElement logoutButton;

    @FindBy(id = "card-configuration")
    WebElement configurationCard;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsermenuButton() {
        return usermenuButton;
    }

    public WebElement getUsernamemenuButton() {
        return usernameMenuButton;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getConfigurationCard() {
        return configurationCard;
    }
}
