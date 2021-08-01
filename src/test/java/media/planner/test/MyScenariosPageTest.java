package media.planner.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MyScenariosPage;

import java.io.IOException;
import java.util.List;

import static media.planner.test.LoginBaseTest.logInClass;

@Deprecated
public class MyScenariosPageTest extends BaseTest {
    static MyScenariosPage myScenariosPage;

    @Test(priority = 2)
    public static void goToMyScenarios() throws IOException {
        logInClass();
        myScenariosPage = new MyScenariosPage(driver);
        wait.until(ExpectedConditions.visibilityOf(myScenariosPage.getPlannerTab()));
        boolean plannerSideBar = myScenariosPage.getPlannerTab().isDisplayed();
        Assert.assertTrue(plannerSideBar);
        log.info("Planner Visible");
        myScenariosPage.getPlannerTab().click();
        log.info("Planner tab clicked");
     }

    @Test(priority = 3)
    public void checkMyScenariosTableActions() {
        boolean type;
        List<WebElement> allRows = myScenariosPage.getMyScenariosTable().findElements(By.tagName("tr"));
        for(WebElement row : allRows) {
            wait.until((ExpectedConditions.visibilityOf(row)));
            type = row.findElement(By.xpath("td[2]")).isDisplayed();
                if (type)
                {
                    WebElement cell2 = row.findElement(By.xpath("td[2]"));
                    cell2.click();
                    Assert.assertTrue(checkElementPresent(cell2));
                    break;
                }
        }
    }
}
