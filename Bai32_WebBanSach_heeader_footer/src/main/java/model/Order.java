package model;

import java.sql.Date;
import java.util.Objects;

public class Order {
	private String orderId;
	private Customer customer;
	private String customerAddress;
	private String receiAddress;
	private String orderStatus; 
	private String payments; 
	private String paymentSatatus;
	private Double amountPaid;
	private Double amountUnpaid;
	private Date orderDate;
	private Date shippedDate;

	public Order() {
	}
	
	public Order(String orderId) {
		this.orderId = orderId;
	}

	public Order(String orderId, Customer customer, String customerAddress, String receiAddress, String orderStatus,
			String payments, String paymentSatatus, Double amountPaid, Double amountUnpaid, Date orderDate,
			Date shippedDate) {
		this.orderId = orderId;
		this.customer = customer;
		this.customerAddress = customerAddress;
		this.receiAddress = receiAddress;
		this.orderStatus = orderStatus;
		this.payments = payments;
		this.paymentSatatus = paymentSatatus;
		this.amountPaid = amountPaid;
		this.amountUnpaid = amountUnpaid;
		this.orderDate = orderDate;
		this.shippedDate = shippedDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getReceiAddress() {
		return receiAddress;
	}

	public void setReceiAddress(String receiAddress) {
		this.receiAddress = receiAddress;
	}

	public String getorderStatus() {
		return orderStatus;
	}

	public void setorderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayments() {
		return payments;
	}

	public void setPayments(String payments) {
		this.payments = payments;
	}

	public String getPaymentSatatus() {
		return paymentSatatus;
	}

	public void setPaymentSatatus(String paymentSatatus) {
		this.paymentSatatus = paymentSatatus;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Double getAmountUnpaid() {
		return amountUnpaid;
	}

	public void setAmountUnpaid(Double amountUnpaid) {
		this.amountUnpaid = amountUnpaid;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(orderId, other.orderId);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", customerAddress=" + customerAddress
				+ ", receiAddress=" + receiAddress + ", orderStatus=" + orderStatus + ", payments=" + payments
				+ ", paymentSatatus=" + paymentSatatus + ", amountPaid=" + amountPaid + ", amountUnpaid=" + amountUnpaid
				+ ", orderDate=" + orderDate + ", shippedDate=" + shippedDate + "]";
	}
	
}
