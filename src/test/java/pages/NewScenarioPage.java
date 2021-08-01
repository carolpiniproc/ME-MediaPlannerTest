package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewScenarioPage extends BasePage {
    public NewScenarioPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "page-header")
    WebElement createScenarioLabel;

    public WebElement getCreateScenarioLabel() {
        return createScenarioLabel;
    }

    @FindBy(id = "conversion-target-field")
    WebElement convTargetField;

    public WebElement getConvTargetField() {
        return convTargetField;
    }

    @FindBy(id = "demo-mutiple-checkbox")
    WebElement kpiDropdown;

    public WebElement getKpiDropdown(){
        return kpiDropdown;
    }

    @FindBy(xpath ="//*[@id='demo-mutiple-checkbox']/parent::div")
    WebElement openKpiDropdown;

    public WebElement getOpenKpiDropdown(){
        return openKpiDropdown;
    }

    @FindBy(xpath = "//*[@id='demo-mutiple-checkbox']/preceding-sibling::div")
    WebElement selectedKPI;

    public WebElement getSelectedKPI(){
        return selectedKPI;
    }

    @FindBy(id ="standard-basic")
    WebElement scenarioName;

    public WebElement getScenarioName() { return scenarioName; }

    @FindBy(xpath = "//*[@id='menu-']/div[2]/ul")
    WebElement KpiList;

    public WebElement getKpiList() { return KpiList; }

    @FindBy(id = "budget-field")
    WebElement budget;

    public WebElement getBudget() { return budget; }

    @FindBy(xpath = ".//*[@role='tooltip']")
    WebElement convTargetTooltip;

    public WebElement getConvTargetTooltip() { return convTargetTooltip; }

    public WebElement getConDisabledTooltip() {
        WebElement conDisabledTooltip = driver.findElement(By.xpath("(.//*[@role='tooltip']//span)[1]"));
        return conDisabledTooltip;
    }

}
