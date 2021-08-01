package media.planner.test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.HeaderPage;
import utils.ExcelUtils;
import java.util.ArrayList;
import static media.planner.test.LoginBaseTest.logInClass;
import static org.testng.Assert.assertEquals;

@Deprecated
public class SwitchAccountTest extends BaseTest {
    HeaderPage header;

    @Test(priority = 6)
    public void switchAccount() throws Exception {
        logInClass();
        header = new HeaderPage(driver);

        SwitchAccountTestCase nespressoTestCase = switchAccount("Switch_Client_to_Nespresso", "DataSheet2");
        assertEquals(header.getClientName().getText(), nespressoTestCase.getClientName());
        assertEquals(header.getEngagementName().getText(), nespressoTestCase.getEngagementName());

        SwitchAccountTestCase kaiserTestCase = switchAccount("Switch_Client_to_Kaiser", "DataSheet2");
        assertEquals(header.getClientName().getText(), kaiserTestCase.getClientName());
        assertEquals(header.getEngagementName().getText(), kaiserTestCase.getEngagementName());
    }


    public SwitchAccountTestCase switchAccount(String testCaseName, String sheetName) throws Exception {
        ArrayList<String> arrayList = new ExcelUtils().getData(testCaseName, sheetName);
        SwitchAccountTestCase testCase = new SwitchAccountTestCase(arrayList.get(1), arrayList.get(2));

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(header.getSwitchAccountButton()));
        header.getSwitchAccountButton().click();

        wait.until(ExpectedConditions.elementToBeClickable(header.getClientDropdown()));
        header.getClientDropdown().click();
        wait.until(ExpectedConditions.elementToBeClickable(header.getClientDropdownList()));
        selectDropDown(header.getClientDropdownList(), testCase.getClientName());


        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(header.getSelectEngagement()));
        header.getSelectEngagement().click();
        wait.until(ExpectedConditions.elementToBeClickable(header.getEngagementMenu()));
        selectDropDown(header.getEngagementMenu(), testCase.getEngagementName());

        wait.until(ExpectedConditions.elementToBeClickable(header.getSwitchButton()));
        Thread.sleep(5000);
        header.getSwitchButton().click();
        Thread.sleep(3000);
        return testCase;
    }

    class SwitchAccountTestCase {
        String clientName;
        String engagementName;

        public SwitchAccountTestCase(String clientName, String engagementName) {
            this.clientName = clientName;
            this.engagementName = engagementName;
        }

        public String getClientName() {
            return clientName;
        }

        public String getEngagementName() {
            return engagementName;
        }
    }

}
