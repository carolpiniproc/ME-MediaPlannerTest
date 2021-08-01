package media.planner.test.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import media.planner.test.BaseTest;
import media.planner.test.driver.Driver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pages.HeaderPage;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class SwitchAccountStep {

    Logger log = Logger.getLogger(getClass().getSimpleName());

    HeaderPage header = new HeaderPage(Driver.getDriver());
    Wait wait = Driver.getWait();

    @Given("the user clicks on the switch account button")
    public void the_user_clicks_on_the_switch_account_button() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(header.getClientName()));
        wait.until(ExpectedConditions.visibilityOf(header.getEngagementName()));
        wait.until(ExpectedConditions.elementToBeClickable(header.getSwitchAccountButton()));
        header.getSwitchAccountButton().click();
        wait.until(ExpectedConditions.visibilityOf(header.getClientDropdown()));
        wait.until(ExpectedConditions.visibilityOf(header.getSelectEngagement()));
    }

    @When("the user selects {string} client name")
    public void the_user_selects_client_name(String clientName) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(header.getClientDropdown()));
        header.getClientDropdown().click();
        wait.until(ExpectedConditions.visibilityOf(header.getClientDropdownList()));
        BaseTest.selectDropDown(header.getClientDropdownList(), clientName);
    }

    @When("the user selects {string} engagement name")
    public void the_user_selects_engagement_name(String engagementName) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(header.getSelectEngagement()));
        header.getSelectEngagement().click();
        wait.until(ExpectedConditions.visibilityOf(header.getEngagementMenu()));
        BaseTest.selectDropDown(header.getEngagementMenu(), engagementName);
    }

    @When("the user clicks on the switch button")
    public void the_user_clicks_on_the_switch_button() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(header.getSwitchButton()));
        header.getSwitchButton().click();
        Thread.sleep(5000);
    }

    @Then("header should have text {string} and {string}")
    public void header_should_have_text_clientName_and_engagementName(String clientName, String engagementName) {
        wait.until(ExpectedConditions.visibilityOf(header.getClientName()));
        assertEquals(header.getClientName().getText(), clientName);
        wait.until(ExpectedConditions.visibilityOf(header.getEngagementName()));
        assertEquals(header.getEngagementName().getText(), engagementName);
    }
}
