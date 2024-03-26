package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Order;

public class OrderDAO implements DAOInterface<Order> {

	public static OrderDAO getInstance() {
		return new OrderDAO();
	}

	@Override
	public int insert(Order t) {
		Order order = this.selectById(t);
		if (order == null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "INSERT INTO orders VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getOrderId());
				pStatement.setString(2, t.getCustomer().getCustomerId());
				pStatement.setString(3, t.getCustomerAddress());
				pStatement.setString(4, t.getReceiAddress());
				pStatement.setString(5, t.getorderStatus());
				pStatement.setString(6, t.getPayments());
				pStatement.setString(7, t.getPaymentSatatus());
				pStatement.setDouble(8, t.getAmountPaid());
				pStatement.setDouble(9, t.getAmountUnpaid());
				pStatement.setDate(10, t.getOrderDate());
				pStatement.setDate(11, t.getShippedDate());
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
	public int insertAll(ArrayList<Order> list) {
		int count = 0;
		for (Order order : list) {
			count += this.insert(order);
		}
		return count;
	}

	@Override
	public Order selectById(Order t) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM orders WHERE orderId = ?";
			PreparedStatement pStatement = connection.prepareStatement(url);
			pStatement.setString(1, t.getOrderId());
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String orderId = rs.getString("orderId");
				String customerId = rs.getString("customerId");
				String customerAddress = rs.getString("customerAddress");
				String receiAddress = rs.getString("receiAddress");
				String orderStatus = rs.getString("orderStatus");
				String payment = rs.getString("payment");
				String paymentStatus = rs.getString("paymentStatus");
				Double amountPaid = rs.getDouble("amountPaid");
				Double amountUnpaind = rs.getDouble("amountUnpaind");
				Date orderDate = rs.getDate("orderDate");
				Date shippedDate = rs.getDate("shippedDate");
				
				Customer customer = CustomerDAO.getInstance().selectById(new Customer(customerId));
				
				return new Order(orderId, customer, customerAddress, receiAddress, orderStatus, payment, paymentStatus, amountPaid, amountUnpaind, orderDate, shippedDate);
			}
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<Order> selectAll() {
		ArrayList<Order> data = new ArrayList<Order>();
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM orders";
			PreparedStatement pStatement = connection.prepareStatement(url);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String orderId = rs.getString("orderId");
				String customerId = rs.getString("customerId");
				String customerAddress = rs.getString("customerAddress");
				String receiAddress = rs.getString("receiAddress");
				String orderStatus = rs.getString("orderStatus");
				String payment = rs.getString("payment");
				String paymentStatus = rs.getString("paymentStatus");
				Double amountPaid = rs.getDouble("amountPaid");
				Double amountUnpaind = rs.getDouble("amountUnpaind");
				Date orderDate = rs.getDate("orderDate");
				Date shippedDate = rs.getDate("shippedDate");
				
				Customer customer = CustomerDAO.getInstance().selectById(new Customer(customerId));
				
				Order order = new Order(orderId, customer, customerAddress, receiAddress, orderStatus, payment, paymentStatus, amountPaid, amountUnpaind, orderDate, shippedDate);
				data.add(order);
			}

			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@Override
	public int update(Order t) {
		Order order = this.selectById(t);
		if (order != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "UPDATE orders SET (?,?,?,?,?,?,?,?,?,?,?) WHERE orderId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getOrderId());
				pStatement.setString(2, t.getCustomer().getCustomerId());
				pStatement.setString(3, t.getCustomerAddress());
				pStatement.setString(4, t.getReceiAddress());
				pStatement.setString(5, t.getorderStatus());
				pStatement.setString(6, t.getPayments());
				pStatement.setString(7, t.getPaymentSatatus());
				pStatement.setDouble(8, t.getAmountPaid());
				pStatement.setDouble(9, t.getAmountUnpaid());
				pStatement.setDate(10, t.getOrderDate());
				pStatement.setDate(11, t.getShippedDate());
				pStatement.setString(12, t.getOrderId());
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
	public int delete(Order t) {
		Order order = this.selectById(t);
		if (order != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "DELETE FROM orders WHERE orderId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getOrderId());
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
	public int deleteAll(ArrayList<Order> list) {
		int count = 0;
		for (Order order : list) {
			count += this.delete(order);
		}
		return count;
	}
	
}
