package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class EmployeeListPage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By pim_menu = By.id("menu_pim_viewPimModule");
	private By employeeListLink = By.linkText("Employee List");
	
	private By addEmpBtn = By.id("btnAdd");
	private By searchBtn = By.id("searchBtn");
	private By editBtn = By.id("btnSave");
	private By saveBtn = By.id("btnSave");
	
	private By fname = By.id("firstName");
	private By lname = By.id("lastName");
	private By id = By.id("personal_txtEmployeeId");
	private By license_exp_date = By.id("personal_txtLicExpDate");
	private By gender = By.id("personal_optGender_1");
	private By marital_status = By.id("personal_cmbMarital");
	private By nationality = By.id("personal_cmbNation");
	private By dob = By.id("personal_DOB");
	private By successMsg = By.xpath("//div[@class='inner' ]/script[@type='text/javascript']");
	
	private By empName_search = By.id("empsearch_employee_name_empName");
	private By emp_table_links = By.xpath("//td/a");
	private By emp_table_fname = By.xpath("(//td/a)[2]");
	
	private By profileName = By.xpath("//div[@id='profile-pic']/h1");
			
	public EmployeeListPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public void clickOnEmployeeListLink() {
		util.doMoveToElementWithWait(pim_menu, 5000);
		util.doActionsClickWithWait(employeeListLink, 5000);
	}
	
	public void fillPersonalDetails(PersonalDetails personalDetails) {
		util.getElement(fname).sendKeys(personalDetails.getFirstName());
		util.getElement(lname).sendKeys(personalDetails.getLastName());
		util.getElement(id).sendKeys(personalDetails.getEmpId());
		util.getElement(gender).sendKeys(personalDetails.getGender());
		util.getElement(marital_status).sendKeys(personalDetails.getMaritalStatus());
		util.getElement(nationality).sendKeys(personalDetails.getNationality());
		util.getElement(dob).sendKeys(personalDetails.getDob());
	}
	
	public String addEmployee(PersonalDetails personalDetails) {
		clickOnEmployeeListLink();
		util.doClick(addEmpBtn);
		util.doActionsSendkeys(fname, personalDetails.getFirstName());
		util.doActionsSendkeys(lname, personalDetails.getLastName());
		util.doClick(saveBtn);
		String empName = util.doGetText(profileName);
		System.out.println("Employee added :: "+empName);
		clickOnEmployeeListLink();
		return empName;
	}
	
	public void searchEmployee(String fName) {
		clickOnEmployeeListLink();
		
		
	}
	
	public void updateEmployee(PersonalDetails personalDetails, String fName) {
		
	}

}
