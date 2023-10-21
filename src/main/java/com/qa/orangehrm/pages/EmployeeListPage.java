package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.qa.orangehrm.utils.ElementUtil;

public class EmployeeListPage {

	private WebDriver driver;
	private ElementUtil util;

	private By pim_menu = By.id("menu_pim_viewPimModule");
	private By employeeListLink = By.linkText("Employee List");
	
	private By addEmpBtn = By.id("btnAdd");
	private By searchBtn = By.id("searchBtn");
	private By editBtn = By.id("btnSave");
	private By saveBtn = By.id("btnSave");
	private By deleteBtn = By.id("btnDelete");
	private By fname_search_checkbox = By.xpath("//td/input[@type='checkbox']");
	private By deleteDialog_Ok_Btn = By.id("dialogDeleteBtn");
	private By noRecodFound_msg = By.xpath("//table//td");
	
	private By fname = By.id("firstName");
	private By lname = By.id("lastName");
	private By fname_edit = By.id("personal_txtEmpFirstName");
	private By lname_edit = By.id("personal_txtEmpLastName");
	private By id = By.id("personal_txtEmployeeId");
	private By license_exp_date = By.id("personal_txtLicExpDate");
	private By gender_male = By.id("personal_optGender_1");
	private By gender_female = By.id("personal_optGender_2");
	private By marital_status = By.id("personal_cmbMarital");
	private By nationality = By.id("personal_cmbNation");
	private By dob = By.id("personal_DOB");
	private By empId = By.id("employeeId");
	
	private By disabled_elements_edit = By.xpath("//input[@disabled='disabled']");
	private By empId_search = By.id("empsearch_id");
	private By empName_search = By.id("empsearch_employee_name_empName");
	private By emp_table_links = By.xpath("//td/a");
	private By emp_table_fname = By.xpath("(//td/a)[2]");
	
	private By profileName = By.xpath("//div[@id='profile-pic']/h1");
			
	public EmployeeListPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public void clickOnEmployeeListLink() {
		util.doMoveToElementWithWait(pim_menu, 5000);
		util.doActionsClickWithWait(employeeListLink, 5000);
	}
	
	public void fillPersonalDetails(PersonalDetails personalDetails) {
		util.doSendKeys(fname_edit,personalDetails.getFirstName());
		util.doSendKeys(lname_edit,personalDetails.getLastName());
		
		if(personalDetails.getGender().equals("Male")) {
			util.doClick(gender_male);
		}else {
			util.doClick(gender_female);
		}
		
		util.selectByVisibleText(marital_status,personalDetails.getMaritalStatus());
		util.selectByVisibleText(nationality,personalDetails.getNationality());		
		util.getElement(dob).sendKeys(personalDetails.getDob());
	}
	
	public String addEmployee(PersonalDetails personalDetails) {
		clickOnEmployeeListLink();
		util.doClick(addEmpBtn);
		util.doActionsSendkeys(fname, personalDetails.getFirstName());
		util.doActionsSendkeys(lname, personalDetails.getLastName());
		String employeeId = util.doGetAttributeValue(empId, "value");
		util.doClick(saveBtn);
		return searchEmployee(employeeId);
		/*
		 * clickOnEmployeeListLink(); util.doActionsSendKeysWithWait(empId_search,
		 * employeeId, 5000); util.doClick(searchBtn); String empTableSearchResult =
		 * "//td/a[text()='"+employeeId+"']";
		 * if(util.doGetElements(By.xpath(empTableSearchResult)).size()>0) {
		 * System.out.println("Employee added.."+employeeId); return true; } return
		 * false;
		 */
	}
	
	public String searchEmployee(String emp_id) {
		clickOnEmployeeListLink();
		util.doActionsSendKeysWithWait(empId_search, emp_id, 5000);
		util.doClick(searchBtn);
		String empTableSearchResult = "//td/a[text()='"+emp_id+"']";
		if(util.doGetElements(By.xpath(empTableSearchResult)).size()>0) {
			System.out.println("Employee available.."+emp_id);
			return emp_id;
		}
		return null;	
	}
	
	public boolean updateEmployee(PersonalDetails personalDetails, String emp_id) {
		/*
		 * clickOnEmployeeListLink(); util.doActionsSendKeysWithWait(empId_search,
		 * emp_id, 5000); String empTableSearchResult = "//td/a[text()='"+emp_id+"']";
		 * util.doClickWithWait(By.xpath(empTableSearchResult),5000);
		 */
		searchEmployee(emp_id);
		String empTableSearchResult = "//td/a[text()='"+emp_id+"']";
		util.doClickWithWait(By.xpath(empTableSearchResult),5000);
		util.doClickWithWait(editBtn,5000);
		fillPersonalDetails(personalDetails);
		util.doClickWithWait(saveBtn,5000);
		if(util.doGetElements(disabled_elements_edit).size()>0) {
			System.out.println("Employee edited successfully..no.disabled ele.."+util.doGetElements(disabled_elements_edit).size());
			return true;
		}else {
			System.out.println("Not edited successfully..");
			return false;
		}
	}
	
	public String deleteEmployee(String emp_id) {
		clickOnEmployeeListLink();
		searchEmployee(emp_id);
		util.doClickWithWait(fname_search_checkbox, 5000);
		util.doClickWithWait(deleteBtn,5000);
		util.doClickWithWait(deleteDialog_Ok_Btn,5000);		
		String no_rec_found_msg = util.doGetText(noRecodFound_msg);
		System.out.println("Deleted employee.."+emp_id+"  "+no_rec_found_msg);
		return no_rec_found_msg;
	}

}
