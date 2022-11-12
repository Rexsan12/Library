package net.codejava.javaee.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public CustomerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertCustomer(Customer customer) throws SQLException {
		String sql = "INSERT INTO customer (name, accountno, balance) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getAccountno());
		statement.setFloat(3, customer.getBalance());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Customer> listAllCustomer() throws SQLException {
		List<Customer> listCustomer = new ArrayList<>();
		
		String sql = "SELECT * FROM customer";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("customer_id");
			String name = resultSet.getString("name");
			String accountno = resultSet.getString("accountno");
			float balance = resultSet.getFloat("balance");
			
			Customer customer = new Customer(id, name, accountno, balance);
			listCustomer.add(customer);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listCustomer;
	}
	
	public boolean deleteCustomer(Customer customer) throws SQLException {
		String sql = "DELETE FROM customer where customer_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, customer.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateCustomer(Customer customer) throws SQLException {
		String sql = "UPDATE customer SET name = ?, accountno = ?, balance = ?";
		sql += " WHERE customer_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getAccountno());
		statement.setFloat(3, customer.getBalance());
		statement.setInt(4, customer.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public Customer getCustomer(int id) throws SQLException {
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE customer_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			String accountno = resultSet.getString("accountno");
			float balance = resultSet.getFloat("balance");
			
			customer = new Customer(id, name, accountno, balance);
		}
		
		resultSet.close();
		statement.close();
		
		return customer;
	}
}
