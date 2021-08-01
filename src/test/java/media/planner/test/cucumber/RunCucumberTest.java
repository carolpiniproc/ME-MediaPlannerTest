package media.planner.test.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "media.planner.test.cucumber.steps",
        tags = "",
        plugin = {"pretty", "html:build/cucumber/report.html"},
        snippets = SnippetType.CAMELCASE
)
public class RunCucumberTest {
}
