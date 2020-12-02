package stepDefinitionPackage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;

import pageObjectsPackage.AccountHomePage;
import pageObjectsPackage.CalendarPage;
import pageObjectsPackage.CompanyPage;
import pageObjectsPackage.ContactsPage;
import pageObjectsPackage.DealsPage;
import pageObjectsPackage.LoginPage;
import pageObjectsPackage.TasksPage;

public class StepDefnClass {
	
	WebDriver driver;
	LoginPage lp;
	AccountHomePage ap;
	ContactsPage cp;
	DealsPage dp;
	TasksPage tp;
	CalendarPage clp;
	CompanyPage cmp;
	
	@Before // Setting up Driver
	public void driverSetup() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@After // Closing Driver
	public void driverClose() {
		driver.close();
	}
	
	@Given("^User is in Login Page$")
    public void user_is_in_login_page() throws Throwable {
        lp = new LoginPage(driver);
        Assert.assertEquals(lp.getExpectedTitle(), lp.getActualTitle()); // Checking if we're in login page
        System.out.println("User in login");
    }

    @Given("^User is in profile page$")
    public void user_is_in_profile_page() throws Throwable {
    	ap = new AccountHomePage(driver);
        Assert.assertEquals(ap.getExpectedTitle(), ap.getActualTitle()); // Checking if we're in profile page
        System.out.println("User in profile");
    }

    @When("^User enters email (.*)$") // use * to accept null values
    public void user_enters_email(String user) throws Throwable {
    	lp.getEmail().sendKeys(user);
    	System.out.println("User entered invalid email");
        
    }
    
    @When("^User enters valid Email \"([^\"]*)\"$")
    public void user_enters_valid_email_something(String strArg1) throws Throwable {
    	lp.getEmail().sendKeys(strArg1);
    	System.out.println("User entered valid email");
    }

    @When("^User clicks Company tab$")
    public void user_clicks_company_tab() throws Throwable {
        ap.getCompany().click();
        Thread.sleep(2000);
        cmp = new CompanyPage(driver);
        System.out.println("User Clicked Company");
    }

    @When("^User clicks Contacts tab$")
    public void user_clicks_contacts_tab() throws Throwable {
        ap.getContacts().click();
        Thread.sleep(2000);
        cp = new ContactsPage(driver);
        System.out.println("User Clicked Contacts");
    }

    @When("^User clicks Deals tab$")
    public void user_clicks_deals_tab() throws Throwable {
        ap.getDeals().click();
        Thread.sleep(2000);
        dp = new DealsPage(driver);
        System.out.println("User Clicked Deals");
    }

    @When("^User clicks Tasks tab$")
    public void user_clicks_tasks_tab() throws Throwable {
        ap.getTasks().click();
        Thread.sleep(2000);
        tp = new TasksPage(driver);
        System.out.println("User Clicked Tasks");
    }

    @When("^User clicks Calendar tab$")
    public void user_clicks_Calendar_tab() throws Throwable {
        ap.getCalendar().click();
        Thread.sleep(2000);
        clp = new CalendarPage(driver);
        System.out.println("User Clicked Calendar");
    }

    @Then("^Invalid Login message is displayed$")
    public void invalid_login_message_is_displayed() throws Throwable {
    	Assert.assertEquals(lp.getExpectedErrorMessage(), lp.getActualErrorMessage().getText());
    	System.out.println("Invalid Login message displayed");
    }

    @Then("^User logs in successfully$")
    public void user_logs_in_successfully() throws Throwable {
    	 Assert.assertEquals(lp.getExpectedTitle(), lp.getActualTitle());
    	 System.out.println("Login Successful");
    }

    @Then("^User is redirected to profile page successfully$")
    public void user_is_redirected_to_profile_page_successfully() throws Throwable {
        ap = new AccountHomePage(driver);
        Assert.assertEquals(ap.getExpectedTitle(), ap.getActualTitle());
        System.out.println("User is directed to profile page");
    }

    @Then("^New company is successfully created$")
    public void new_company_is_successfully_created() throws Throwable {
    	Assert.assertEquals(cmp.getExpectedCompanyName(), ap.getActual());
    	System.out.println("Company created");
    }

    @Then("^Company Error Message is returned successfully$")
    public void company_error_message_is_returned_successfully() throws Throwable {
    	Assert.assertEquals(cmp.getExpectedCompanyErrorMessage(), cmp.getActualCompanyErrorMessage().getText());
    	System.out.println("Company error message");
    }

    @Then("^New contact is successfully created$")
    public void new_contact_is_successfully_created() throws Throwable {
    	Assert.assertEquals(cp.getExpectedContact(), ap.getActual());
    	System.out.println("Contact created");
    }

    @Then("^Contact Error Message is returned successfully$")
    public void contact_error_message_is_returned_successfully() throws Throwable {
    	Assert.assertEquals(cp.getExpectedFirstErrorMessage(), cp.getActualFirstErrorMessage().getText());
		Assert.assertEquals(cp.getExpectedLastErrorMessage(), cp.getActualLastErrorMessage().getText());
		System.out.println("Contact Error Message");
    }

