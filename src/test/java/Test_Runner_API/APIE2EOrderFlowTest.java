package Test_Runner_API;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features="src/test/resources/Features/API suite/EcomAPIPlaceOrder.feature", glue="StepDefinitions",
monochrome = true,
plugin = {"pretty","json:target/Reports/report.json",
		   "junit:target/Reports/report.xml",
		   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		   }
)
public class APIE2EOrderFlowTest {

}
