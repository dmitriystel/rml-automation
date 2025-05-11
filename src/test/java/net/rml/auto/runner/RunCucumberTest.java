package net.rml.auto.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"net.rml.auto.steps", "net.rml.auto.hooks"},
        plugin = {
                "pretty",
                "html:build/cucumber-reports",
                "json:build/cucumber.json"
        },
        monochrome = true
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    // This is required so Gradle & TestNG detect the scenarios to run
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}