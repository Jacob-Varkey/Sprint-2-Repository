package testNgExecutionPackage;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import pageObjectsPackage.AccountHomePage;
import pageObjectsPackage.CalendarPage;
import pageObjectsPackage.CompanyPage;
import pageObjectsPackage.ContactsPage;
import pageObjectsPackage.DealsPage;
import pageObjectsPackage.LoginPage;
import pageObjectsPackage.TasksPage;

//Run as Suite in xml for parallel execution
public class ParallelExecutionFreeCRM {
	
	@BeforeClass
	public void propertySetup() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	}
	
	@DataProvider(name="InvalidUser")
	public String[][] negativeLoginData() throws Exception { 
		String array[][] = LoginProvider("negativeDataList.xlsx");
		return array;
	}
	
	
	@DataProvider(name="ValidUser")
	public String[][] positiveLoginData() throws Exception { 
		String array[][] = LoginProvider("positiveDataList.xlsx");
		return array;
	}
	
	//Getting data from excel
	public String[][] LoginProvider(String fileName) throws Exception {
		
		String array[][];
		
		FileInputStream fs = new FileInputStream(fileName);
		
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet ws = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		
		int rowCount = ws.getLastRowNum();
		int colCount = ws.getRow(0).getLastCellNum();
		
		array = new  String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			row = ws.getRow(i);
			for(int j=0;j<colCount;j++) {
				cell = row.getCell(j);
				array[i-1][j] = cell.getStringCellValue();
			}
		}
		
		wb.close();
		
		return array;
		
	}
	
	//Testing Invalid Login Details
	@Test(dataProvider="InvalidUser")
	public void negativeLogin(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui']/div/div/form/div/div[3]/p")));
		Assert.assertEquals(lp.getExpectedErrorMessage(), lp.getActualErrorMessage().getText());
		System.out.println("\nInvalid Login Error Message Verified"); 
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}
	
	//Testing Valid Login Details
	@Test(dataProvider="ValidUser")
	public void positiveLogin(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleContains("Cogmento CRM"));
		Assert.assertEquals(lp.getExpectedTitle(), lp.getActualTitle());
		System.out.println("\nLog In Successful");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}
	
	//Testing to assert error messages in Company creation
	@Test(dataProvider = "ValidUser")
	public void nullCompanyCreation(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getCompany().click();
		Thread.sleep(2000);
		CompanyPage cmp = new CompanyPage(driver);
		cmp.getNewCompanyButton().click();
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(cmp.getExpectedCompanyErrorMessage(), cmp.getActualCompanyErrorMessage().getText());
		System.out.println("\nNull Value Company Error Message Verified");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
		}	
	
	//Testing Creation of new Company
	@Test(dataProvider = "ValidUser")
	public void companyCreation(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getCompany().click();
		Thread.sleep(2000);
		CompanyPage cmp = new CompanyPage(driver);
		cmp.getNewCompanyButton().click();
		Thread.sleep(2000);
		cmp.getCompanyName().sendKeys("First Company");
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(cmp.getExpectedCompanyName(), ap.getActual());
		System.out.println("\nNew Company Created");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}
	
	//Testing to assert error messages in Contact creation
	@Test(dataProvider = "ValidUser")
	public void nullContactCreation(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getContacts().click();
		Thread.sleep(2000);
		ContactsPage cp = new ContactsPage(driver);
		cp.getNewContactButton().click();
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(cp.getExpectedFirstErrorMessage(), cp.getActualFirstErrorMessage().getText());
		Assert.assertEquals(cp.getExpectedLastErrorMessage(), cp.getActualLastErrorMessage().getText());
		System.out.println("\nContact Error Messages Verified");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}	
	
	//Testing creation of new contact
	@Test(dataProvider = "ValidUser")
	public void contactCreation(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getContacts().click();
		Thread.sleep(2000);
		ContactsPage cp = new ContactsPage(driver);
		cp.getNewContactButton().click();
		Thread.sleep(2000);
		cp.getFirstName().sendKeys("Jake");
		cp.getLastName().sendKeys("Castle");
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(cp.getExpectedContact(), ap.getActual());
		System.out.println("\nNew Contact Created");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}
	
	//Testing to assert error messages in Deal creation
	@Test(dataProvider = "ValidUser")
	public void nullDealCreation(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getDeals().click();
		Thread.sleep(2000);
		DealsPage dp = new DealsPage(driver);
		dp.getNewDealButton().click();
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(dp.getExpectedDealErrorMessage(), dp.getActualDealErrorMessage().getText());
		System.out.println("\nNull Value Deal Error Message Verified");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
		}
	
	//Testing Creation of new Deal
	@Test(dataProvider = "ValidUser")
	public void dealCreation(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getDeals().click();
		Thread.sleep(2000);
		DealsPage dp = new DealsPage(driver);
		dp.getNewDealButton().click();
		Thread.sleep(2000);
		dp.getDealTitle().sendKeys("First Deal");
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(dp.getExpectedDeal(), ap.getActual());
		System.out.println("\nNew Deal Created");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}
	
	
	//Testing to assert error messages in task creation
	@Test(dataProvider = "ValidUser")
	public void nullTaskCreation(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getTasks().click();
		Thread.sleep(2000);
		TasksPage tp = new TasksPage(driver);
		tp.getNewTaskButton().click();
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(tp.getExpectedTaskErrorMessage(), tp.getActualTaskErrorMessage().getText());
		tp.getTaskTitle().sendKeys("omkSP0UnsIc3n984BQhzSSTvokKF7nFqvRpUixWr4GcFNt3vCE0PI51MiHutqDrclBXdzXfB1qRFu3PN2ufRAsn6SwjSGkkUBdSgmEwEGURoPdJzEGTupeFnPmrEZMN5dWG712IwwGka153mqTKBqCDvejBhBcdix2o0J3z3TsqHJ5oZTPwzWaoYFAKvet6P0fv0gnxOfwp9ff7SFZMUMpSdkEu2DGl2LqJL7Sh05qPbQnrAytqrCtstu1Y");
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(tp.getExpectedTaskLengthErrorMessage(), tp.getActualTaskLengthErrorMessage().getText());
		tp.getOkButton().click();
		System.out.println("\nTask Error Messages Verified");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}
	
	//Testing creation of new task
	@Test(dataProvider = "ValidUser")
	public void taskCreation(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getTasks().click();
		Thread.sleep(2000);
		TasksPage tp = new TasksPage(driver);
		tp.getNewTaskButton().click();
		Thread.sleep(2000);
		tp.getTaskTitle().sendKeys("First Task");
		tp.getDueDate().sendKeys("04/12/2020 17:30");
		ap.getSaveButton().click();
		Thread.sleep(2000);
		Assert.assertEquals(tp.getExpectedTask(), ap.getActual());
		System.out.println("\nNew Task Created");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}
	
	//Testing to see if task appears on calendar
	@Test(dataProvider = "ValidUser")
	public void calendarTaskView(String email, String pass) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(pass);
		lp.getLogin().click();
		AccountHomePage ap = new AccountHomePage(driver);
		ap.getCalendar().click();
		CalendarPage clp= new CalendarPage(driver);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div/div")));
		Assert.assertEquals(clp.getExpectedCalendarTask(), clp.getActualCalendarTask()); 
		System.out.println("\nTask Viewed on Calendar Successfully");
		System.out.println("Thread ID : "+Thread.currentThread().getId());
		driver.close();
	}

}
