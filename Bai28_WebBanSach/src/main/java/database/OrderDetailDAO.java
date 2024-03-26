package database;

import java.util.ArrayList;

import model.Order;
import model.OrderDetail;

public class OrderDetailDAO implements DAOInterface<OrderDetail> {

	ArrayList<OrderDetail> data = new ArrayList<OrderDetail>();

	public static OrderDetailDAO getInstance() {
		return new OrderDetailDAO();
	}

	@Override
	public int insert(OrderDetail t) {
		OrderDetail orderDetail = this.selectById(t);
		if (orderDetail == null) {
			data.add(orderDetail);
			return 1;
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
		for (OrderDetail orderDetail : data) {
			if (orderDetail.equals(t)) {
				return orderDetail;
			}
		}
		return null;
	}

	@Override
	public ArrayList<OrderDetail> selectAll() {
		return this.data;
	}

	@Override
	public int update(OrderDetail t) {
		OrderDetail orderDetail = this.selectById(t);
		if (orderDetail != null) {
			this.data.remove(orderDetail);
			this.data.add(t);
			return 1;
		}

		return 0;
	}

	@Override
	public int delete(OrderDetail t) {
		OrderDetail orderDetail = this.selectById(t);
		if (orderDetail != null) {
			this.data.remove(orderDetail);
			return 1;
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
	
	public int deleteAll(Order o) {
		int count = 0;
		for (OrderDetail orderDetail : data) {
			if (orderDetail.getOrder().equals(o)) {
				this.data.remove(orderDetail);
				count++;
			}
		}
		return count;
	}

}
