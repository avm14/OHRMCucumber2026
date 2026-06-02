package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OHRMHomePage {

	WebDriver driver;
	public OHRMHomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By OHRM_admin_tab = By.xpath("//a[contains(@href,'viewAdminModule')]");
	By ORHM_leave_tab = By.xpath("//a[contains(@href,'viewLeaveModule')]");
	By OHRM_systemUsers_heading = By.xpath("//*[text()='System Users']");
	By OHRM_leaveList_heading = By.xpath("//*[text()='Leave List']");
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
		driver.findElement(OHRM_systemUsers_heading).isDisplayed();
	}
	
	public void verifyLeaveListDisplayed()
	{
		driver.findElement(OHRM_leaveList_heading).isDisplayed();
	}
	
	public void verifyHomepage()
	{
		driver.findElement(OHRM_admin_tab).isDisplayed();
	}
}

