package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class PersonalDetailsPage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By profileName = By.xpath("//div[@id='profile-pic']/h1");
	private By editProfileBtn = By.xpath("//input[@id='btnSave' and @value='Edit']");
	private By saveProfileBtn = By.xpath("//input[@id='btnSave' and @value='Save']");
	
	public PersonalDetailsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public String getProfileName() {
		return util.doGetText(profileName);
	}
	
	public void editEmployeeProfile() {
		util.doClick(editProfileBtn);
		//edit details
		util.doClick(saveProfileBtn);
	}
}
