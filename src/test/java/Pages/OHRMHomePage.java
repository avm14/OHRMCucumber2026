package Pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.JavaUtils;
import junit.framework.Assert;

public class OHRMHomePage {

	WebDriver driver;
	JavaUtils utils;
	public OHRMHomePage(WebDriver driver)
	{
		this.driver = driver;
		this.utils = new JavaUtils(driver);
	}
	
	By OHRM_admin_tab = By.xpath("//a[contains(@href,'viewAdminModule')]");
	By ORHM_leave_tab = By.xpath("//a[contains(@href,'viewLeaveModule')]");
	By OHRM_systemUsers_heading = By.xpath("//*[text()='System Users']");
	By OHRM_leaveList_heading = By.xpath("//*[text()='Leave List']");
	By OHRM_Recruitment_tab = By.xpath("//a[contains(@href,'viewRecruitmentModule')]");
	
	
	public void clickAdminTab()
	{
		utils.click(OHRM_admin_tab);
	}
	
	public void clickLeaveTab()
	{
		utils.click(ORHM_leave_tab);
	}
	
	public void clickRecruitmentTab()
	{
		utils.click(OHRM_Recruitment_tab);
	}
	
	public void verifysystemUsersDisplayed()
	{
		List<WebElement> element = driver.findElements(OHRM_systemUsers_heading);
		Assert.assertTrue(element.size()>0);;
		
	}
	
	public void verifyLeaveListDisplayed()
	{
		List<WebElement> element = driver.findElements(OHRM_leaveList_heading);
		Assert.assertTrue(element.size()>0);
	
	}
	
	public void verifyHomepage()
	{
		List<WebElement> element = driver.findElements(OHRM_admin_tab);
		Assert.assertTrue(element.size()>0);
		
	}
}

