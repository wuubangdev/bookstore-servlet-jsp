package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerDAO implements DAOInterface<Customer> {

	public static CustomerDAO getInstance() {
		return new CustomerDAO();
	}

	@Override
	public int insert(Customer t) {
		Customer customer = this.selectById(t);
		if (customer == null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "INSERT INTO customers VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getCustomerId());
				pStatement.setString(2, t.getCustomerName());
				pStatement.setString(3, t.getUsername());
				pStatement.setString(4, t.getPassword());
				pStatement.setString(5, t.getGender());
				pStatement.setString(6, t.getAddress());
				pStatement.setString(7, t.getreceiAddress());
				pStatement.setString(8, t.getbuyAddress());
				pStatement.setDate(9, t.getDob());
				pStatement.setString(10, t.getPhoneNumber());
				pStatement.setString(11, t.getEmail());
				pStatement.setInt(12, t.isRecei_email());
				int rs = pStatement.executeUpdate();
				JDBCUtil.closeConnection(connection);
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Customer> list) {
		int count = 0;
		for (Customer customer : list) {
			count += this.insert(customer);
		}
		return count;
	}

	@Override
	public Customer selectById(Customer t) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM customers WHERE customerId = ?";
			PreparedStatement pStatement = connection.prepareStatement(url);
			pStatement.setString(1, t.getCustomerId());
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String customerId = rs.getString("customerId");
				String customerName = rs.getString("customerName");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String receiAddress = rs.getString("receiAddress");
				String buyAddress = rs.getString("buyAddress");
				Date dob = rs.getDate("dob");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				Integer isRecei_email = rs.getInt("isRecei_email");
				
				return new Customer(customerId, customerName, userName, password, gender, address, receiAddress, buyAddress, dob, phoneNumber, email, isRecei_email);
			}
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<Customer> selectAll() {
		ArrayList<Customer> data = new ArrayList<Customer>();
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM customers";
			PreparedStatement pStatement = connection.prepareStatement(url);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String customerId = rs.getString("customerId");
				String customerName = rs.getString("customerName");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String receiAddress = rs.getString("receiAddress");
				String buyAddress = rs.getString("buyAddress");
				Date dob = rs.getDate("dob");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				Integer isRecei_email = rs.getInt("isRecei_email");
				Customer customer =  new Customer(customerId, customerName, userName, password, gender, address, receiAddress, buyAddress, dob, phoneNumber, email, isRecei_email);
				data.add(customer);
			}

			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@Override
	public int update(Customer t) {
		Customer customer = this.selectById(t);
		if (customer != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "UPDATE customers SET (?,?,?,?,?,?,?,?,?,?,?,?) WHERE customerId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getCustomerId());
				pStatement.setString(2, t.getCustomerName());
				pStatement.setString(3, t.getUsername());
				pStatement.setString(4, t.getPassword());
				pStatement.setString(5, t.getGender());
				pStatement.setString(6, t.getAddress());
				pStatement.setString(7, t.getreceiAddress());
				pStatement.setString(8, t.getbuyAddress());
				pStatement.setDate(9, t.getDob());
				pStatement.setString(10, t.getPhoneNumber());
				pStatement.setString(11, t.getEmail());
				pStatement.setInt(12, t.isRecei_email());
				pStatement.setString(13, t.getCustomerId());
				int rs = pStatement.executeUpdate();
				JDBCUtil.closeConnection(connection);
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}

	@Override
	public int delete(Customer t) {
		Customer customer = this.selectById(t);
		if (customer != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "DELETE FROM customers WHERE customerId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getCustomerId());
				int rs = pStatement.executeUpdate();
				JDBCUtil.closeConnection(connection);
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<Customer> list) {
		int count = 0;
		for (Customer customer : list) {
			count += this.delete(customer);
		}
		return count;
	}
}
