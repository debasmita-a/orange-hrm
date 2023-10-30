package com.qa.orangehrm.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.pages.EmployeeListPage;
import com.qa.orangehrm.pages.PersonalDetails;

public class EmployeeListPageTest extends BaseTest{

	@BeforeClass
	public EmployeeListPage setupEmployeeListPage(){
		employeeListPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		return employeeListPage;
	}
	
	@DataProvider
	public Object[][] addEmployeeTestData() {
		return new Object[][] {
			{"August", "Rush"},
			{"Daisy", "Jones"},
			{"Peter", "Parker"}
		};
	}
	
	@Test(dataProvider="addEmployeeTestData")
	public void addEmployeeTest(String fn, String ln){
		PersonalDetails personalDetails = new PersonalDetails(fn,ln);
		List<String> actualResult = employeeListPage.addEmployee(personalDetails);
		String successMsg = actualResult.get(1);
		Assert.assertTrue(successMsg.equals("Successfully Saved"));
	}
	
	@DataProvider
	public Object[][] searchEmployeeTestData() {
		return new Object[][] {
			{"Juniper","Thorne"},
			{"Nina", "Terrence"},
			{"Kaz", "Nolan"}
		};
	}
	
	@Test(dataProvider="searchEmployeeTestData")
	public void searchEmployeeTest(String fn, String ln){
		PersonalDetails personalDetails = new PersonalDetails(fn,ln);
		List<String> actualResult = employeeListPage.addEmployee(personalDetails);
		boolean flag = employeeListPage.searchEmployee(actualResult.get(0));
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] updateEmployeeTestData() {
		return new Object[][] {
			{"Joey", "Tribianni","","","","","Italian","Male","Single"},
			{"Rachel", "Green","","","","","Albanian","Female","Married"},
		    {"Ross", "Geller","","","","","Danish","Male","Single"},
			{"Phoebe", "Buffay","","","","","Indian","Female","Single"},
			{"Monica", "Geller","","","","","German","Female","Married"},
			{"Chandler", "Bing","","","","","Spanish","Male","Married"}
		};
	}
	@Test(dataProvider="updateEmployeeTestData")
	public void updateEmployeeTest(String fn, String ln, String gender, String maritalStatus, String nationality, String dob, 
			String setNationality, String setGender, String setMaritalStatus){
		PersonalDetails personalDetails = new PersonalDetails(fn,ln,gender,maritalStatus,nationality,dob);
		employeeListPage.addEmployee(personalDetails);
		personalDetails.setGender(setGender);
		personalDetails.setMaritalStatus(setMaritalStatus);
		personalDetails.setNationality(setNationality);
		String successMsg = employeeListPage.updateEmployee(personalDetails);	
		Assert.assertTrue(successMsg.equals("Successfully Updated"));
	}
	
	@DataProvider
	public Object[][] deleteEmployeeTestData() {
		return new Object[][] {
			{"Jake","Peralta"},
			{"Amy", "Adams"},
			{"Rosa", "Diaz"}
		};
	}
	
	@Test(dataProvider="deleteEmployeeTestData")
	public void deleteEmployeeTest(String fn, String ln){
		PersonalDetails personalDetails = new PersonalDetails(fn, ln);
		List<String> actualResult = employeeListPage.addEmployee(personalDetails);
		String successMsg = employeeListPage.deleteEmployee(actualResult.get(0));	
		Assert.assertTrue(successMsg.equals("Successfully Deleted"));
	}
	
}
