package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends BasePage {

    @FindBy(id = "breadcrumb-0")
    WebElement clientName;

    @FindBy(id = "breadcrumb-1")
    WebElement engagementName;

    @FindBy(xpath = "//*/div/div/div/main/div[2]/div/button")
    WebElement switchAccountButton;

    @FindBy(id = "Client-select")
    WebElement clientDropdown;

    @FindBy(id = "cascade-my-cascading-selects-switch-button")
    WebElement switchButton;

    @FindBy(id = "Client-select-menu-list")
    WebElement clientDropdownlist;

    @FindBy(id = "Engagement-select")
    WebElement selectEngagement;

    @FindBy(id = "Engagement-select-menu-list")
    WebElement engagementMenu;

    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getClientName() {
        return clientName;
    }

    public WebElement getEngagementName() {
        return engagementName;
    }

    public WebElement getSwitchAccountButton() {
        return switchAccountButton;
    }

    public WebElement getClientDropdown() {
        return clientDropdown;
    }

    public WebElement getSwitchButton() {
        return switchButton;
    }

    public WebElement getClientDropdownList() {
        return clientDropdownlist;
    }
    public WebElement getSelectEngagement() {
        return selectEngagement;
    }

    public WebElement getEngagementMenu() {
        return engagementMenu;
    }
}
