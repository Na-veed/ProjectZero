package com.revature.banking.model;

public class Customers {
	private Long customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerUserName;
	private String customerPassword;
	private long accountNumber;
	private long accountBalance;
	
	//Default Constructor
	public Customers() {
		// TODO Auto-generated constructor stub
	}
	
	//Parameterized Constructor
	public Customers(Long customerCifId, String customerFirstName, String customerLastName, String customerUserName,
			String customerPassword, long accountNumber, long accountBalance) {
		super();
		this.customerId = customerCifId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerUserName = customerUserName;
		this.customerPassword = customerPassword;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
	//Getter Setter Methods
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerCifId) {
		this.customerId = customerCifId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Customers [customerCifId=" + customerId + ", customerFirstName=" + customerFirstName
				+ ", customerLastName=" + customerLastName + ", customerUserName=" + customerUserName
				+ ", customerPassword=" + customerPassword + ", accountNumber=" + accountNumber + ", accountBalance="
				+ accountBalance + "]";
	}

}
