package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyScenariosPage extends BasePage {
    public MyScenariosPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "create-scenario-button")
    WebElement createScenarioButton;

    @FindBy(id = "sidebar-button-planner")
    WebElement plannerTab;

    public WebElement getPlannerTab() {
        return plannerTab;
    }

    @FindBy(xpath = "//*[@id=\"my-scenarios-table\"]/tbody/tr[1]")
    WebElement tableLine;

    public WebElement getTableLine() {
        return tableLine;
    }

    public WebElement getCreateScenarioButton() {
        return createScenarioButton;
    }

    @FindBy(xpath = "//*[@id=\"my-scenarios-table\"]/tbody")
    WebElement myScenariosTable;

    public WebElement getMyScenariosTable() {
        return myScenariosTable;
    }

    @FindBy(xpath = "//*[@id=\"menu-\"]/div[2]/ul")
    WebElement actionsDropDown;

    public WebElement getActionsDropDown() { return actionsDropDown; }

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/main/div[3]/div/div/div/div[2]/table/tbody")
    WebElement fullScenariosTable;

    public WebElement getFullTable() { return fullScenariosTable; }

    @FindBy(id = "page-header")
    WebElement pageTitle;

    public WebElement getPageTitle() { return pageTitle; }


}
