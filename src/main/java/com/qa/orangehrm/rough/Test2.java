package com.qa.orangehrm.rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test2 {

	static WebDriver driver;
	private static By username = By.xpath("//input[@name='username']");
	private static By password = By.name("password");
	private static By loginBtn = By.xpath("//button[@type='submit']");
	private static By pim_menu = By.xpath("//a[contains(@href,'/pim/viewPimModule')]");
	private static By employeeListLink = By.linkText("Employee List");
	//form button elements::
	private static By addEmpBtn = By.xpath("//button[text()=' Add ']");
	private static By saveBtn = By.xpath("//button[text()=' Save ']");
	
	private static By fname = By.xpath("//input[contains(@class,'orangehrm-firstname')]");
	private static By lname = By.xpath("//input[contains(@class,'orangehrm-lastname')]");
	private static By employeeId = By.xpath("//label[@class='oxd-label']/parent::div/following-sibling::div/input");
	private static By gender_male = By.xpath("//input[@value='1']");
	private static By gender_female = By.xpath("//input[@value='2']");
	private static By maritalStatus = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]");
	private static By nationality = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]");
	private static By dob = By.xpath("//label[text()='Date of Birth']/parent::div/following-sibling::div//input[@placeholder='yyyy-mm-dd']");
	private static By profileName = By.xpath("//div[@class='orangehrm-edit-employee-name']/h6");
	private static By toasterMsg = By.xpath("(//div[@id='oxd-toaster_1']//p)[2][text()='Successfully Saved']");
	
	public static void main(String[] args) throws InterruptedException {
		driver = new EdgeDriver();
		driver.get("https://debasmitaa-osondemand.orangehrm.com/auth/login");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(username)).sendKeys("Admin");
		//driver.findElement(username).sendKeys("Admin");
		wait.until(ExpectedConditions.presenceOfElementLocated(password)).sendKeys("E@@G53@mdnPK");
		wait.until(ExpectedConditions.presenceOfElementLocated(loginBtn)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(pim_menu)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(employeeListLink)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(addEmpBtn)).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='orangehrm-background-container']")));
		wait.until(ExpectedConditions.presenceOfElementLocated(fname)).sendKeys("Jim");
		wait.until(ExpectedConditions.presenceOfElementLocated(lname)).sendKeys("Peterson");		
		
		String id = wait.until(ExpectedConditions.presenceOfElementLocated(employeeId)).getAttribute("value");
		System.out.println("id.."+id);
	
		wait.until(ExpectedConditions.presenceOfElementLocated(saveBtn)).click();
		
		//boolean msgFlag = wait.until(ExpectedConditions.textToBePresentInElementLocated(toasterMsg, "Successfully Saved"));
		//System.out.println("msg.."+msgFlag);
		String msg = wait.until(ExpectedConditions.presenceOfElementLocated(toasterMsg)).getText();
		System.out.println("msg.."+msg);
		Thread.sleep(5000);
		String name = wait.until(ExpectedConditions.presenceOfElementLocated(profileName)).getAttribute("textContent");
		System.out.println("name.."+name);	
		
		if(name==null) {
			System.out.println("Name not available.");
		}else {
			System.out.println("Name available.");
		}
		
		

	}

}
