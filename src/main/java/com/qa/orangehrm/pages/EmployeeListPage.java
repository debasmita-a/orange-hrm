package com.qa.orangehrm.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
	
	public boolean searchEmployeeWithName(String name) {
		return false;

	}
	
	public boolean searchEmployeeWithId(String id) {
		util.doSendKeys(searchWith_id, id);
		util.doClick(searchBtn);
		if(util.doGetElements(searchResult).size()>0) {
			return true;
		}	
		return false;
	}
	
	public Map<String, String> searchResult(String name, String id) {
		Map<String, String> searchResult = new HashMap<String, String>();
		if(searchEmployeeWithName(name) || searchEmployeeWithId(id)){
			searchResult.put("ID", util.doGetText(id_value));
			searchResult.put("First name", util.doGetText(firstName_value));
			searchResult.put("Last name", util.doGetText(lastName_value));
		}	
		System.out.println(searchResult);
		return searchResult;
	}
	
	public void deleteEmployee(String name) {
		if(searchEmployeeWithName(name)) {
			util.doClick(checkBox);
			util.doClick(deleteBtn);
			util.doClick(deleteOkBtn);
		}
	}
	
}