    @Then("^New deal is successfully created$")
    public void new_deal_is_successfully_created() throws Throwable {
    	Assert.assertEquals(dp.getExpectedDeal(), ap.getActual());
    	System.out.println("Deal created");
    }

    @Then("^Deal error message is returned$")
    public void deal_error_message_is_returned() throws Throwable {
    	Assert.assertEquals(dp.getExpectedDealErrorMessage(), dp.getActualDealErrorMessage().getText());
    	System.out.println("Deal Error Message");
    }

    @Then("^New task is successfully created$")
    public void new_task_is_successfully_created() throws Throwable {
    	Assert.assertEquals(tp.getExpectedTask(), ap.getActual());
    	System.out.println("Task created");
    }

    @Then("^Task Error Message is returned$")
    public void task_error_message_is_returned() throws Throwable {
    	Assert.assertEquals(tp.getExpectedTaskErrorMessage(), tp.getActualTaskErrorMessage().getText());
    	System.out.println("Task error message");
    }

    @Then("^User can view created tasks on calendar successfully$")
    public void user_can_view_created_tasks_on_calendar_successfully() throws Throwable {
    	Thread.sleep(2000);
    	clp.refresh();
		WebDriverWait wait = new WebDriverWait(driver,30);					
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div/div")));
		Assert.assertEquals(clp.getExpectedCalendarTask(), clp.getActualCalendarTask());
		System.out.println("Calendar viewed");
    }

    @And("^User enter password (.*)$") // * to allow for 0 or more
    public void user_enter_password(String pass) throws Throwable {
        lp.getPassword().sendKeys(pass);
        System.out.println("Invalid password entered");
    }
    
    @And("^User enter valid Password \"([^\"]*)\"$")
    public void user_enter_valid_password_something(String strArg1) throws Throwable {
    	lp.getPassword().sendKeys(strArg1);
    	System.out.println("Valid Password Entered");
    }

    @And("^User clicks Login$")
    public void user_clicks_login() throws Throwable {
        lp.getLogin().click();
        System.out.println("Login clicked");
    }

    @And("^User clicks New Company$")
    public void user_clicks_new_company() throws Throwable {
        cmp.getNewCompanyButton().click();
        Thread.sleep(2000);
        System.out.println("Company Clicked");
    }

    @And("^User enters Company Name \"([^\"]*)\"$")
    public void user_enters_company_name_something(String strArg1) throws Throwable {
        cmp.getCompanyName().sendKeys(strArg1);
        Thread.sleep(2000);
        System.out.println("Company name entered");
    }

    @And("^User clicks save$")
    public void user_clicks_save() throws Throwable {
        ap.getSaveButton().click();
        System.out.println("Save Clicked");
        Thread.sleep(2000);
    }

    @And("^User clicks New Contact$")
    public void user_clicks_new_contact() throws Throwable {
        cp.getNewContactButton().click();
        Thread.sleep(2000);
        System.out.println("Contact Clicked");
    }

    @And("^User enters First Name \"([^\"]*)\"$")
    public void user_enters_first_name_something(String strArg1) throws Throwable {
        cp.getFirstName().sendKeys(strArg1);
        System.out.println("First Name Entered");
    }

    @And("^User enters Last Name \"([^\"]*)\"$")
    public void user_enters_last_name_something(String strArg1) throws Throwable {
        cp.getLastName().sendKeys(strArg1);
        System.out.println("Last Name Entered");
    }

    @And("^User clicks New Deal$")
    public void user_clicks_new_deal() throws Throwable {
        dp.getNewDealButton().click();
        Thread.sleep(2000);
        System.out.println("New Deal Clicked");
    }

    @And("^User enters deal Title \"([^\"]*)\"$")
    public void user_enters_deal_title_something(String strArg1) throws Throwable {
        dp.getDealTitle().sendKeys(strArg1);
        System.out.println("Deal title entered");
    }

    @And("^User clicks New Task$")
    public void user_clicks_new_task() throws Throwable {
        tp.getNewTaskButton().click();
        Thread.sleep(2000);
        System.out.println("Task Clicked");
    }

    @And("^User enters task Title \"([^\"]*)\"$")
    public void user_enters_task_title_something(String strArg1) throws Throwable {
    	tp.getTaskTitle().sendKeys(strArg1);
    	System.out.println("Task title entered");
    }

    @And("^User enters due date \"([^\"]*)\"$")
    public void user_enters_due_date_something(String strArg1) throws Throwable {
        tp.getDueDate().sendKeys(strArg1);
        System.out.println("Due date entered");
    }

    @But("^Credentials are invalid$")
    public void credentials_are_invalid() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui']/div/div/form/div/div[3]/p")));
		Assert.assertEquals(lp.getExpectedErrorMessage(), lp.getActualErrorMessage().getText());
		System.out.println("Credentials invalidity checked");
    }

}