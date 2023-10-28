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
	public EmployeeListPage setupEmployeeListPage(){
		employeeListPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		return employeeListPage;
	}
	
	@DataProvider
	public Object[][] addEmployeeTestData() {
		return new Object[][] {
			{"Nicaragua", "test"},
			//{"May", "test"}
		};
	}
	
	@Test(dataProvider="addEmployeeTestData")
	public void addEmployeeTest(String fn, String ln){
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
	
	@DataProvider
	public Object[][] updateEmployeeTestData() {
		return new Object[][] {
			{"Joey", "Tribianni","","","","","Italian","Male","Single"},
			{"Rachel", "Green","","","","","Albanian","Female","Married"},
			{"Ross", "Geller","","","","","Danish","Male","Single"},
			{"Phoebe", "Buffay","","","","","Indian","Female","Single"},
			{"Monica", "Geller","","","","","German","Female","Married"},
			{"Chandler", "Bing","","","","","Spanish","Male","Married"},
		};
	}
	@Test(dataProvider="updateEmployeeTestData")
	public void updateEmployeeTest(String fn, String ln, String gender, String maritalStatus, String nationality, String dob, 
			String setNationality, String setGender, String setMaritalStatus){
		PersonalDetails personalDetails = new PersonalDetails(fn,ln,gender,maritalStatus,nationality,dob);
		String emp_id =employeeListPage.addEmployee(personalDetails);
		personalDetails.setGender(setGender);
		personalDetails.setMaritalStatus(setMaritalStatus);
		personalDetails.setNationality(setNationality);
		employeeListPage.updateEmployee(personalDetails, emp_id);	
	}
	
	@Test
	public void deleteEmployeeTest(){
		PersonalDetails personalDetails = new PersonalDetails("test", "test_del","","","","");
		String emp_id =employeeListPage.addEmployee(personalDetails);
		employeeListPage.deleteEmployee(emp_id);
	}
	
}
