package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	String expectedErrorMessage = "Invalid login";
	String expectedTitle = "Cogmento CRM";

	


	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get("https://ui.freecrm.com/");
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//*[@id='ui\']/div/div/form/div/div[1]/div/input")
	@CacheLookup
	WebElement email;
	
	@FindBy(xpath="//*[@id='ui']/div/div/form/div/div[2]/div/input")
	@CacheLookup
	WebElement password;
	
	@FindBy(xpath="//*[@id='ui']/div/div/form/div/div[3]/p")
	@CacheLookup
	WebElement actualErrorMessage;
	
	@FindBy(xpath="//*[@id='ui']/div/div/form/div/div[3]")
	@CacheLookup
	WebElement login;
	
	
	public String getExpectedTitle() {
		return expectedTitle;
	}
	
	public String getActualTitle() {
		return driver.getTitle();
	}

	public WebElement getActualErrorMessage() {
		return actualErrorMessage;
	}


	public WebElement getEmail() {
		return email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLogin() {
		return login;
	}

	public String getExpectedErrorMessage() {
		return expectedErrorMessage;
	}

}
