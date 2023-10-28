package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By username = By.xpath("//input[@name='username']");
	private By password = By.name("password");
	private By loginBtn = By.xpath("//button[@type='submit']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public EmployeeListPage doLogin(String user_name, String pass){
			util.doSendKeysWithWait(username, user_name, 5000);
			util.doSendKeysWithWait(password, pass,10000);
			util.doClickWithWait(loginBtn, 5000);		
		return new EmployeeListPage(driver);
	}
}
