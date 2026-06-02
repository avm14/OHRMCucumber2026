package StepDefinitions;

import org.openqa.selenium.WebDriver;

import POJO.LoginData;
import Pages.OrangeHRMHomePage;
import Pages.OrangeHRMLoginPage;
import Utils.TestDataManager;
import io.cucumber.java.en.*;

public class OHRMBackgroundSteps {

	WebDriver driver;
	OrangeHRMLoginPage login;
	OrangeHRMHomePage home;
	
	public OHRMBackgroundSteps()
	{
		this.driver = Hooks.driver;
	}
	
	@Given("user is on OHRM login page")
	public void user_is_on_ohrm_login_page() {
	    
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		login = new OrangeHRMLoginPage(driver);
		home = new OrangeHRMHomePage(driver);
	}

	@When("user enters {string} credentials")
	public void user_enters_credentials(String user) {
		LoginData data = TestDataManager.getLoginData(user);
		login.enterCredentials(data.getUsername(), data.getPassword());
	}

	@When("clicks on login button")
	public void clicks_on_login_button() {
	    login.clickLogin();
	}

	@Then("user is navigated to the home page")
	public void user_is_navigated_to_the_home_page() {
		home.verifyHomepage();
	}

	@When("the user clicks on admin tab")
	public void the_user_clicks_on_admin_tab() {
	    home.clickAdminTab();
	}

	@Then("user is able to see employee search box")
	public void user_is_able_to_see_employee_search_box() {
	    home.verifysystemUsersDisplayed();
	}

	@When("the user clicks on leave tab")
	public void the_user_clicks_on_leave_tab() {
	    home.clickLeaveTab();
	}

	@Then("the user is able to see leave list")
	public void the_user_is_able_to_see_leave_list() {
	    home.verifyLeaveListDisplayed();
	}



}
