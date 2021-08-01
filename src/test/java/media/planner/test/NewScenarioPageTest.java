package media.planner.test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MyScenariosPage;
import pages.NewScenarioPage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import static media.planner.test.LoginBaseTest.logInClass;

@Deprecated
public class NewScenarioPageTest extends BaseTest {
    static MyScenariosPage myScenariosPage;
    static NewScenarioPage newScenarioPage;

    @Test(priority = 4)
    public void createNewScenario() throws IOException {
        logInClass();
        myScenariosPage = new MyScenariosPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(myScenariosPage.getPlannerTab()));
        myScenariosPage.getPlannerTab().click();
        wait.until(ExpectedConditions.elementToBeClickable(myScenariosPage.getCreateScenarioButton()));
        myScenariosPage.getCreateScenarioButton().click();
        newScenarioPage = new NewScenarioPage(driver);
        Assert.assertTrue(newScenarioPage.getCreateScenarioLabel().isDisplayed());
        Assert.assertEquals(newScenarioPage.getCreateScenarioLabel().getText(), "Scenario Planner - Create Scenario" );
        log.info("Create Scenario is present");
    }

    @Test(priority = 5)
    public void multipleKPIDisableConvTarget() throws InterruptedException {
        newScenarioPage = new NewScenarioPage(driver);
        Assert.assertTrue(newScenarioPage.getConvTargetField().isDisplayed());
        log.info("Conversion Target field is present");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(newScenarioPage.getOpenKpiDropdown()));
        newScenarioPage.getOpenKpiDropdown().click();
        wait.until(ExpectedConditions.visibilityOf(newScenarioPage.getKpiList()));
        List<String> selectedKPIs = selectAllOptionsDropDown(newScenarioPage.getKpiList());
        log.info("KPI list to be clicked", selectedKPIs);
        wait.until(ExpectedConditions.visibilityOf(newScenarioPage.getSelectedKPI()));
        String kpiName = newScenarioPage.getSelectedKPI().getText();
        log.info("KPI name clicked", kpiName);
        String expectedKpi = selectedKPIs.stream().collect(Collectors.joining(", "));
        Assert.assertEquals(kpiName, expectedKpi);
        log.info("KPIs selected and verified");
        Assert.assertTrue(newScenarioPage.getBudget().isEnabled());
        log.info("Budget field is enable");
        Assert.assertFalse(newScenarioPage.getConvTargetField().isEnabled());
        log.info("Conversion target field is disable");
    }
}
