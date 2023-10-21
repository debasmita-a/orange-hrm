package com.qa.orangehrm.rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://debasmitaa-osondemand.orangehrm.com/symfony/web/index.php/auth/login");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("E@@G53@mdnPK");
		driver.findElement(By.id("btnLogin")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		Actions action = new Actions(driver);
		action.moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewPimModule")))).build().perform();
		action.click(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewEmployeeList")))).build().perform();
		
		//add employees...

		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("Jan");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Jan");
		String empId = driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
		
		
		action.moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewPimModule")))).build().perform();
		action.click(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewEmployeeList")))).build().perform();
		
		//search employee..
		
		driver.findElement(By.id("empsearch_id")).sendKeys(empId);
		driver.findElement(By.id("searchBtn")).click();
		
		String empTableSearchResult = "//td/a[text()='"+empId+"']";
		
		if(driver.findElements(By.xpath(empTableSearchResult)).size()>0) {
			System.out.println("Employee Added to table.."+empId);
		}

		//update employee data..
		
		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("Feb");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Feb");
		//String empId = driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
		
		driver.findElement(By.id("btnSave")).click();
		driver.findElement(By.id("personal_optGender_2")).click();
		Select select;
		select = new Select(driver.findElement(By.id("personal_cmbNation")));
		select.selectByVisibleText("Armenian");
		select = new Select(driver.findElement(By.id("personal_cmbMarital")));
		select.selectByValue("Other");
		driver.findElement(By.id("btnSave")).click();
		
		if(driver.findElements(By.xpath("//input[@disabled='disabled']")).size()>0) {
			System.out.println("Employee edited successfully..");
		}else {
			System.out.println("Not edited..");
		}
		
		//delete employee..
		
		action.moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewPimModule")))).build().perform();
		action.click(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewEmployeeList")))).build().perform();
		
		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("March");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("March");
		String empId_del = driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
				
		action.moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewPimModule")))).build().perform();
		action.click(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewEmployeeList")))).build().perform();
		
		driver.findElement(By.id("empsearch_id")).sendKeys(empId_del);
		driver.findElement(By.id("searchBtn")).click();
		
		String empTableSearchResult_del = "//td/a[text()='"+empId_del+"']";
		
		if(driver.findElements(By.xpath(empTableSearchResult_del)).size()>0) {
			System.out.println("Employee Added to table.."+empId_del);
		}
		
		driver.findElement(By.xpath("//td/input[@type='checkbox']")).click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String no_rec_found_msg = driver.findElement(By.xpath("//table//td")).getText();
		System.out.println("Employee deleted.."+empId_del+"  "+no_rec_found_msg);	

}
	
}
