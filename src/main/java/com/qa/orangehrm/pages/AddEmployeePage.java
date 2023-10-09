package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class AddEmployeePage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	private By firstname = By.id("firstName");
	private By lastname = By.id("lastName");
	private By empID = By.id("employeeId");
	private By uploadPhoto = By.id("photofile");
	private By saveBtn = By.id("btnSave");
	
	public AddEmployeePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public PersonalDetailsPage addAnEmployee(String fname, String lanme, String id) {
		util.doSendKeys(firstname, fname);
		util.doSendKeys(lastname, lanme);
		util.doSendKeys(empID, id);
		util.doClick(saveBtn);
		return new PersonalDetailsPage(driver);
	}

}
