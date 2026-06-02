package Pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;

public class OHRMHomePage {

	WebDriver driver;
	public OHRMHomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By OHRM_admin_tab = By.xpath("//a[contains(@href,'viewAdminModule')]");
	By ORHM_leave_tab = By.xpath("//a[contains(@href,'viewLeaveModule')]");
	By OHRM_systemUsers_heading = By.xpath("//*[text()='System Users']");
	By OHRM_leaveList_heading = By.xpath("//*[text()='Leave Lists']");
	By OHRM_Recruitment_tab = By.xpath("//a[contains(@href,'viewRecruitmentModule')]");
	
	
	public void clickAdminTab()
	{
		driver.findElement(OHRM_admin_tab).click();
	}
	
	public void clickLeaveTab()
	{
		driver.findElement(ORHM_leave_tab).click();
	}
	
	public void clickRecruitmentTab()
	{
		driver.findElement(OHRM_Recruitment_tab).click();
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

