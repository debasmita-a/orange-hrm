package com.qa.orangehrm.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.constants.FrameworkConstants;
import com.qa.orangehrm.utils.ElementUtil;

public class EmployeeListPage {

	private WebDriver driver;
	private ElementUtil util;
	
	public EmployeeListPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	private By searchBtn = By.id("searchBtn");
	private By searchWith_empName = By.xpath("//input[contains(@id,'empName')]");
	private By searchWith_id = By.id("empsearch_id");
	private By addEmpBtn = By.id("btnAdd");
	private By deleteBtn = By.id("btnDelete");
	private By searchResult = By.xpath("//tr//td//a");
	private By noRecordFound = By.xpath("//tr//td");
	private By id_value = By.xpath("(//tr//td//a)[1]");
	private By firstName_value = By.xpath("(//tr//td//a)[2]");
	private By lastName_value = By.xpath("(//tr//td//a)[3]");
	private By checkBox = By.xpath("//tr/td/input[@type='checkbox']");
	private By deleteOkBtn = By.id("dialogDeleteBtn");
	
	public AddEmployeePage addAnEmployee() {
		util.doClick(addEmpBtn);
		return new AddEmployeePage(driver);
	}
	
	public boolean searchEmployeeWithName(String name){
		util.doActionsSendkeys(searchWith_empName, name);
		util.doActionsClick(searchBtn);
		if(util.doGetText(firstName_value).equalsIgnoreCase(name)) {
			System.out.println(util.doGetText(firstName_value));
			return true;
		}
		return false;
	}
	
	public boolean searchWhenEmployeeNotAvailable(String name) throws InterruptedException {
		util.doActionsSendkeys(searchWith_empName, name);
		Thread.sleep(4000);
		util.doActionsClick(searchBtn);
		if(util.doGetText(noRecordFound).equals(FrameworkConstants.NO_RECORDS_FOUND)) {
			System.out.println("Employee not available in database."+name);
			return true;
		}	
		return false;
	}

	public String searchAnEmployee(String name) {
		if(searchEmployeeWithName(name)) {
			System.out.println("Employee id is :: "+util.doGetText(id_value));
		}
		return util.doGetText(id_value);
	}
	
	public boolean deleteAnEmployee(String name) throws InterruptedException {
		if(searchEmployeeWithName(name)) {
			util.doClick(checkBox);
			util.doClick(deleteBtn);
		    util.doClick(deleteOkBtn);
		    System.out.println("Employee deleted successfully.."+name);
		}
		return searchWhenEmployeeNotAvailable(name);
	}

	public PersonalDetailsPage navigateToPersonalDetailsPage() {
		util.doClick(firstName_value);
		return new PersonalDetailsPage(driver);
	}
	
}
