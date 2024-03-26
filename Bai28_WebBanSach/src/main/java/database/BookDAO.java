package database;

import java.util.ArrayList;

import model.Book;

public class BookDAO implements DAOInterface<Book> {

	ArrayList<Book> data = new ArrayList<Book>();

	public static BookDAO getInstance() {
		return new BookDAO();
	}

	@Override
	public int insert(Book t) {
		Book book = this.selectById(t);
		if (book == null) {
			data.add(book);
			return 1;
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Book> list) {
		int count = 0;
		for (Book book : list) {
			count += this.insert(book);
		}

		return count;
	}

	@Override
	public Book selectById(Book t) {
		for (Book book : data) {
			if (book.equals(t)) {
				return book;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Book> selectAll() {
		return this.data;
	}

	@Override
	public int update(Book t) {
		Book book = this.selectById(t);
		if (book!=null) {
			this.data.remove(book);
			this.data.add(t);
			return 1;
		}
		
		return 0;
	}

	@Override
	public int delete(Book t) {
		Book book = this.selectById(t);
		if (book != null) {
			this.data.remove(book);
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<Book> list) {
		int count = 0;
		for (Book book : list) {
			count += this.delete(book);
		}
		return count;
	}

}
