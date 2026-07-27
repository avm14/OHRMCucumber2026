package Test_Runner_UI;

import org.junit.runner.*;
import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(
features="src/test/resources/Features/UI suite/OHRMSubmitClaim.feature", glue="StepDefinitions",
monochrome = true,
plugin = {"pretty","json:target/Reports/report.json",
		   "junit:target/Reports/report.xml",
		   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		   }
)
public class OHRMSubmitClaimTest {
	

}
