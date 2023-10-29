package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.constants.FrameworkConstants;
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
	
	public EmployeeListPage doLogin(String userName, String pass){
			util.doSendKeysWithWait(username, userName, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
			util.doSendKeysWithWait(password, pass,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
			util.doClickWithWait(loginBtn, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);		
		return new EmployeeListPage(driver);
	}
}
