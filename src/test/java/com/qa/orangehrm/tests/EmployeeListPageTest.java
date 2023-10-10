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
	public void searchEmployeeWithNameTest() throws InterruptedException {
		Assert.assertTrue(employeeListPage.searchEmployeeWithName("joey"));
	}
	
	@Test
	public void searchWhenEmployeeNotAvailableTest() throws InterruptedException {
		Assert.assertTrue(employeeListPage.searchWhenEmployeeNotAvailable("test01"));
	}
	
	@Test
	public void searchResultTest(){
		Assert.assertEquals(employeeListPage.searchAnEmployee("debasmita"),"0001");
	}
	
	@Test
	public void deleteEmployeeTest() throws InterruptedException{
		Assert.assertTrue(employeeListPage.deleteAnEmployee("test01"));
	}
}
