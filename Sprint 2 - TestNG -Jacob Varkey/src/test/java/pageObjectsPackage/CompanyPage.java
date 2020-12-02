package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyPage {

	WebDriver driver;
	String expectedCompanyName = "First Company";
	String expectedCompanyErrorMessage = "The field Name is required"; 

	@FindBy(xpath="//*[@id='dashboard-toolbar']/div[2]/div/a/button") 
	@CacheLookup
	WebElement newCompanyButton;
	
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[1]/div/div/div/input") 
	@CacheLookup
	WebElement companyName;
	
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[1]/div/label/span") 
	@CacheLookup
	WebElement  actualCompanyErrorMessage;
	
	public CompanyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCompanyName() {
		return companyName;
	}
	
	public WebElement getActualCompanyErrorMessage() {
		return actualCompanyErrorMessage;
	}

	public String getExpectedCompanyErrorMessage() {
		return expectedCompanyErrorMessage;
	}
	
	public String getExpectedCompanyName() {
		return expectedCompanyName;
	}

	public WebElement getNewCompanyButton() {
		return newCompanyButton;
	}

}
