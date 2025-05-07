package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/feature",plugin="json:target/jsonreports/cucumber-report.json",glue= {"stepDefinition"},tags= "@Regression") //,tags= "@DeletePlace" ,tags="@AddPlace"
public class TestRunner {
	
	
}
