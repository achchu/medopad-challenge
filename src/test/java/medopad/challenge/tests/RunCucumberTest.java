package medopad.challenge.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * JUnit runner class for running the tests
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"medopad/challenge/tests/stepdefs"}, plugin = {"pretty", "html:target/html"})
public class RunCucumberTest {
}
