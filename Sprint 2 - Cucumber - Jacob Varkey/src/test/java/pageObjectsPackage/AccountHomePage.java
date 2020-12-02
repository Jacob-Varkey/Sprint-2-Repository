package pageObjectsPackage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountHomePage {

	WebDriver driver;
	String expectedTitle = "Cogmento CRM";
	
	public AccountHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Company Tab
	@FindBy(xpath="//*[@id='main-nav']/a[4]")
	@CacheLookup
	WebElement company;
	
	//Contacts Tab
	@FindBy(xpath="//*[@id='main-nav']/a[3]")
	@CacheLookup
	WebElement contacts;	
	
	//Deals Tab
	@FindBy(xpath="//*[@id='main-nav']/a[5]")
	@CacheLookup
	WebElement deals;
	
	//Tasks Tab
	@FindBy(xpath="//*[@id='main-nav']/a[6]")
	@CacheLookup
	WebElement tasks;
	
	//Calendar
	@FindBy(xpath="//*[@id='main-nav']/a[2]/span")
	@CacheLookup
	WebElement calendar; 

	//Common Save Button
	@FindBy(xpath="//*[@id='dashboard-toolbar']/div[2]/div/button[2]") 
	@CacheLookup
	WebElement saveButton;
	
	@FindBy(xpath="//*[@id='dashboard-toolbar']/div[1]")
	@CacheLookup
	WebElement actual;	
	
	public String getExpectedTitle() {
		return expectedTitle;
	}
	
	public String getActualTitle() {
		return driver.getTitle();
	}
	
	public WebElement getCompany() {
		return company;
	}

	public WebElement getContacts() {
		return contacts;
	}

	public WebElement getDeals() {
		return deals;
	}

	public WebElement getTasks() {
		return tasks;
	}

	public WebElement getCalendar() {
		return calendar;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public String getActual() {
		return actual.getText();
	}




}
