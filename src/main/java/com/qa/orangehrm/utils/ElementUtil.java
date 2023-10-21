package com.qa.orangehrm.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ElementUtil {

	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> doGetElements(By locator){
		return driver.findElements(locator);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public void doActionsSendkeys(By locator, String keys) {
		Actions action = new Actions(driver);
		getElement(locator).clear();
		action.sendKeys(getElement(locator), keys).build().perform();
	}
	
	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}

	public void doMoveToElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).build().perform();
	}

	public void doAcceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();	
	}
	
	public void doDismissAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();	
	}
	
	public String doGetAlertText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();	
	}
	
	public void moveToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}
	
	public String doGetAttributeValue(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}
	
	public void selectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	//*********************************************************************** wait utils *****************//
	
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public void doMoveToElementWithWait(By locator, int timeout) {
		Actions action = new Actions(driver);
		action.moveToElement(waitForElementPresence(locator,timeout)).build().perform();
	}
	
	public void doActionsClickWithWait(By locator, int timeout) {
		Actions action = new Actions(driver);
		action.click(waitForElementPresence(locator,timeout)).build().perform();
	}
	
	public void doActionsSendKeysWithWait(By locator, String value, int timeout) {
		Actions action = new Actions(driver);
		getElement(locator).clear();
		action.sendKeys(waitForElementPresence(locator,timeout), value).build().perform();
	}
	
	public void doAcceptAlertWithWait(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(timeout));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}
	
	public void doClickWithWait(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(timeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
	}
	//**************************************************************************************************************************//

}
