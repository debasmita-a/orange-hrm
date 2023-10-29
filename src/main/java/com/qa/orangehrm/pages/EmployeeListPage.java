package com.qa.orangehrm.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.orangehrm.constants.FrameworkConstants;
import com.qa.orangehrm.utils.ElementUtil;

public class EmployeeListPage {

	private WebDriver driver;
	private ElementUtil util;
	
    //main menu link elements::
	private By pim_menu = By.xpath("//a[contains(@href,'/pim/viewPimModule')]");
	private By employeeListLink = By.linkText("Employee List");
	//form button elements::
	private By addEmpBtn = By.xpath("//button[text()=' Add ']");
	private By searchBtn = By.xpath("//button[text()=' Search ']");
	private By saveBtn = By.xpath("//button[text()=' Save ']");
	//private By editBtn = By.xpath("//i[contains(@class,'bi-pencil-fill')]");
	//private By deleteBtn1 = By.xpath("//i[contains(@class,'bi-trash')]");
	private By deleteBtn2 = By.xpath("//button[text()=' Delete Selected ']");
	private By deleteDialog_Ok_Btn = By.xpath("//button[text()=' Yes, Delete ']");
	
	private By toasterMsg_onAdd = By.xpath("(//div[@id='oxd-toaster_1']//p)[2][text()='Successfully Saved']");
	private By toasterMsg_onUpdate = By.xpath("(//div[@id='oxd-toaster_1']//p)[2][text()='Successfully Updated']");
	private By toasterMsg_onDelete = By.xpath("(//div[@id='oxd-toaster_1']//p)[2][text()='Successfully Deleted']");
	
	//employee edit form elements::
	private By fname = By.xpath("//input[contains(@class,'orangehrm-firstname')]");
	private By lname = By.xpath("//input[contains(@class,'orangehrm-lastname')]");
	private By employeeId = By.xpath("//label[@class='oxd-label']/parent::div/following-sibling::div/input");
	private By gender_male = By.xpath("//input[@value='1']");
	private By gender_female = By.xpath("//input[@value='2']");
	private By maritalStatus = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]");
	private By nationality = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]");
	private By dropdownValues = By.xpath("(//div[@role='listbox'])//div[@role='option']/span");
	private By dob = By.xpath("//label[text()='Date of Birth']/parent::div/following-sibling::div//input[@placeholder='yyyy-mm-dd']");
	//employee table cell values::
	private By checkbox = By.xpath("(//div[@role='cell'])[1]//input[@type='checkbox']");
	private By emp_table_links = By.xpath("//div[@role='cell']");
	
	//private By profileName = By.xpath("//div[@class='orangehrm-edit-employee-name']//h6");
		
	//page actions::
	
	public EmployeeListPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public void clickOnEmployeeListLink() {
		util.doClickWithWait(pim_menu, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doClickWithWait(employeeListLink,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
	}
	
	public void selectNationalityOption(String nationalityVal) {
		util.doClickWithWait(nationality, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		List<WebElement> nationalityValues = util.waitForAllElementsPresence(dropdownValues, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		for(WebElement e : nationalityValues) {
			if(e.getText().contains(nationalityVal)) {
				e.click();
				break;
			}
		}
	}
	
	public void selectMaritalStatus(String maritalVal) {
		util.doClickWithWait(maritalStatus, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		List<WebElement> maritalStatusValues = util.waitForAllElementsPresence(dropdownValues, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		for(WebElement e : maritalStatusValues) {
			if(e.getText().contains(maritalVal)) {
				e.click();
				break;
			}
		}
	}
	
	public void fillPersonalDetails(PersonalDetails personalDetails) {
		util.doSendKeysWithWait(fname, personalDetails.getFirstName(), FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doSendKeysWithWait(lname, personalDetails.getLastName(), FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);	
		if(personalDetails.getGender().equals("Male")) {
			util.doActionsClickWithWait(gender_male, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		}else {
			util.doActionsClickWithWait(gender_female, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		}		
		selectNationalityOption(personalDetails.getNationality());
		selectMaritalStatus(personalDetails.getMaritalStatus());
		util.doSendKeysWithWait(dob, personalDetails.getDob(), FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
	}
	
	public List<String> addEmployee(PersonalDetails personalDetails){
		clickOnEmployeeListLink();
		util.doClickWithWait(addEmpBtn,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doSendKeysWithWait(fname, personalDetails.getFirstName(),FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doSendKeysWithWait(lname, personalDetails.getLastName(),FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		String id = util.doGetAttributeValueWithWait(employeeId,"value",FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doClickWithWait(saveBtn,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		String successMsg = util.doGetTextWithWait(toasterMsg_onAdd,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		System.out.println("Success msg.."+successMsg);
		//Thread.sleep(5000);
		//String name = util.doGetAttributeValueWithWait(profileName,"textContent",FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		//System.out.println("Employee added.."+name);
		System.out.println("Employee added.."+id);
		
		List<String> returnValues = new ArrayList<String>();
		returnValues.add(id);
		//returnValues.add(name);
		returnValues.add(successMsg);
		
		return returnValues;
	}
	
	public boolean searchEmployee(String emp_id) {
		clickOnEmployeeListLink();
		util.doSendKeysWithWait(employeeId, emp_id, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doClickWithWait(searchBtn,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
	    if(util.waitForAllElementsPresence(emp_table_links, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT).size()>0) {
	    	System.out.println("Employee available..");
	    	return true;
	    }
	    System.out.println("Employee not available..");
	    return false;
	}
	
	public String updateEmployee(PersonalDetails personalDetails) {
		//searchEmployee(emp_id);
		//util.doClickWithWait(editBtn,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		fillPersonalDetails(personalDetails);
		util.doClickWithWait(saveBtn,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		String successMsg = util.doGetTextWithWait(toasterMsg_onUpdate,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		System.out.println(successMsg);
		return successMsg;
	}
	
	public String deleteEmployee(String emp_id) {
		clickOnEmployeeListLink();
		util.doSendKeysWithWait(employeeId, emp_id, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doClickWithWait(searchBtn,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doActionsClickWithWait(checkbox, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doActionsClickWithWait(deleteBtn2, FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		util.doClickWithWait(deleteDialog_Ok_Btn,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);		
		String successMsg = util.doGetTextWithWait(toasterMsg_onDelete,FrameworkConstants.DEFAULT_MEDIUM_TIMEOUT);
		System.out.println(successMsg);
		return successMsg;
	}

}
