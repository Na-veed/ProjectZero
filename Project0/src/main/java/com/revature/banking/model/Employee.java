package com.revature.banking.model;

public class Employee {

	private int employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeUserName;
	private String employeePassword;
	
	//default constructor
	public Employee() {
		// TODO Auto-generated constructor stub
		
	}
	//parameterized constructor
	public Employee(int employeeId, String employeeFirstName, String employeeLastName, String employeeUserName,
			String employeePassword) {
		super();
		this.employeeId = employeeId;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeeUserName = employeeUserName;
		this.employeePassword = employeePassword;
	}
	//getter and setter methods
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getEmployeeUserName() {
		return employeeUserName;
	}
	public void setEmployeeUserName(String employeeUserName) {
		this.employeeUserName = employeeUserName;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	@Override
	public String toString() {
		return "\nEmployee [employeeId=" + employeeId + ", employeeFirstName=" + employeeFirstName + ", employeeLastName="
				+ employeeLastName + ", employeeUserName=" + employeeUserName + ", employeePassword=" + employeePassword
				+ "]";
	}
}