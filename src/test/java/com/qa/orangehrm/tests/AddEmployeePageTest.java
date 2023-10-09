package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.pages.AddEmployeePage;
import com.qa.orangehrm.pages.DashboardPage;

public class AddEmployeePageTest extends BaseTest{

	@BeforeClass
	public AddEmployeePage addEmployeePageSetup() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		addEmployeePage = dashboardPage.navigateToAddEmployee();
		return addEmployeePage;
	}
	@Test
	public void addEmployeeTest() {
		personalDetailsPage = addEmployeePage.addAnEmployee("Joey", "Tribianni", "0003");
		String actualProfileName = personalDetailsPage.getProfileName();
		Assert.assertEquals(actualProfileName, "Joey Tribianni");
		
	}
}
