package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.JavaUtils;

public class OHRMLoginPage {
	
	WebDriver driver;
	JavaUtils utils;
	public OHRMLoginPage(WebDriver driver)
	{
		this.driver = driver;
		this.utils = new JavaUtils(driver);
	}

	By OHRM_username_TB = By.xpath("//input[@name='username']");
	By OHRM_password_TB = By.xpath("//input[@name='password']");
	By OHRM_login_Btn = By.xpath("//button[@type='submit']");
	
	
	public void enterCredentials(String username, String password)
	{
		utils.type(OHRM_username_TB, username);
		utils.type(OHRM_password_TB, password);
		
	}
	public void clickLogin()
	{
		utils.click(OHRM_login_Btn);
	}
}
