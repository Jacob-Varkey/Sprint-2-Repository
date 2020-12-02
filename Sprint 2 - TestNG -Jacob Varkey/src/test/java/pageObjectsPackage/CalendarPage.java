package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalendarPage {

	WebDriver driver;
	String expectedCalendarTask = "First Task";
	
	public CalendarPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getExpectedCalendarTask() {
		return expectedCalendarTask;
	}
	
	public String getActualCalendarTask() {
		return driver.findElement(By.xpath("//*[@id='ui']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div/div")).getText();
	}

}
