package com.revature.banking.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.banking.dao.CustomersDAO;
import com.revature.banking.dao.CustomersDAOImpl;
import com.revature.banking.dao.EmployeeDAO;
import com.revature.banking.dao.EmployeeDAOImpl;
import com.revature.banking.model.Customers;

public class BankingApp {
	Logger logger=Logger.getLogger("Insight Banking App");
	public int passwordMissmatchValue=0;
	
	public void startInsightApp () {
		

		CustomersDAO customersDAO=new CustomersDAOImpl();
		Customers customers=new Customers();
		
		EmployeeDAO employeeDAO=new EmployeeDAOImpl();
		Scanner sc=new Scanner(System.in);
	    boolean res=false;
		while(true) {
//			customersDAO.deleteCustomer(-6465);
			System.out.println("======================================================================================================");
			System.out.println();
			System.out.println("                                            BANKING APP                                               ");
			System.out.println();
			System.out.println("======================================================================================================");
			System.out.println();
			System.out.println("                           P L E A S E   E N T E R  Y O U R   C H O I C E                             ");
			System.out.println();
			System.out.println("                                1. C U S T O M E R      L O G I N                                      ");
			System.out.println();
			System.out.println("                                2. E M P L O Y E E      L O G I N                                      ");
			System.out.println();
			System.out.println("								 3.C R E A T E       A C C O U N T                                       ");
			System.out.println();
			System.out.println("                                     4. A B O U T      U S                                               ");
			System.out.println();
			System.out.println("                                           9. E X I T                                                    ");
			int choice=0;
			try {
				choice =sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("please enter digits only.. Thanks for using my app.");
				// TODO Auto-generated catch block
				System.exit(0);
			}
			switch(choice) {
			case 1:
				System.out.println("Enter your UserName : ");
				String customerUserName=sc.next();
				System.out.println("Enter your Password : ");
				String customerPassword=sc.next();
				logger.info("customer entered into CUSTOMER LOGIN section with userName"+customerUserName);
				customers=customersDAO.validateCustomer(customerUserName,customerPassword);
					if (customers != null) {
						 Long cifId=customers.getCustomerId();
						 if (cifId>=100000 && cifId<=999999 ) {
						CustomerPage customerPage = new CustomerPage();
						logger.info("customer validated and sending to CustomerPage class with userName"
								+ customerUserName);
						customerPage.customerDashboard(customers);
						break;
					} 
						 else {
							 System.out.println("Your account not Approved yet(or Denied)");
							 System.out.println("It will take 24 to 48 hours for approval,Contact our customer support for further assistance...");
							 break;
						 }
					} else {
						logger.error("customer username or password did not match and entered values are UserName: "
								+ customerUserName + " Password: " + customerPassword);
						System.out.println("UserName or Password does not match, Please try again");
						break;
					} 
			case 2:
				System.out.println("Enter your UserName : ");
				String employeeUserName=sc.next();
				System.out.println("Enter your Password : ");
				String employeePassword=sc.next();
				logger.info("Employee entered into EMPLOYEE LOGIN section with userName"+employeeUserName);
				String employeeName=employeeDAO.validateEmployee(employeeUserName,employeePassword);
				if(employeeName!=null){
					EmployeePage emploteePage=new EmployeePage();
					logger.info("Employee validated and sending to EmployeePage class with UserName "+employeeUserName);
					emploteePage.employeeDashboard(employeeName);
				}
				else {
					logger.error("Employee username or password did not match and entered values are userName: "+employeeUserName+" Password: "+employeePassword);
					System.out.println("UserName or Password does not match, Please try again");
				}
				break;
			case 3:
				logger.info("new customer entered into CREATE ACCOUNT section");
				customers=getCustomerDetails();
				if (customers!=null) {
					res = customersDAO.addCustomer(customers);
					if (res == true) {
						logger.info("customer has successfully created account with cif Id: "+customers.getCustomerId());
						System.out.println("Congratulations " + customers.getCustomerFirstName()
								+ " Your Account Successfully created");
						System.out.println("Your account number is "+customers.getAccountNumber()+" and Customer Id is "+customers.getCustomerId());
						System.out.println("It will be approved soon , Kindly check your mail frequently for an update");
					} else {
						logger.error("Customer account not created as entered invalid details");
						System.out.println("Something went wrong,Please try again");
					}
					break;
				}
				break;
			case 4:

				logger.info("customer entered about app section");
				customersDAO.aboutApp();
				break;
			case 9:
				logger.info("customer choosen the exit option");
				System.exit(0);
				System.out.println("Thank you for using my App,Visit Again");
				break;
			default:
				logger.fatal("Customer selected invalid option");

				System.out.println("Invalid Option..Please try again");
			}
			
		}
	}
	public Customers getCustomerDetails() {

		logger.info("customer came to getCustomerDetails method to enter inputs manually");
		
		  	Scanner sc=new Scanner(System.in);
		  	System.out.println("Enter your First Name : "); String customerFirstName=sc.nextLine();
		  	System.out.println("Enter your last name : ");  String customerLastName=sc.next();
		  	System.out.println("Enter Username : "); String customerUserName=sc.next();
		  	System.out.println("Enter password : ");String customerPassword=sc.next();
		  	System.out.println("Re-enter password : "); String customerReEnteredPassword=sc.next();
			System.out.println("Enter the amount you want to open with : "); int accountBalance=sc.nextInt();
			if(customerPassword.equals(customerReEnteredPassword)) {
				Long accountNumber = (long) (100000000 + Math.random() * 900000000);
				Long customerId= (long) (10000 + Math.random() * 90000);

				logger.info("account number of 9 digits and cifid of 5 degits generated using random method");


				Customers customers=new Customers(customerId, customerFirstName, customerLastName, customerUserName, customerPassword, accountNumber, accountBalance);

				return customers;
			}
			else {
				logger.error("Customer entered different passwords ...password mismatch. Entered passwords are:"+customerPassword+" and "+customerReEnteredPassword);
				System.out.println("Password mismatch...Please try again");
				return null;
			}
	}
}
