package model;

import java.util.Objects;

public class OrderDetail {
	private String orderDetailId;
	private Order order;
	private Book book;
	private Integer quantity;
	private Double realPrice;
	private Double sale;
	private Double salePrice;
	private Double vat;
	private Double totalPrice;

	public OrderDetail() {
	}

	public OrderDetail(String orderDetailId, Order order, Book bookId, Integer quantity, Double realPrice,
			Double sale, Double salePrice, Double vat, Double totalPrice) {
		this.orderDetailId = orderDetailId;
		this.order = order;
		this.book = bookId;
		this.quantity = quantity;
		this.realPrice = realPrice;
		this.sale = sale;
		this.salePrice = salePrice;
		this.vat = vat;
		this.totalPrice = totalPrice;
	}

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book bookId) {
		this.book = bookId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public Double getSale() {
		return sale;
	}

	public void setSale(Double sale) {
		this.sale = sale;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderDetailId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		return Objects.equals(orderDetailId, other.orderDetailId);
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", order=" + order + ", book=" + book + ", quantity="
				+ quantity + ", realPrice=" + realPrice + ", sale=" + sale + ", salePrice=" + salePrice + ", vat=" + vat
				+ ", totalPrice=" + totalPrice + "]";
	}
	
}
