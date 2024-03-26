package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Author;

public class AuthorDAO implements DAOInterface<Author> {

	public static AuthorDAO getInstance() {
		return new AuthorDAO();
	}

	@Override
	public int insert(Author t) {
		Author author = this.selectById(t);
		if (author == null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "INSERT INTO authors VALUES (?,?,?,?)";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getAuthorId());
				pStatement.setString(2, t.getAuthorName());
				pStatement.setDate(3, t.getDob());
				pStatement.setString(4, t.getBiography());
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
	public int insertAll(ArrayList<Author> list) {
		int count = 0;
		for (Author author : list) {
			count += this.insert(author);
		}
		return count;
	}

	@Override
	public Author selectById(Author t) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM authors WHERE authorId = ?";
			PreparedStatement pStatement = connection.prepareStatement(url);
			pStatement.setString(1, t.getAuthorId());
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String authorId = rs.getString("authorId");
				String authorName = rs.getString("authorName");
				Date dob = rs.getDate("dob");
				String biography = rs.getString("biography");
				return new Author(authorId, authorName, dob, biography);
			}
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<Author> selectAll() {
		ArrayList<Author> data = new ArrayList<Author>();
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM authors";
			PreparedStatement pStatement = connection.prepareStatement(url);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String authorId = rs.getString("authorId");
				String authorName = rs.getString("authorName");
				Date dob = rs.getDate("dob");
				String biography = rs.getString("biography");
				Author author = new Author(authorId, authorName, dob, biography);
				data.add(author);
			}

			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@Override
	public int update(Author t) {
		Author author = this.selectById(t);
		if (author != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "UPDATE authors SET (?,?,?,?) WHERE authorId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getAuthorId());
				pStatement.setString(2, t.getAuthorName());
				pStatement.setDate(3, t.getDob());
				pStatement.setString(4, t.getBiography());
				pStatement.setString(5, t.getAuthorId());
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
	public int delete(Author t) {
		Author author = this.selectById(t);
		if (author != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "DELETE FROM authors WHERE authorId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getAuthorId());
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
	public int deleteAll(ArrayList<Author> list) {
		int count = 0;
		for (Author author : list) {
			count += this.delete(author);
		}
		return count;
	}
}
