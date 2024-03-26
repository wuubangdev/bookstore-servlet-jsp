package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Author;
import model.Book;
import model.Catalogy;

public class BookDAO implements DAOInterface<Book> {

	public static BookDAO getInstance() {
		return new BookDAO();
	}

	@Override
	public int insert(Book t) {
		Book book = this.selectById(t);
		if (book == null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "INSERT INTO books VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getBookId());
				pStatement.setString(2, t.getBookName());
				pStatement.setString(3, t.getAuthor().getAuthorId());
				pStatement.setInt(4, t.getYop());
				pStatement.setDouble(5, t.getPriceBuy());
				pStatement.setDouble(6, t.getPriceReal());
				pStatement.setDouble(7, t.getPriceSale());
				pStatement.setInt(8, t.getQuantity());
				pStatement.setString(9, t.getCatalogy().getCatalogyId());
				pStatement.setString(10, t.getLanguage());
				pStatement.setString(11, t.getDescription());
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
	public int insertAll(ArrayList<Book> list) {
		int count = 0;
		for (Book book : list) {
			count += this.insert(book);
		}
		return count;
	}

	@Override
	public Book selectById(Book t) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM books WHERE bookId = ?";
			PreparedStatement pStatement = connection.prepareStatement(url);
			pStatement.setString(1, t.getBookId());
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				String authorId = rs.getString("authorId");
				Integer yop = rs.getInt("yop");
				Double priceBuy = rs.getDouble("priceBuy");
				Double priceReal = rs.getDouble("priceReal");
				Double priceSale = rs.getDouble("priceSale");
				Integer quantity = rs.getInt("quantity");
				String catalogyId = rs.getString("catalogyId");
				String language = rs.getString("language");
				String description = rs.getString("description");

				Author author = AuthorDAO.getInstance().selectById(new Author(authorId));
				Catalogy catalogy = CatalogyDAO.getInstance().selectById(new Catalogy(catalogyId));

				return new Book(bookId, bookName, author, yop, priceBuy, priceReal, priceSale, quantity, catalogy,
						language, description);
			}
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<Book> selectAll() {
		ArrayList<Book> data = new ArrayList<Book>();
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM books";
			PreparedStatement pStatement = connection.prepareStatement(url);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				String authorId = rs.getString("authorId");
				Integer yop = rs.getInt("yop");
				Double priceBuy = rs.getDouble("priceBuy");
				Double priceReal = rs.getDouble("priceReal");
				Double priceSale = rs.getDouble("priceSale");
				Integer quantity = rs.getInt("quantity");
				String catalogyId = rs.getString("catalogyId");
				String language = rs.getString("language");
				String description = rs.getString("description");

				Author author = AuthorDAO.getInstance().selectById(new Author(authorId));
				Catalogy catalogy = CatalogyDAO.getInstance().selectById(new Catalogy(catalogyId));

				Book book = new Book(bookId, bookName, author, yop, priceBuy, priceReal, priceSale, quantity, catalogy,
						language, description);
				data.add(book);
			}

			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@Override
	public int update(Book t) {
		Book book = this.selectById(t);
		if (book != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "UPDATE books SET (?,?,?,?,?,?,?,?,?,?,?) WHERE bookId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getBookId());
				pStatement.setString(2, t.getBookName());
				pStatement.setString(3, t.getAuthor().getAuthorId());
				pStatement.setInt(4, t.getYop());
				pStatement.setDouble(5, t.getPriceBuy());
				pStatement.setDouble(6, t.getPriceReal());
				pStatement.setDouble(7, t.getPriceSale());
				pStatement.setInt(8, t.getQuantity());
				pStatement.setString(9, t.getCatalogy().getCatalogyId());
				pStatement.setString(10, t.getLanguage());
				pStatement.setString(11, t.getDescription());
				pStatement.setString(12, t.getBookId());
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
	public int delete(Book t) {
		Book book = this.selectById(t);
		if (book != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "DELETE FROM books WHERE bookId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getBookId());
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
	public int deleteAll(ArrayList<Book> list) {
		int count = 0;
		for (Book book : list) {
			count += this.delete(book);
		}
		return count;
	}

}
