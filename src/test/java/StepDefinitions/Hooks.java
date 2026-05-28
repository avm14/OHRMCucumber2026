package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void browserSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @After(order=1)
    public void captureFailureSS(Scenario scenario)
    {
    	if(scenario.isFailed())
    	{
    		TakesScreenshot ts = (TakesScreenshot) driver;
    		byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
    		scenario.attach(screenshot, "image/png", "Failure screenshot");
    	}
    }
    
    @After(order = 0)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    
}