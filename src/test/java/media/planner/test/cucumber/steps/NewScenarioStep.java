package media.planner.test.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import media.planner.test.BaseTest;
import media.planner.test.driver.Driver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import pages.MyScenariosPage;
import pages.NewScenarioPage;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class NewScenarioStep {
    Logger log = Logger.getLogger(getClass().getSimpleName());
    MyScenariosPage myScenariosPage = new MyScenariosPage(Driver.getDriver());
    NewScenarioPage newScenarioPage = new NewScenarioPage(Driver.getDriver());
    Wait wait = Driver.getWait();

    @Given("the planner tab is clickable")
    public void the_planner_tab_is_clickable() {
        wait.until(ExpectedConditions.elementToBeClickable(myScenariosPage.getPlannerTab()));
        myScenariosPage.getPlannerTab().click();
    }

    @When("the user clicks on create scenario button")
    public void the_user_clicks_on_create_scenario_button() {
        wait.until(ExpectedConditions.elementToBeClickable(myScenariosPage.getCreateScenarioButton()));
        myScenariosPage.getCreateScenarioButton().click();
    }

    @Then("assert that label is displayed with text {string}")
    public void assert_that_label_is_displayed_with_text(String text) {
        Assert.assertTrue(newScenarioPage.getCreateScenarioLabel().isDisplayed());
        Assert.assertEquals(newScenarioPage.getCreateScenarioLabel().getText(), text );
    }

    @Given("conv target field is displayed")
    public void conv_target_field_is_displayed() throws InterruptedException {
        Assert.assertTrue(newScenarioPage.getConvTargetField().isDisplayed());
        log.info("Conversion Target field is present");
        Thread.sleep(3000);
    }

    @When("user selects all KPIs")
    public void user_selects_all_KPIs() {
        wait.until(ExpectedConditions.elementToBeClickable(newScenarioPage.getOpenKpiDropdown()));
        newScenarioPage.getOpenKpiDropdown().click();
        wait.until(ExpectedConditions.visibilityOf(newScenarioPage.getKpiList()));
        List<String> selectedKPIs = BaseTest.selectAllOptionsDropDown(newScenarioPage.getKpiList());
        log.info("KPI list to be clicked" +  selectedKPIs);
        wait.until(ExpectedConditions.visibilityOf(newScenarioPage.getSelectedKPI()));
    }

    @Then("assert selected KPI text matches")
    public void assert_selected_KPI_text_matches() {
        String kpiName = newScenarioPage.getSelectedKPI().getText();
        log.info("KPI name clicked:" + kpiName);
        List<String> selectedKPIs = BaseTest.selectAllOptionsDropDown(newScenarioPage.getKpiList());
        String expectedKpi = selectedKPIs.stream().collect(Collectors.joining(", "));
        Assert.assertEquals(kpiName, expectedKpi);
        log.info("KPIs selected and verified");
    }

    @Then("assert budget is enabled")
    public void assert_budget_is_enabled() {
        Assert.assertTrue(newScenarioPage.getBudget().isEnabled());
        log.info("Budget field is enable");
    }

    @Then("conv target field is enabled")
    public void conv_target_field_is_enabled() {
        Assert.assertTrue(newScenarioPage.getConvTargetField().isEnabled());
        log.info("Conversion target field is disable");
    }
}
