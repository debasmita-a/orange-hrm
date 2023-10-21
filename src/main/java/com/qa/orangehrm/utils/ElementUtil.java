package com.qa.orangehrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementUtil {

	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public void doMoveToElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).build().perform();
	}
}
