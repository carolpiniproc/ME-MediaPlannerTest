package media.planner.test.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import media.planner.test.driver.Driver;
import org.junit.Assert;
import pages.LoginPage;

import java.util.logging.Logger;

public class LoginStep {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    LoginPage loginPage;

    @Given("the user has access to \"Login Page\"")
    public void the_user_has_access_to() {
        loginPage = new LoginPage(Driver.getDriver());
        Driver.waitVisibleElement(loginPage.getSignInTitle());
        Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
    }

    @Given("the user login with {string} and {string}")
    public void the_user_login_with_and(String username, String password) {
        loginPage.login(username, password);
    }

    @Given("the user is logged in with {string} and {string}")
    public void the_user_is_logged_in_with_username_and_password(String username, String password) {
        LoginPage loginPage = new LoginPage(Driver.getDriver());
        Driver.waitVisibleElement(loginPage.getSignInTitle());
        Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
        loginPage.login(username, password);
    }

    @Then("the system validates login {bool}")
    public void the_system_validates_login(Boolean status) {
        if(status) {
            Driver.waitClickableElement(loginPage.getUsernamemenuButton());
            log.info("Logged in successfully with valid credentials");
            loginPage.getUsernamemenuButton().click();

            Driver.waitClickableElement(loginPage.getLogoutButton());
            loginPage.getLogoutButton().click();
            
            Assert.assertEquals(loginPage.getSignInTitle().getText(), "Sign in");
            log.info("Logged out successfully with valid credentials.");
        } else {
            Driver.waitVisibleElement(loginPage.getIncorrectCredentials());
            Assert.assertEquals(loginPage.getIncorrectCredentials().getText(), "Incorrect username or password.");
            log.info("Test with invalid credentials");
        }
    }

}
