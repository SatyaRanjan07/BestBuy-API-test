package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"StepDefinitions"},
        plugin = {"pretty","html:reports/CucumberReport.html"},
        dryRun = false,
        monochrome = true,
        tags = "@schema"
)


public class TestRunner extends AbstractTestNGCucumberTests {
}
