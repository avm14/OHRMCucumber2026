package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.util.Util;

import Utils.JavaUtils;
import Utils.TableUtils;
import java.time.Duration;
import junit.framework.Assert;


public class OHRMRecruitmentPage {

	WebDriver driver;
	JavaUtils utils;
	TableUtils table; 
	
	public OHRMRecruitmentPage(WebDriver driver)
	{
		this.driver = driver;
		this.utils = new JavaUtils(driver);
		this.table =  new TableUtils(driver);
	}
	
	
	By recruitmentTab = By.xpath("//a[contains(@href,'viewRecruitmentModule')]");
	By addCandidate_btn = By.xpath("//button[text()=' Add ']");
	By newCandidateFirstName_tb = By.xpath("//input[@name='firstName']");
	By newCandidateLastName_tb = By.xpath("//input[@name='lastName']");
	
	By vacancyListBox_dd = By.xpath("//div[@role='option']/span");
	
	By vacancyDD_arrow = By.xpath("//div[contains(@class,'oxd-select-text')]");
	By newCandidateEmail_tb = By.xpath("//label[text()='Email']/parent::div/following-sibling::div/input");
	By submit_btn = By.xpath("//button[@type='submit']");
	By recordsTable = By.xpath("//div[@class='oxd-table']");
	By candidateRecordsTableHeader = By.xpath("//div[@role='columnheader']");
	By candidateRecordsTableRows = By.xpath("//div[@role='row']");
	
	By candidateShortlistBtn = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--success']");
	By addNoteTextArea = By.xpath("//textarea[@placeholder='Type here']");
	By submitBtn = By.xpath("//button[@type='submit']");
	By scheduleInterviewBtn = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--success']");
	By interviewTitleTB = By.xpath("//label[text()='Interview Title']/parent::div/following-sibling::div/input");
	By interviewerTB = By.xpath("//input[@placeholder = 'Type for hints...']");
	String firstInterviewerFromList = "//*[@role='listbox']/div[1]";
	String interviewerSuggestionLoader = "//*[text()='Searching....']";
	By interviewDateBox=By.xpath("//input[@placeholder='yyyy-dd-mm']");
	By interviewTimeBox = By.xpath("//input[@placeholder='hh:mm']");
	By interviewPassBtn = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--success']");
    By offerJobBtn = By.xpath("//button[text()=' Offer Job ']");
    By hireBtn = By.xpath("//button[text()=' Hire ']");
    By hiredStatusMsg = By.xpath("//*[text()='Status: Hired']");
    By loadingSpinner = By.xpath("//*[@class='oxd-loading-spinner']");
	
	public void clickRecruitmentTab()
	{
		utils.click(recruitmentTab);	
	}
	
	public void clickAddCandidateButton()
	{
		utils.click(addCandidate_btn);
	}
	
	public void enterFirstName(String firstName)
	{
		utils.type(newCandidateFirstName_tb, firstName);
	}
	
	public void enterLastName(String lastName)
	{
		utils.type(newCandidateLastName_tb, lastName);
	}
	
	public void selectVacancy(String vacancy) throws InterruptedException
	{
		driver.findElement(vacancyDD_arrow).click();
		
		List<WebElement> list = driver.findElements(vacancyListBox_dd);
		for(WebElement e:list)
		{
			if(e.getText().equals(vacancy))
			{
				e.click();
				break;
			}
		}
	}
	
	public void enterEmailID(String email)
	{
		utils.type(newCandidateEmail_tb, email);
	}
	
	public void clickSubmit()
	{
		utils.click(submit_btn);
	}
	
	public String fetchDateOfCreation(String name)
	{
		
		 String tableDate= table.getCellValueUsingRowAndHeader(candidateRecordsTableHeader, "Date of Application",candidateRecordsTableRows, name );
		 
		 return tableDate;
		
	}
	
	public void clickOnActions(String candidateName, String candidateStatus) throws InterruptedException
	{
		table.clickUsingMultipleRowKeysAndHeader(candidateRecordsTableHeader, "Actions",candidateRecordsTableRows, candidateName, candidateStatus);
		
	}
	
	public void clickShortList() throws InterruptedException
	{
		utils.click(candidateShortlistBtn);
	}
	
	public void addANote(String note)
	{
		utils.type(addNoteTextArea, note);
	}
	
	public void clickSave()
	{
		utils.click(submitBtn);
	}
	public void clickScheduleInterview()
	{
		utils.click(scheduleInterviewBtn);
	}
	public void enterInterviewTitle(String title)
	{
		utils.clear(interviewTitleTB);
		utils.type(interviewTitleTB,title);
	}
	
	public void selectInterviewer() throws InterruptedException
	{
		utils.clear(interviewerTB);
		utils.click(interviewerTB);
		utils.type(interviewerTB, "a");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement loader = driver.findElement(By.xpath(interviewerSuggestionLoader));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		WebElement option = driver.findElement(By.xpath(firstInterviewerFromList));
		option.click();
	}
	public void selectInterviewDate(String dateInput) throws InterruptedException
	{
		utils.click(interviewDateBox);
		utils.selectDate( dateInput);
		
	}
	
	//Another way of selecting time where the string is pharsed to split time elements
//	public void selectInterviewTime(String time)
//	{
//		// Split AM/PM
//	    String meridian = time.substring(time.length() - 2);
//
//	    // Remove AM/PM
//	    String timePart = time.substring(0, time.length() - 2);
//
//	    // Split hour and minute
//	    String[] parts = timePart.split(":");
//
//	    String hour = parts[0];
//	    String minutes = parts[1];
//	    
//	    driver.findElement(interviewTimeHour).sendKeys(hour);
//	    driver.findElement(interviewTimeMinutes).sendKeys(minutes);
//	    
//	    if(meridian=="AM") 
//	    {
//	    	driver.findElement(interviewTimeAM).click();
//	    }
//	    else if(meridian=="PM")
//	    {
//	    	driver.findElement(interviewTimePM).click();
//	    }
//	    
//	    
//	}
	
	public void selectInterviewTime(String time)
	{
		utils.click(interviewTimeBox);
		utils.clear(interviewTimeBox);
		utils.type(interviewTimeBox, time);
		
	}
	
	public void clickInterviewPass()
	{
		utils.click(interviewPassBtn);
	}
	
	public void clickOfferJob()
	{
		utils.waitTillSpinnerDissappears();
		utils.click(offerJobBtn);
	}
	public void clickHireBtn()
	{
		utils.click(hireBtn);
	}
	
	public void checkHireMsg(String statusMsg)
	{
		driver.findElement(By.xpath("//*[text()='"+statusMsg+"']"));
	
	}
	
}
