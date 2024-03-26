package database;

import java.util.ArrayList;

import model.Order;

public class OrderDAO implements DAOInterface<Order> {

	ArrayList<Order> data = new ArrayList<Order>();

	public static OrderDAO getInstance() {
		return new OrderDAO();
	}

	@Override
	public int insert(Order t) {
		Order order = this.selectById(t);
		if (order == null) {
			data.add(order);
			return 1;
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
		for (Order order : data) {
			if (order.equals(t)) {
				return order;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Order> selectAll() {
		return this.data;
	}

	@Override
	public int update(Order t) {
		Order order = this.selectById(t);
		if (order!=null) {
			this.data.remove(order);
			this.data.add(t);
			return 1;
		}
		
		return 0;
	}

	@Override
	public int delete(Order t) {
		Order order = this.selectById(t);
		if (order != null) {
			//Xoa chi tiet don hang "OrderDetail"
			OrderDetailDAO.getInstance().deleteAll(order);
			//Xoa don hang "Order"
			this.data.remove(order);
			return 1;
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
