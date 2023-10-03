package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class DashboardPage {

	private WebDriver driver;
	private ElementUtil util;

	private By welcome = By.id("welcome");
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public String getWelcomeText() {
		return util.doGetText(welcome);
	}
	
}
