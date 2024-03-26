package model;

import java.util.Objects;

public class Book {
	private String bookId;
	private String bookName;
	private Author author;
	private Integer yop;
	private Double priceBuy;
	private Double priceReal;
	private Double priceSale;
	private Integer quantity;
	private Catalogy catalogy;
	private String laguage;
	private String description;

	public Book() {
	}

	public Book(String bookId, String bookName, Author author, Integer yop, Double priceBuy, Double priceReal,
			Double priceSale, Integer quantity, Catalogy catalogy, String laguage, String description) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.yop = yop;
		this.priceBuy = priceBuy;
		this.priceReal = priceReal;
		this.priceSale = priceSale;
		this.quantity = quantity;
		this.catalogy = catalogy;
		this.laguage = laguage;
		this.description = description;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getYop() {
		return yop;
	}

	public void setYop(Integer yop) {
		this.yop = yop;
	}

	public Double getPriceBuy() {
		return priceBuy;
	}

	public void setPriceBuy(Double priceBuy) {
		this.priceBuy = priceBuy;
	}

	public Double getPriceReal() {
		return priceReal;
	}

	public void setPriceReal(Double priceReal) {
		this.priceReal = priceReal;
	}

	public Double getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(Double priceSale) {
		this.priceSale = priceSale;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Catalogy getCatalogy() {
		return catalogy;
	}

	public void setCatalogy(Catalogy catalogy) {
		this.catalogy = catalogy;
	}

	public String getLaguage() {
		return laguage;
	}

	public void setLaguage(String laguage) {
		this.laguage = laguage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(bookId, other.bookId);
	}
	
}
