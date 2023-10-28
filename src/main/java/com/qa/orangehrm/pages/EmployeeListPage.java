package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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
	private By editBtn = By.xpath("//i[contains(@class,'bi-pencil-fill')]");
	private By deleteBtn1 = By.xpath("//i[contains(@class,'bi-trash')]");
	private By deleteBtn2 = By.xpath("//button[text()=' Delete Selected ']");
	private By deleteDialog_Ok_Btn = By.xpath("//button[text()=' Yes, Delete ']");
	private By toasterMsg = By.id("oxd-toaster_1");
	//employee edit form elements::
	private By fname = By.xpath("//input[contains(@class,'orangehrm-firstname')]");
	private By lname = By.xpath("//input[contains(@class,'orangehrm-lastname')]");
	private By employeeId = By.xpath("//label[@class='oxd-label']/parent::div/following-sibling::div/input");
	private By gender_male = By.xpath("//input[@value='1']");
	private By gender_female = By.xpath("//input[@value='2']");
	private By maritalStatus = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]");
	private By nationality = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]");
	private By dob = By.xpath("//label[text()='Date of Birth']/parent::div/following-sibling::div//input[@placeholder='yyyy-mm-dd']");
	//employee table cell values::
	private By checkbox = By.xpath("(//div[@role='cell'])[1]//input[@type='checkbox']");
	private By id = By.xpath("(//div[@role='cell'])[2]");
	private By firstName = By.xpath("(//div[@role='cell'])[3]");
	private By lastName = By.xpath("(//div[@role='cell'])[4]");
	private By emp_table_links = By.xpath("//div[@role='cell']");
	
	private By profileName = By.xpath("//div[@class='orangehrm-edit-employee-name']//h6");
		
	//page actions::
	
	public EmployeeListPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public void clickOnEmployeeListLink() {
		util.doClickWithWait(pim_menu, 5000);
		util.doClickWithWait(employeeListLink,5000);
	}
	
	public void fillPersonalDetails(PersonalDetails personalDetails) {
		util.doSendKeysWithWait(fname, personalDetails.getFirstName(), 5000);
		util.doSendKeysWithWait(lname, personalDetails.getLastName(), 5000);	
		if(personalDetails.getGender().equals("Male")) {
			util.doClickWithWait(gender_male, 5000);
		}else {
			util.doClickWithWait(gender_female, 5000);
		}		
		util.selectByVisibleTextWithWait(maritalStatus,personalDetails.getMaritalStatus(),5000);
		util.selectByVisibleTextWithWait(nationality,personalDetails.getNationality(),5000);		
		util.doSendKeysWithWait(dob, personalDetails.getDob(), 5000);
	}
	
	public String addEmployee(PersonalDetails personalDetails){
		clickOnEmployeeListLink();
		util.doClickWithWait(addEmpBtn,5000);
		util.doSendKeysWithWait(fname, personalDetails.getFirstName(),5000);
		util.doSendKeysWithWait(lname, personalDetails.getLastName(),5000);
		String id = util.doGetAttributeValueWithWait(employeeId,"value",5000);
		util.doClickWithWait(saveBtn,5000);
		String successMsg = util.doGetAttributeValueWithWait(toasterMsg,"value",5000);
		System.out.println("Success msg.."+successMsg);
		String name = util.doGetAttributeValueWithWait(profileName,"value",5000);
		System.out.println("Employee added.."+name);
		System.out.println("Employee added.."+id);
		return id;
	}
	
	public boolean searchEmployee(String emp_id) {
		clickOnEmployeeListLink();
		util.doActionsSendKeysWithWait(employeeId, emp_id, 5000);
		util.doClick(searchBtn);
	    if(util.doGetText(id).equals(emp_id)) {
	    	return true;
	    }
	    return false;
	}
	
	public void updateEmployee(PersonalDetails personalDetails, String emp_id) {
		searchEmployee(emp_id);
		util.doClickWithWait(editBtn,5000);
		fillPersonalDetails(personalDetails);
		util.doClickWithWait(saveBtn,5000);
		String successMsg = util.doGetText(toasterMsg);
		System.out.println(successMsg);
	}
	
	public void deleteEmployee(String emp_id) {
		clickOnEmployeeListLink();
		searchEmployee(emp_id);
		util.doClickWithWait(deleteBtn1, 5000);
		util.doClickWithWait(deleteDialog_Ok_Btn,5000);		
		String successMsg = util.doGetText(toasterMsg);
		System.out.println(successMsg);
	}

}
