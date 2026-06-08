package Utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaUtils {


	By calenderMonth = By.xpath("//li[@class='oxd-calendar-selector-month']");
	By calenderYear = By.xpath("//li[@class='oxd-calendar-selector-year']"); 
	By calenderDropdownOptions = By.xpath("//li[contains(@class,'oxd-calendar-dropdown--option')]");
    By calenderDates = By.xpath("//div[contains(@class,'oxd-calendar-date-wrapper')]/div");
    By loadPageSpinner = By.xpath("//div[@class='oxd-loading-spinner']");
 
	WebDriver driver;
	WebDriverWait wait;
	
	public JavaUtils(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public static String fetchCurrentDateYYYYDDMM()
	{
		String currentDate = LocalDate.now()
		                     .format(DateTimeFormatter.ofPattern("yyyy-dd-MM"));
		return currentDate;
	}
	public static String fetchCurrentDateYYYYMMDD()
	{
		String currentDate = LocalDate.now()
		                     .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return currentDate;
	}
	
	//This method helps select option by clicking. useful when select tag is not available
	public  void selectByVisibleText(By DDArrow, By optionList, String option)
	{
		System.out.println("Searching for: "+option);
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(DDArrow));
		driver.findElement(DDArrow).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(optionList));
		List<WebElement>options = driver.findElements(optionList);
		
		for(WebElement e: options)
		{
			if(e.getText().trim().equalsIgnoreCase(option.trim()))
			{
				System.out.println("Found: "+option);
				e.click();
				break;
			}
		}
	}
	
	//helps parseDate and get month year and date details using LocalDate Java class
	//using a list so that multiple input formats are supported
	public  LocalDate parseDate(String date) {
        List<DateTimeFormatter> formats = List.of(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("MMM dd yyyy")
        );

        for (DateTimeFormatter f : formats) {
            try {
                return LocalDate.parse(date, f);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Unsupported date format: " + date);
    }
	
	//Internally uses select by visible text to find and click the correct dates
	public  void selectDate(String dateStr) throws InterruptedException 
	{

        LocalDate date = parseDate(dateStr);

        //select month
        selectByVisibleText(calenderMonth, calenderDropdownOptions, String.valueOf(date.getMonth()));

        //select month
        selectByVisibleText(calenderYear,calenderDropdownOptions, String.valueOf(date.getYear()));

        // select day
        
        new WebDriverWait(driver, Duration.ofSeconds(20))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(calenderDates));
        
        List<WebElement>dates = driver.findElements(calenderDates);
        for(WebElement d: dates)
        {
        	if(d.getText().trim().equals(String.valueOf(date.getDayOfMonth())))
        	{
        		d.click();
        	}
        }
       
    }	
	
	//Wrapper method for synchronization 
	public void click(By locator)
	{
		    wait.until(ExpectedConditions.elementToBeClickable(locator));
		    driver.findElement(locator).click();
		   
	}
	public void type(By locator, String text)
	{
		 wait.until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).sendKeys(text);
	}
	
	public void clear(By locator)
	{
		    wait.until(ExpectedConditions.elementToBeClickable(locator));
		    driver.findElement(locator).clear();
		   
	}
	public void waitTillSpinnerDissappears()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadPageSpinner));
	}
}
