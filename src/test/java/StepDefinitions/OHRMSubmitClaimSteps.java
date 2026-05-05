package StepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import Pages.OHRMClaimPage;
import io.cucumber.java.en.*;

public class OHRMSubmitClaimSteps {
	
	WebDriver driver;
	OHRMClaimPage claim;
	
	public OHRMSubmitClaimSteps()
	{
		this.driver = Hooks.driver;
	}
	
    String referenceID;
	
	 @When("user clicks on claim menu")
	    public void user_clicks_on_claim_menu() {
		    claim = new OHRMClaimPage(driver);
	        claim.clickClaimMenu();
	    }

	    @And("user navigates to submit claim tab")
	    public void user_navigates_to_submit_claim_tab() {
	        claim.clickSubmitClaimTab();
	    }

	    @When("user creates a claim with {string} and {string}")
	    public void user_creates_a_claim_with_event_type_and_currency(String eventType, String currency) throws InterruptedException {
	        claim.selectClaimTypeFromDropdown(eventType);
	        claim.selectCurrencyFromDropdown(currency);
	        claim.clickCreateClaim();
	    }

	    @Then("claim reference ID is created with {string} status")
	    public void claim_reference_id_is_created_with_status(String statusMsg) {
	    	
	    	   referenceID = claim.captureRefID();
	    	   System.out.println(referenceID + claim.captureStatus());
	        Assert.assertNotNull(referenceID);
	        Assert.assertEquals(statusMsg, claim.captureStatus());
	    }

	    @When("user adds an expense with {string} {string} and {string} and clicks on submit")
	    public void user_adds_an_expense_with_amount_and_date_and_clicks_on_submit(String expenseType, String amount,String date) throws InterruptedException
	    {
          claim.clickAddExpense();
         
          claim.selectExpenseType(expenseType);
          claim.enterExpenseAmount(amount);
          claim.selectExpenseDate(date);
          
          claim.clicksaveExpense();

	    }

	    @Then("a record is created in the expense table with given {string} and {string}")
	    public void a_record_is_created_in_the_expense_table_with_given_eventType_and_date(String eventType,String date)
	    {
	        claim.verifyExpenseRecord(eventType, date);
	    }

	    @When("user clicks on submit")
	    public void user_clicks_on_submit() {
	       claim.clicksubmitClaim();
	    }

	    @Then("a new record is created with the {string} in the claim menu")
	    public void a_new_record_is_created_with_the_reference_id_in_the_claim_menu(String eventType) 
	    {
	    	claim.verifyClaimRecord(eventType,referenceID);
	    }
	}
	
	

