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
	private By gender_male_radioBtn = By.xpath("//input[@value='1']");
	private By gender_female_radioBtn = By.xpath("//input[@value='2']");
	private By select_nationality = By.id("personal_cmbNation");
	private By disabledFields = By.xpath("//input[@disabled='disabled']");
	
	public PersonalDetailsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public String getProfileName() {
		return util.doGetText(profileName);
	}
	
	public void editEmployeeProfile() {
		util.doClick(editProfileBtn);
		util.doActionsClick(gender_female_radioBtn);
		util.selectByVisibleText(select_nationality, "Algerian");
		util.doClick(saveProfileBtn);
		if(util.doGetElements(disabledFields).size()>0) {
			System.out.println("Updated employee data..");
		}
	}
}
