package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;

public class AddEmployeePageTest extends BaseTest{

	@BeforeClass
	public void addEmployeePageSetup() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		addEmployeePage = dashboardPage.navigateToAddEmployee();
	}
	
	@Test
	public void addEmployeeTest() {
		personalDetailsPage = addEmployeePage.addAnEmployee("Joey", "Tribianni", "0003");
		String actualProfileName = personalDetailsPage.getProfileName();
		Assert.assertEquals(actualProfileName, "Joey Tribianni");
		
	}
}
