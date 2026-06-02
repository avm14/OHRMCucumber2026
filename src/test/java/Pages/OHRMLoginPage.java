package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OHRMLoginPage {
	
	WebDriver driver;
	public OHRMLoginPage(WebDriver driver)
	{
		this.driver = driver;
	}

	By OHRM_username_TB = By.xpath("//input[@name='username']");
	By OHRM_password_TB = By.xpath("//input[@name='password']");
	By OHRM_login_Btn = By.xpath("//button[@type='submit']");
	
	
	public void enterCredentials(String username, String password)
	{
		driver.findElement(OHRM_username_TB).sendKeys(username);
		driver.findElement(OHRM_password_TB).sendKeys(password);
	}
	public void clickLogin()
	{
		driver.findElement(OHRM_login_Btn).click();
	}
}
