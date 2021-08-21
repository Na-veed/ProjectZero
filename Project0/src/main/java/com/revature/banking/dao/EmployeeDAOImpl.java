package com.revature.banking.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.revature.banking.model.Customers;
import com.revature.banking.model.Employee;
import com.revature.banking.util.DbConnection;

public class EmployeeDAOImpl implements EmployeeDAO {

	// connecting to the database
	Connection connection = DbConnection.getDbConnection();
	Customers customers = new Customers();
	Employee employee = new Employee();

	// Sql Query statements based on our requirement
	private String SQL_QUERY_FOR_CUSTOMER_BALANCE = "call project0.getCustomerBalance(?,?)";
	private String SQL_QUERY_FOR_GET_ALL_CUSTOMER = "select * from project0.customers";
	private String SQL_QUERY_FOR_GET_CUSTOMER = "select * from project0.customers where accountNumber=?";
	private String SQL_QUERY_FOR_LOGIN = "select employeeUserName,employeePassword from project0.employee where employeeUserName=? and employeePassword=? ";
	private String SQL_QUERY_FOR_GET_EMPLOYEE_NAME = "select employeeFirstName,employeeLastName from project0.employee where employeeUserName=?";
	private String SQL_QUERY_FOR_FIRST_NAME = "select customerFirstName from project0.customers where accountNumber=?";
	private String GET_CUSTOMER_ID = "select customerCifId from project0.customers where accountNumber=?";
	private String SQL_QUERY_FOR_UPDATE_CUSTOMER = "update project0.customers set customerCifId=? where accountNumber=?";
	// calling stored procedure

	// method to view account balance using account Number ,return balance
	public int viewAccountBalance(int accountNumber) {
		int accountBalance = -1;
		try {

			CallableStatement statement = connection.prepareCall(SQL_QUERY_FOR_CUSTOMER_BALANCE);
			statement.setInt(1, accountNumber);
			statement.registerOutParameter(2, Types.INTEGER);
			statement.setInt(2, accountBalance);
			statement.execute();
			accountBalance = statement.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(accountBalance);
		return accountBalance;
	}

	// method to get all the bank accounts of bank,prints all data in table
	public void getCustomerBankAccounts() {
		// TODO Auto-generated method stub
		ResultSet res = null;
		try {
			Statement statement = connection.createStatement();
			res = statement.executeQuery(SQL_QUERY_FOR_GET_ALL_CUSTOMER);
			ResultSetMetaData rsmd = res.getMetaData();
			int columnCount = rsmd.getColumnCount();
//			 System.out.println(columnCount);
			while (res.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(res.getString(i) + "\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method to get particular customer details using account number,return
	// customers object with all the details
	public Customers getCustomerDetails(int accountNumber) {
		// TODO Auto-generated method stub
		ResultSet res = null;

		try {
			PreparedStatement statement = connection.prepareStatement(SQL_QUERY_FOR_GET_CUSTOMER);
			statement.setInt(1, accountNumber);
			res = statement.executeQuery();
			if (res.next()) {
				customers = new Customers(res.getLong(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getLong(6), res.getInt(7));
			} else {
				customers = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Account Number does not exist, Please try again");
		}

//		System.out.println(customers);
//		System.out.println(customers.getCustomerFirstName());
		return customers;

	}

	// method to validate the employee ,if exists in the database then return
	// employee full name
	public String validateEmployee(String employeeUserName, String employeePassword) {
		// TODO Auto-generated method stub
		ResultSet res;
		int res1 = 1;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_QUERY_FOR_LOGIN);
			statement.setString(1, employeeUserName);
			statement.setString(2, employeePassword);
			res = statement.executeQuery();
			if (res.next())
				res1 = 1;
			else
				res1 = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("You entered Wrong UserName or Password, Please try again");
		}
		if (res1 == 1) {
//			return employee.getEmployeeFirstName();
			ResultSet res2 = null;
			String employeeName = null;
			try {
				PreparedStatement statement = connection.prepareStatement(SQL_QUERY_FOR_GET_EMPLOYEE_NAME);
				statement.setString(1, employeeUserName);
				res2 = statement.executeQuery();
				if (res2.next()) {
					employeeName = res2.getString(1) + " " + res2.getString(2);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return employeeName;
		} else {
			return null;
		}
	}

	// method to check the customer existence using account number ,return customer
	// name
	public String isCustomerExist(int accountNumber) {
		// TODO Auto-generated method stub
		ResultSet res = null;
		String res1 = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_QUERY_FOR_FIRST_NAME);
			statement.setInt(1, accountNumber);
			res = statement.executeQuery();
			if (res.next())
				res1 = res.getString(1);
			else
				res1 = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res1;
	}

	public boolean approveAccount(int accountNumber) {
		// TODO Auto-generated method stub
		ResultSet res = null;
		int res1 = 0;
		int cif = 0, newCif = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_ID);
			statement.setInt(1, accountNumber);
			res = statement.executeQuery();
			if (res.next()) {
				cif = res.getInt(1);
			}
			newCif = cif + 100000;
			PreparedStatement stat = connection.prepareStatement(SQL_QUERY_FOR_UPDATE_CUSTOMER);
			stat.setInt(1, newCif);
			stat.setInt(2, accountNumber);
			res1 = stat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (res1 != 1) {
			return false;
		} else {
			return true;
		}

	}
}
