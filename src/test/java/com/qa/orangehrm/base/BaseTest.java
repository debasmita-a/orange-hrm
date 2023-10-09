package com.qa.orangehrm.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.orangehrm.factory.DriverFactory;
import com.qa.orangehrm.pages.AddEmployeePage;
import com.qa.orangehrm.pages.DashboardPage;
import com.qa.orangehrm.pages.EmployeeListPage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.pages.PersonalDetailsPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected DashboardPage dashboardPage;
	protected AddEmployeePage addEmployeePage;
	protected PersonalDetailsPage personalDetailsPage;
	protected EmployeeListPage employeeListPage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}
	
	
	
}
