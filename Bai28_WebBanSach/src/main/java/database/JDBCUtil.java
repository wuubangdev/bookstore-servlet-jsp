package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection c = null;

		try {

			DriverManager.registerDriver(new Driver());
			String url = "jdbc:mysql://localhost:3306/myweb";
			String user = "root";
			String password = "";
			DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if (c!=null) {
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
