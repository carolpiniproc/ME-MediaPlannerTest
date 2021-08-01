package media.planner.test.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import media.planner.test.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pages.MyScenariosPage;
import java.util.List;
import java.util.logging.Logger;

public class MyScenariosStep {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    MyScenariosPage myScenariosPage = new MyScenariosPage(Driver.getDriver());
    Wait wait = Driver.getWait();

    @Given("the planner tab is displayed")
    public void the_planner_tab_id_displayed() {
        wait.until(ExpectedConditions.visibilityOf(myScenariosPage.getPlannerTab()));
    }

    @When("click on planner tab")
    public void click_on_planner_tab() {
        myScenariosPage.getPlannerTab().click();
    }

    @When("my scenarios table is displayed")
    public void my_scenarios_table_is_displayed() {
        wait.until(ExpectedConditions.visibilityOf(myScenariosPage.getMyScenariosTable()));
    }

    @Then("validate if any row contains any action")
    public void validate_if_any_row_contains_any_action() {
        List<WebElement> allRows = myScenariosPage.getMyScenariosTable().findElements(By.tagName("tr"));
        for(WebElement row : allRows) {
            wait.until((ExpectedConditions.visibilityOf(row)));
            if (row.findElement(By.xpath("td[2]")).isDisplayed()) {
                WebElement cell2 = row.findElement(By.xpath("td[2]"));
                cell2.click();
                if(cell2.isDisplayed()) {
                    return;
                }
            }
        }
    }
}
