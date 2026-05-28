package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.JavaUtils;
import Utils.TableUtils;
import java.time.Duration;
import junit.framework.Assert;


public class OrangeHRMRecruitmentPage {

	WebDriver driver;
	
	
	public OrangeHRMRecruitmentPage(WebDriver driver)
	{
		this.driver = driver;
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
	//By interviewTimeHour = By.xpath("//div[@class='oxd-time-hour-input']/input");
	//By interviewTimeMinutes = By.xpath("//div[@class='oxd-time-minute-input']/input");
	//By interviewTimeAM=By.xpath("//input[@value='AM']");
	//By interviewTimePM=By.xpath("//input[@value='PM']");
	By interviewPassBtn = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--success']");
    By offerJobBtn = By.xpath("//button[text()=' Offer Job ']");
    By hireBtn = By.xpath("//button[text()=' Hire ']");
    By hiredStatusMsg = By.xpath("//*[text()='Status: Hired']");
    By loadingSpinner = By.xpath("//*[@class='oxd-loading-spinner']");
	
	public void clickRecruitmentTab()
	{
		driver.findElement(recruitmentTab).click();
	}
	
	public void clickAddCandidateButton()
	{
		driver.findElement(addCandidate_btn).click();
	}
	
	public void enterFirstName(String firstName)
	{
		driver.findElement(newCandidateFirstName_tb).sendKeys(firstName);
	}
	
	public void enterLastName(String lastName)
	{
		driver.findElement(newCandidateLastName_tb).sendKeys(lastName);
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
		driver.findElement(newCandidateEmail_tb).sendKeys(email);
	}
	
	public void clickSubmit()
	{
		driver.findElement(submit_btn).click();
	}
	
	public String fetchDateOfCreation(String name)
	{
		TableUtils utils = new TableUtils(driver);
		 String tableDate= utils.getCellValueUsingRowAndHeader(candidateRecordsTableHeader, "Date of Application",candidateRecordsTableRows, name );
		 
		 return tableDate;
		
	}
	
	public void clickOnActions(String candidateName, String candidateStatus) throws InterruptedException
	{
		TableUtils table = new TableUtils(driver);
		table.clickUsingMultipleRowKeysAndHeader(candidateRecordsTableHeader, "Actions",candidateRecordsTableRows, candidateName, candidateStatus);
		
	}
	
	public void clickShortList() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(candidateShortlistBtn)));
		
		driver.findElement(candidateShortlistBtn).click();
	
	}
	
	public void addANote(String note)
	{
		driver.findElement(addNoteTextArea).sendKeys(note);
	}
	
	public void clickSave()
	{
		driver.findElement(submitBtn).click();
	}
	public void clickScheduleInterview()
	{
		driver.findElement(scheduleInterviewBtn).click();
	}
	public void enterInterviewTitle(String title)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(interviewTitleTB)));
		driver.findElement(interviewTitleTB).clear();
		driver.findElement(interviewTitleTB).sendKeys(title);
	}
	
	public void selectInterviewer() throws InterruptedException
	{
		driver.findElement(interviewerTB).clear();
		driver.findElement(interviewerTB).click();
		driver.findElement(interviewerTB).sendKeys("a");
		//Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement loader = driver.findElement(By.xpath(interviewerSuggestionLoader));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		WebElement option = driver.findElement(By.xpath(firstInterviewerFromList));
		option.click();
	}
	public void selectInterviewDate(String dateInput) throws InterruptedException
	{
		JavaUtils utils = new JavaUtils();
		driver.findElement(interviewDateBox).click();
		utils.selectDate(driver, dateInput);
		
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
		driver.findElement(interviewTimeBox).click();
		driver.findElement(interviewTimeBox).clear();
		driver.findElement(interviewTimeBox).sendKeys(time);
		
	}
	
	public void clickInterviewPass()
	{
		driver.findElement(interviewPassBtn).click();
	}
	
	public void clickOfferJob()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
		driver.findElement(offerJobBtn).click();
	}
	public void clickHireBtn()
	{
		driver.findElement(hireBtn).click();
	}
	
	public void checkHireMsg(String statusMsg)
	{
		driver.findElement(By.xpath("//*[text()='"+statusMsg+"']"));
	
	}
	
}
