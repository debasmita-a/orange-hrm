package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By username = By.id("txtUsername");
	private By password = By.id("txtPassword");
	private By loginBtn = By.id("btnLogin");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public EmployeeListPage doLogin(String user_name, String pass) {
		util.doSendKeys(username, user_name);
		util.doSendKeys(password, pass);
		util.doActionsClick(loginBtn);
		return new EmployeeListPage(driver);
	}
}
