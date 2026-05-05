package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import POJO.NewCandidateData;
import Pages.OrangeHRMHomePage;
import Pages.OrangeHRMLoginPage;
import Pages.OrangeHRMRecruitmentPage;
import Utils.JavaUtils;
import Utils.TestDataManager;
import io.cucumber.java.en.*;
import io.cucumber.messages.types.Duration;
import junit.framework.Assert;

public class OHRMRecruitmentSteps {
	
	WebDriver driver;
	
	OrangeHRMRecruitmentPage recruitment;
	
	public OHRMRecruitmentSteps()
	{
		driver = Hooks.driver;
	}
	
	@When("user clicks on recruitment tab")
	public void user_clicks_on_recruitment_tab()
	{
		recruitment = new OrangeHRMRecruitmentPage(driver);
		recruitment.clickRecruitmentTab();
	}

	@And("user clicks on add button")
	public void user_clicks_on_add_button()
	{
		recruitment.clickAddCandidateButton();
	}
	
	@And("user enters candidate deatils for {string} and clicks submit")
	public void candidateCreation(String role) throws InterruptedException
	{
		NewCandidateData data = TestDataManager.getCandidateDetails(role);
		recruitment.enterFirstName(data.getFirstName());
		recruitment.enterLastName(data.getLastName());
		recruitment.selectVacancy(data.getRole());
		recruitment.enterEmailID(data.getEmail());
		recruitment.clickSubmit();
		
	}
	
	@Then("under recruitment tab a new record is created {string} with current date")
	public void verifyCurrentDateOnRecord(String val)
	{
		recruitment.clickRecruitmentTab();
		String tableDate = recruitment.fetchDateOfCreation(val);
		String currentDate = JavaUtils.fetchCurrentDateYYYYDDMM();
		System.out.println("Java current date is : " +currentDate);
		Assert.assertEquals(tableDate, currentDate);
		
	}
	@When("user clicks on actions button from records table for {string}")
	public void clickActions(String value) throws InterruptedException
	{
		recruitment.clickRecruitmentTab();
		recruitment.clickOnActions(value);
	}
	@And("shortlists the candidate")
	public void shortlistCandidate() throws InterruptedException
	{
		
		recruitment.clickShortList();
		recruitment.addANote("shortlisted");
		recruitment.clickSave();
	}
	@And("schedules an interview with {string} and {string}")
	public void scheduleInterview(String date, String time) throws InterruptedException
	{
		recruitment.clickScheduleInterview();
		System.out.println("clicked scedule");
		
		recruitment.enterInterviewTitle("Tech Round1");
		System.out.println("entered title");
		recruitment.selectInterviewer();
		System.out.println("selected interviewer");
		recruitment.selectInterviewDate(date);
		System.out.println("selected date");
		recruitment.selectInterviewTime(time);
		recruitment.addANote("join on time");
		recruitment.clickSave();
	}
	@And("marks the interview as pass")
	public void passInterview()
	{
		recruitment.clickInterviewPass();
		recruitment.addANote("cleared L1");
		recruitment.clickSave();
	}
	@Then("user is able to offer job and application status changes to {string}")
	public void verifyHireMsg(String status)
	{
		recruitment.clickOfferJob();
		recruitment.addANote("offered L1");
		recruitment.clickSave();
		recruitment.clickHireBtn();recruitment.addANote("hired");
		recruitment.clickSave();
		recruitment.checkHireMsg(status);
		
	}
}
