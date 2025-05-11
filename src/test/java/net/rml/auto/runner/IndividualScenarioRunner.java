package net.rml.auto.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"net.rml.auto.steps", "net.rml.auto.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/individual-html",
                "json:target/cucumber-reports/individual.json"
        },
        monochrome = true,
        tags = "@smoke"
)
public class IndividualScenarioRunner extends AbstractTestNGCucumberTests {
        @Test(enabled = false)
        public void dummy() {
        }
}