package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	WebDriver driver;
	String expectedContact = "Jake Castle";
	String expectedFirstErrorMessage = "The field First Name is required";
	String expectedLastErrorMessage = "The field Last Name is required";
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Contacts
	@FindBy(xpath="//*[@id='dashboard-toolbar']/div[2]/div/a/button/i") 
	@CacheLookup
	WebElement newContactButton; 
		
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[1]/div/div/input")
	@CacheLookup
	WebElement firstName;
		
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[2]/div/div/input")
	@CacheLookup
	WebElement lastName; 
		
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[1]/div/label/span")
	@CacheLookup
	WebElement actualFirstErrorMessage;
	
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[2]/div/label/span")
	@CacheLookup
	WebElement actualLastErrorMessage;
	
	public String getExpectedFirstErrorMessage() {
		return expectedFirstErrorMessage;
	}

	public WebElement getActualFirstErrorMessage() {
		return actualFirstErrorMessage;
	}
	
	public String getExpectedLastErrorMessage() {
		return expectedLastErrorMessage;
	}

	public WebElement getActualLastErrorMessage() {
		return actualLastErrorMessage;
	}

	public WebElement getNewContactButton() {
		return newContactButton;
	}
	
	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public String getExpectedContact() {
		return expectedContact;
	}	
	
		
}
