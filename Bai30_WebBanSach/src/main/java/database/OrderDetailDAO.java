package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Book;
import model.Order;
import model.OrderDetail;

public class OrderDetailDAO implements DAOInterface<OrderDetail> {

	public static OrderDetailDAO getInstance() {
		return new OrderDetailDAO();
	}

	@Override
	public int insert(OrderDetail t) {
		OrderDetail orderDetail = this.selectById(t);
		if (orderDetail == null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "INSERT INTO orderDetails VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getOrderDetailId());
				pStatement.setString(2, t.getOrder().getOrderId());
				pStatement.setString(3, t.getBook().getBookId());
				pStatement.setInt(4, t.getQuantity());
				pStatement.setDouble(5, t.getRealPrice());
				pStatement.setDouble(6, t.getSale());
				pStatement.setDouble(7, t.getSalePrice());
				pStatement.setDouble(8, t.getVat());
				pStatement.setDouble(9, t.getTotalPrice());
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
	public int insertAll(ArrayList<OrderDetail> list) {
		int count = 0;
		for (OrderDetail orderDetail : list) {
			count += this.insert(orderDetail);
		}
		return count;
	}

	@Override
	public OrderDetail selectById(OrderDetail t) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM orderDetails WHERE orderDetailId = ?";
			PreparedStatement pStatement = connection.prepareStatement(url);
			pStatement.setString(1, t.getOrderDetailId());
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String orderDetailId = rs.getString("orderDetailId");
				String orderId = rs.getString("orderId");
				String bookId = rs.getString("bookId");
				Integer quantity = rs.getInt("quantity");
				Double realPrice = rs.getDouble("realPrice");
				Double sale = rs.getDouble("sale");
				Double salePrice = rs.getDouble("salePrice");
				Double vat = rs.getDouble("vat");
				Double totalPrice = rs.getDouble("totalPrice");
				
				Order order = OrderDAO.getInstance().selectById(new Order(orderId));
				Book book = BookDAO.getInstance().selectById(new Book(bookId));
				
				return new OrderDetail(orderDetailId, order, book, quantity, realPrice, sale, salePrice, vat, totalPrice);
			}
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<OrderDetail> selectAll() {
		ArrayList<OrderDetail> data = new ArrayList<OrderDetail>();
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM orderDetails";
			PreparedStatement pStatement = connection.prepareStatement(url);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String orderDetailId = rs.getString("orderDetailId");
				String orderId = rs.getString("orderId");
				String bookId = rs.getString("bookId");
				Integer quantity = rs.getInt("quantity");
				Double realPrice = rs.getDouble("realPrice");
				Double sale = rs.getDouble("sale");
				Double salePrice = rs.getDouble("salePrice");
				Double vat = rs.getDouble("vat");
				Double totalPrice = rs.getDouble("totalPrice");
				
				Order order = OrderDAO.getInstance().selectById(new Order(orderId));
				Book book = BookDAO.getInstance().selectById(new Book(bookId));
				
				OrderDetail orderDetail = new OrderDetail(orderDetailId, order, book, quantity, realPrice, sale, salePrice, vat, totalPrice);
				data.add(orderDetail);
			}

			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@Override
	public int update(OrderDetail t) {
		OrderDetail orderDetail = this.selectById(t);
		if (orderDetail != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "UPDATE orderDetails SET (?,?,?,?,?,?,?,?,?) WHERE orderDetailId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getOrderDetailId());
				pStatement.setString(2, t.getOrder().getOrderId());
				pStatement.setString(3, t.getBook().getBookId());
				pStatement.setInt(4, t.getQuantity());
				pStatement.setDouble(5, t.getRealPrice());
				pStatement.setDouble(6, t.getSale());
				pStatement.setDouble(7, t.getSalePrice());
				pStatement.setDouble(8, t.getVat());
				pStatement.setDouble(9, t.getTotalPrice());
				pStatement.setString(10, t.getOrderDetailId());
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
	public int delete(OrderDetail t) {
		OrderDetail orderDetail = this.selectById(t);
		if (orderDetail != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "DELETE FROM orderDetails WHERE orderDetailId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getOrderDetailId());
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
	public int deleteAll(ArrayList<OrderDetail> list) {
		int count = 0;
		for (OrderDetail orderDetail : list) {
			count += this.delete(orderDetail);
		}
		return count;
	}
	
}
