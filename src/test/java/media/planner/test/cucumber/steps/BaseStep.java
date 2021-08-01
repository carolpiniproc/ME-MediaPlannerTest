package media.planner.test.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import media.planner.test.driver.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import java.net.URL;
import java.util.logging.Logger;

public class BaseStep {

    final Logger log = Logger.getLogger(getClass().getSimpleName());

    @Before
    public void setUp() throws Exception {
        Driver.setup();
        System.out.println("UrlMediaPlannerBase: "+ System.getProperty("UrlMediaPlannerBase"));
        URL url = new URL(System.getProperty("UrlMediaPlannerBase"));
        log.info("Connecting to: " +  url.toString());
        Driver.setUrl(url.toString());
    }

    @After
    public void tearDown(){
        Driver.exit();
    }

    @ParameterType(name = "bool", value = "true|True|TRUE|false|False|FALSE")
    public Boolean bool(String value) {
        return Boolean.valueOf(value);
    }


}
