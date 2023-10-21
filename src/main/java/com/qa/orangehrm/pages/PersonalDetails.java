package com.qa.orangehrm.pages;

public class PersonalDetails {

	private String firstName;
	private String lastName;
	private String empId;
	private String gender;
	private String maritalStatus;
	private String nationality;
	private String dob;
	
	
	public PersonalDetails(String firstName, String lastName, String empId, String gender, String maritalStatus,
			String nationality, String dob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.empId = empId;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.nationality = nationality;
		this.dob = dob;
	}
	public PersonalDetails(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
	
	
}
