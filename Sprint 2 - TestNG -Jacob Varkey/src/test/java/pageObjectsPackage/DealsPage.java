package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DealsPage {

	WebDriver driver;
	String expectedDeal = "First Deal";
	String expectedDealErrorMessage = "The field Title is required";
	
	public DealsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='dashboard-toolbar']/div[2]/div/a[3]/button/i") 
	@CacheLookup
	WebElement newDealButton; 
	
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[1]/div/div/input") 
	@CacheLookup
	WebElement dealTitle;
	
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[1]/div/label/span")
	@CacheLookup
	WebElement actualDealErrorMessage; 
	
	
	public String getExpectedDealErrorMessage() {
		return expectedDealErrorMessage;
	}

	public WebElement getActualDealErrorMessage() {
		return actualDealErrorMessage;
	}

	public WebElement getNewDealButton() {
		return newDealButton;
	}
	
	public WebElement getDealTitle() {
		return dealTitle;
	}
	
	public String getExpectedDeal() {
		return expectedDeal;
	}
	
	
	
}
