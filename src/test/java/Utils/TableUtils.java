package Utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableUtils {
	
	WebDriver driver;
	
	public TableUtils(WebDriver driver)
	{
		this.driver = driver;
	}

	
	public  int getColumnIndex(By headerLocator, String columnName)
	{
		List<WebElement> headers = driver.findElements(headerLocator);
		
		for(int i =0; i<headers.size();i++)
		{
			if(headers.get(i).getText().equalsIgnoreCase(columnName))
			{
				return i;
			}	
		}
		//Need this to handle a case where column header was not found
		throw new RuntimeException("Column not found: " + columnName);
	}
	
	public WebElement getRowUsingUniqueValue(By rowsLocator, String uniqueValue)
	{
		List<WebElement> rows = driver.findElements(rowsLocator);
		for(WebElement row: rows)
		{
			if(row.getText().contains(uniqueValue))
			{
				return row;
			}
		}
		throw new RuntimeException("Unique Value not found: " + uniqueValue);
	}
	
	
	public String getCellValueUsingRowAndHeader(By headerLocator, String columnName,By rowsLocator, String uniqueValue )
	{
		int columnID = getColumnIndex(headerLocator,columnName);
		WebElement row = getRowUsingUniqueValue(rowsLocator,uniqueValue);
		
		List<WebElement> cells = row.findElements(By.xpath(".//div[@role='cell']"));
		
		return cells.get(columnID).getText().trim();
	}
	
	public int getRowIndexUsingUniqueValue(By rowsLocator, String uniqueValue)
	{
		List<WebElement> rows = driver.findElements(rowsLocator);
		for(int i=0;i<rows.size();i++)
		{

			if(rows.get(i).getText().contains(uniqueValue))
			{
				return i;
			}
		}
		
		throw new RuntimeException("Unique Value not found: " + uniqueValue);
	}
	public int getRowIndexUsingUniqueValueSet(By rowsLocator, String uniqueValue1, String uniqueValue2)
	{
		List<WebElement> rows = driver.findElements(rowsLocator);
		for(int i=0;i<rows.size();i++)
		{

			if(rows.get(i).getText().contains(uniqueValue1) && rows.get(i).getText().contains(uniqueValue2))
			{
				return i;
			}
		}
		
		throw new RuntimeException("Unique Value not found: " + uniqueValue1 +" "+ uniqueValue2);
	}
	public void clickUsingRowAndHeader(By headerLocator, String columnName,By rowsLocator, String uniqueValue )
	{
		int columnID = getColumnIndex(headerLocator,columnName)+1;
		int rowID = getRowIndexUsingUniqueValue(rowsLocator, uniqueValue);

		driver.findElement(By.xpath("//div[@role='rowgroup' and @class='oxd-table-body']/div["+rowID+"]/div/div["+columnID+"]/descendant::button[1]")).click();

	}
	public void clickUsingMultipleRowKeysAndHeader(By headerLocator, String columnName,By rowsLocator, String uniqueValue1, String uniqueValue2 )
	{
		int columnID = getColumnIndex(headerLocator,columnName)+1;
		int rowID = getRowIndexUsingUniqueValueSet(rowsLocator, uniqueValue1, uniqueValue2);

		driver.findElement(By.xpath("//div[@role='rowgroup' and @class='oxd-table-body']/div["+rowID+"]/div/div["+columnID+"]/descendant::button[1]")).click();

	}
}
