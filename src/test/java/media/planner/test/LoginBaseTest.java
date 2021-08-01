package media.planner.test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.BasePage;
import pages.LoginPage;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.ArrayList;

@Deprecated
public class LoginBaseTest extends BaseTest {
    static LoginPage loginPage;

    public static void logInClass() throws IOException {
        driver.get(BasePage.baseUrl);
        waitForJStoLoad();
        ArrayList logindata;
        ExcelUtils data = new ExcelUtils();
        loginPage = new LoginPage(driver);
        logindata = data.getData("validLogin", "DataSheet1");
        wait.until(ExpectedConditions.visibilityOf(loginPage.getSignInTitle()));
        Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
        loginPage.login(logindata.get(1).toString(), logindata.get(2).toString());
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getUsernamemenuButton()));
        log.info("Log in Successfully");
    }
}
