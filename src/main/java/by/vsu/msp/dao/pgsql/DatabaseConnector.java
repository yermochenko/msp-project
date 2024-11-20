package by.vsu.msp.dao.pgsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	private static String url;
	private static String username;
	private static String password;

	public static void init(String driverName, String url, String username, String password) throws ClassNotFoundException {
		Class.forName(driverName);
		DatabaseConnector.url = url;
		DatabaseConnector.username = username;
		DatabaseConnector.password = password;
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
