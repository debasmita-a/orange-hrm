package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class EmployeeListPage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By pim_menu = By.id("menu_pim_viewPimModule");
	private By employeeListLink = By.linkText("Employee List");
	
	private By addEmpBtn = By.id("btnAdd");
	
	private By fname = By.id("firstName");
	private By lname = By.id("lastName");
	private By saveBtn = By.id("btnSave");
	
	private By profileName = By.xpath("//div[@id='profile-pic']/h1");
			
	public EmployeeListPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public void clickOnEmployeeListLink() {
		util.doMoveToElementWithWait(pim_menu, 5000);
		util.doActionsClickWithWait(employeeListLink, 5000);
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
}
