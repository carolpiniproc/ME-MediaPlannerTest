package media.planner.test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import utils.ExcelUtils;
import java.io.IOException;
import java.util.ArrayList;

@Deprecated
public class LoginPageTest extends BaseTest {
    public static LoginPage loginPage;
    public static ArrayList logindata;
    public static ExcelUtils data = new ExcelUtils();

    public static void setup() {
        driver.get(BasePage.baseUrl);
        waitForJStoLoad();
    }

    @Test(priority = 1)
    public static void logInOutTest() throws IOException {
        setup();
        loginPage = new LoginPage(driver);
        logindata = data.getData("validLogin", "DataSheet1");
        wait.until(ExpectedConditions.visibilityOf(loginPage.getSignInTitle()));
        Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
        loginPage.login(logindata.get(1).toString(), logindata.get(2).toString());
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getUsernamemenuButton()));
        log.info("Log in Successfully");
        loginPage.getUsernamemenuButton().click();
        loginPage.getLogoutButton().click();
        Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
        log.info("Logout Successfully");

    }

     @Test
     public void invalidCredentials() throws IOException {
        setup();
         loginPage = new LoginPage(driver);
         Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
         logindata = data.getData("InvalidLogin", "DataSheet1");
         loginPage.login(logindata.get(1).toString(), logindata.get(2).toString());
         wait.until((ExpectedConditions.visibilityOf(loginPage.getIncorrectCredentials())));
         Assert.assertEquals(loginPage.getIncorrectCredentials().getText(), "Incorrect username or password.");
         log.info("Invalid Credentials Tested successfully");

     }

     @Test
     public void forgotPasswordFlow() {
        setup();
         loginPage = new LoginPage(driver);
         Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
         loginPage.getForgotpasswordLink().click();
         Assert.assertEquals(loginPage.getResetPasswordTitle().getText(), "Reset password");
         String InvalidUsername = randomString();
         loginPage.getForgotPwdUsername().sendKeys(InvalidUsername);
         loginPage.getResetPasswordButton().click();
         log.info("Input invalid user on forget password");
         wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameError()));
         Assert.assertEquals(loginPage.getUsernameError().getText(), "Username " + "\"" + InvalidUsername + "\"" + " not found.");
         log.info(" Click on cancel button");
         loginPage.getForgotPwdCancelButton().click();
         Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
         log.info("Cancel forgot password");

     }
}
