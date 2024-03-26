package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Catalogy;

public class CatalogyDAO implements DAOInterface<Catalogy> {

	public static CatalogyDAO getInstance() {
		return new CatalogyDAO();
	}

	@Override
	public int insert(Catalogy t) {
		Catalogy catalogy = this.selectById(t);
		if (catalogy == null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "INSERT INTO catalogys VALUES (?,?)";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getCatalogyId());
				pStatement.setString(2, t.getCatalogyName());
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
	public int insertAll(ArrayList<Catalogy> list) {
		int count = 0;
		for (Catalogy catalogy : list) {
			count += this.insert(catalogy);
		}
		return count;
	}

	@Override
	public Catalogy selectById(Catalogy t) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM catalogys WHERE catalogyId = ?";
			PreparedStatement pStatement = connection.prepareStatement(url);
			pStatement.setString(1, t.getCatalogyId());
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String catalogyId = rs.getString("catalogyId");
				String catalogyName = rs.getString("catalogyName");
				return new Catalogy(catalogyId, catalogyName);
			}
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<Catalogy> selectAll() {
		ArrayList<Catalogy> data = new ArrayList<Catalogy>();
		try {
			Connection connection = JDBCUtil.getConnection();
			String url = "SELECT* FROM catalogys";
			PreparedStatement pStatement = connection.prepareStatement(url);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				String catalogyId = rs.getString("catalogyId");
				String catalogyName = rs.getString("catalogyName");
				Catalogy catalogy = new Catalogy(catalogyId, catalogyName);
				data.add(catalogy);
			}

			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@Override
	public int update(Catalogy t) {
		Catalogy catalogy = this.selectById(t);
		if (catalogy != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "UPDATE catalogys SET (?,?) WHERE catalogyId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getCatalogyId());
				pStatement.setString(2, t.getCatalogyName());
				pStatement.setString(3, t.getCatalogyId());
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
	public int delete(Catalogy t) {
		Catalogy catalogy = this.selectById(t);
		if (catalogy != null) {
			try {
				Connection connection = JDBCUtil.getConnection();
				String url = "DELETE FROM catalogys WHERE catalogyId = ?";
				PreparedStatement pStatement = connection.prepareStatement(url);
				pStatement.setString(1, t.getCatalogyId());
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
	public int deleteAll(ArrayList<Catalogy> list) {
		int count = 0;
		for (Catalogy catalogy : list) {
			count += this.delete(catalogy);
		}
		return count;
	}
}
