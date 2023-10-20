package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.pages.EmployeeListPage;
import com.qa.orangehrm.pages.PersonalDetails;

public class EmployeeListPageTest extends BaseTest{

	@BeforeClass
	public EmployeeListPage setupEmployeeListPage() {
		employeeListPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		return employeeListPage;
	}
	
	@DataProvider
	public Object[][] addEmployeeTestData() {
		return new Object[][] {
			{"Marchese", "Pooby"},
			{"Marchesa", "Cooco"}
		};
	}
	
	@Test(dataProvider="addEmployeeTestData")
	public void addEmployeeTest(String fn, String ln) {
		PersonalDetails personalDetails = new PersonalDetails(fn,ln);
		employeeListPage.addEmployee(personalDetails);
	}
	
	@DataProvider
	public Object[][] searchEmployeeTestData() {
		return new Object[][] {
			{"Marchese"},
			{"Marchesa"}
		};
	}
	@Test(dataProvider="searchEmployeeTestData")
	public void searchEmployeeTest(String name) {
		
	}
	
}
