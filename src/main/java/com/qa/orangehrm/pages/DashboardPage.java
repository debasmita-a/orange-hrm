package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class DashboardPage {

	private WebDriver driver;
	private ElementUtil util;

	private By welcome = By.id("welcome");
	private By pim = By.id("menu_pim_viewPimModule");
	private By addEmployeeOption = By.id("menu_pim_addEmployee");
	private By employeeListOption = By.id("menu_pim_viewEmployeeList");
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public String getWelcomeText() {
		return util.doGetText(welcome);
	}
	
	public AddEmployeePage navigateToAddEmployee() {
		util.doMoveToElement(pim);
		util.doClick(addEmployeeOption);
		return new AddEmployeePage(driver);
	}
	
	public EmployeeListPage navigateToEmployeeList() {
		util.doMoveToElement(pim);
		util.doClick(employeeListOption);
		return new EmployeeListPage(driver);
	}
	
}
