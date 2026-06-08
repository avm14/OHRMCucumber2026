package Pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.JavaUtils;
import Utils.TableUtils;
import junit.framework.Assert;

public class OHRMClaimPage {

	WebDriver driver;
	JavaUtils utils; 
	TableUtils table;  
	
	public OHRMClaimPage(WebDriver driver)
	{
		this.driver = driver;	
		 this.utils = new JavaUtils(driver);
		 this.table  = new TableUtils(driver);
	}
	

	
	
	By claimMenu = By.xpath("//a[contains(@href,'viewClaimModule')]");
	By submitClaimTab = By.xpath("//a[contains(text(),'Submit Claim')]");
	By eventTypeDDArrow = By.xpath("//label[text()='Event']/ancestor::div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-text--after']");
	By eventOptions = By.xpath("//div[@role='option']/span");
	By currencyDDArrow = By.xpath("//label[text()='Currency']/ancestor::div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-text--after']");
	By currencyOptions = By.xpath("//div[@role='option']");
	By createClaimBtn = By.xpath("//button[@type='submit']");
	
	By refIDField = By.xpath("//label[text()='Reference Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	By statusField = By.xpath("//label[text()='Status']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	
	By addExpensesBtn = By.xpath("//*[text()='Expenses']/parent::div/button");
	By expenseTypeDDArrow = By.xpath("//label[text()='Expense Type']/ancestor::div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-text--after']");
	By expenseTypeOptions = By.xpath("//div[@role='option']/span");
	By expenseDate = By.xpath("//i[contains(@class,'oxd-icon bi-calendar')]");
	By expenseAmountTB = By.xpath("//label[text()='Amount']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	By saveExpenseBtn = By.xpath("//button[@type='submit']");
	
	By expenseTableColumnHeaders = By.xpath("//div[@role='columnheader']");
	By expenseTableRows = By.xpath("//div[@role='row']");
	
	By totalExpensesField = By.xpath("//p[contains(normalize-space(.),'Total Amount')]");
	By addAttachmentsBtn = By.xpath("//*[text()='Attachments']/parent::div/button");
	By browseAttachmentBtn = By.xpath("//div[text()='Browse']");
	By submitClaimBtn = By.xpath("//button[text()=' Submit ']");
	
	By myClaimsTab = By.xpath("//a[text()='My Claims']");
	By claimRecordsTableCoumnHeaders = By.xpath("//div[@role='columnheader']");
	By claimRecordsTableRows = By.xpath("//div[@role='row']");
	
	By loadPageSpinner = By.xpath("//div[@class='oxd-loading-spinner']");
	
	public void clickClaimMenu()
	{
		driver.findElement(claimMenu).click();
	}
	public void clickSubmitClaimTab()
	{
		driver.findElement(submitClaimTab).click();
	}
	public void selectClaimTypeFromDropdown(String option)
	{
	
		utils.selectByVisibleText(eventTypeDDArrow, eventOptions, option);
	}
	
	public void selectCurrencyFromDropdown(String option)
	{
		utils.selectByVisibleText(currencyDDArrow,currencyOptions,option);
	}
	
	public void clickCreateClaim()
	{
		driver.findElement(createClaimBtn).click();
	}
	
	public String captureRefID()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadPageSpinner));
		
		String RfID=driver.findElement(refIDField).getAttribute("value").toString();
		return RfID;
	}
	
	public String captureStatus()
	{
		String status=driver.findElement(statusField).getAttribute("value").toString();
		return status;
	}
	
	public void clickAddExpense()
	{
		driver.findElement(addExpensesBtn).click();
	}
	public void selectExpenseType(String option)
	{
		utils.selectByVisibleText(expenseTypeDDArrow,expenseTypeOptions,option);
	}
	
	public void selectExpenseDate(String dateInput) throws InterruptedException
	{
		
		driver.findElement(expenseDate).click();
		utils.selectDate(dateInput);
	}
	
	public void enterExpenseAmount(String amount)
	{
		driver.findElement(expenseAmountTB).sendKeys(amount);
	}
	
	public void clicksaveExpense()
	{
		driver.findElement(saveExpenseBtn).click();
		
	}
	
	public void verifyExpenseRecord(String eventType, String date)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadPageSpinner));
		
		String recordDate= table.getCellValueUsingRowAndHeader(expenseTableColumnHeaders, "Date", expenseTableRows, eventType);
		
		Assert.assertEquals(recordDate, date);
		
		
	}
	public void clicksubmitClaim()
	{
		driver.findElement(submitClaimBtn).click();
	}
	public void verifyClaimRecord(String eventType, String RefID)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadPageSpinner));
		driver.findElement(claimMenu).click();
		String eventTypeoFRecord= table.getCellValueUsingRowAndHeader(claimRecordsTableCoumnHeaders, "Event Name", claimRecordsTableRows, RefID);
		Assert.assertTrue(eventTypeoFRecord.equalsIgnoreCase(eventTypeoFRecord));
	}
	
}
