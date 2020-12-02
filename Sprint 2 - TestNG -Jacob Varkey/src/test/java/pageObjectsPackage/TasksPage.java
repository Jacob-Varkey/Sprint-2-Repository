package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TasksPage {
	
	WebDriver driver;
	String expectedTask = "First Task";
	String expectedTaskErrorMessage = "The field Title is required";
	String expectedTaskLengthErrorMessage = "value too long for type character varying(250)";
	
	public TasksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='dashboard-toolbar']/div[2]/div/a/button/i") 
	@CacheLookup
	WebElement newTaskButton;
	
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[1]/div/div/input")
	@CacheLookup
	WebElement taskTitle;
	
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[3]/div[1]/div/div/div/input")
	@CacheLookup
	WebElement dueDate;

	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[5]/div[1]/div/div/div/input")
	@CacheLookup
	WebElement closeDate;
	
	@FindBy(xpath="//*[@id='ui']/div/div[2]/div[2]/div/div[2]/form/div[1]/div[1]/div/label/span")
	@CacheLookup
	WebElement actualTaskErrorMessage;	
	
	@FindBy(xpath="/html/body/div[3]/div/div[2]/div/div/div/div/span/p")
	@CacheLookup
	WebElement actualTaskLengthErrorMessage;
	
	@FindBy(xpath="/html/body/div[3]/div/div[3]/button")
	@CacheLookup
	WebElement okButton;
	
	public String getExpectedTaskLengthErrorMessage() {
		return expectedTaskLengthErrorMessage;
	}

	public WebElement getActualTaskLengthErrorMessage() {
		return actualTaskLengthErrorMessage;
	}

	public String getExpectedTaskErrorMessage() {
		return expectedTaskErrorMessage;
	}

	public WebElement getActualTaskErrorMessage() {
		return actualTaskErrorMessage;
	}

	public WebElement getNewTaskButton() {
		return newTaskButton;
	}

	public WebElement getTaskTitle() {
		return taskTitle;
	}
	
	public WebElement getDueDate() {
		return dueDate;
	}

	public WebElement getCloseDate() {
		return closeDate;
	}

	public String getExpectedTask() {
		return expectedTask;
	}

	public WebElement getOkButton() {
		return okButton;
	}


}
