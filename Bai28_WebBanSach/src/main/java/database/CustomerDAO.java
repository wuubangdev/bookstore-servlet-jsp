package database;

import java.util.ArrayList;

import model.Customer;

public class CustomerDAO implements DAOInterface<Customer> {

	ArrayList<Customer> data = new ArrayList<Customer>();

	public static CustomerDAO getInstance() {
		return new CustomerDAO();
	}

	@Override
	public int insert(Customer t) {
		Customer customer = this.selectById(t);
		if (customer == null) {
			data.add(customer);
			return 1;
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
		for (Customer customer : data) {
			if (customer.equals(t)) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Customer> selectAll() {
		return this.data;
	}

	@Override
	public int update(Customer t) {
		Customer customer = this.selectById(t);
		if (customer!=null) {
			this.data.remove(customer);
			this.data.add(t);
			return 1;
		}
		
		return 0;
	}

	@Override
	public int delete(Customer t) {
		Customer customer = this.selectById(t);
		if (customer != null) {
			this.data.remove(customer);
			return 1;
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
