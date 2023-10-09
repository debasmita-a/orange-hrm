package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.pages.EmployeeListPage;

public class EmployeeListPageTest extends BaseTest {
	
	@BeforeClass
	public EmployeeListPage employeeListPageSetUp() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		employeeListPage = dashboardPage.navigateToEmployeeList();
		return employeeListPage;
	}
	
	@Test
	public void searchEmployeeWithNameTest() {
		Assert.assertEquals(employeeListPage.searchEmployeeWithName("debasmita"), true);
	}
	
	@Test
	public void searchEmployeeWithIdTest() {
		Assert.assertEquals(employeeListPage.searchEmployeeWithId("001"), true);
	}
	
	@Test
	public void searchResultTest() {
		String emp_last_name = employeeListPage.searchResult("debasmita", " ").get("Last name");
		Assert.assertEquals(emp_last_name, "Adhikari");
	}
	
	@Test
	public void deleteEmployeeTest() {
		employeeListPage.deleteEmployee("debasmita");
		Assert.assertEquals(employeeListPage.searchEmployeeWithId("debasmita"), false);
	}
}
