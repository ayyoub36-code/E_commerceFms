package fr.fms.bdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BddConnection {

	public static Connection getConnection() {

		Connection connection = null;
		String filename = "src/config.properties";

		try (InputStream input = new FileInputStream(filename)) {

			Properties prop = new Properties();

			prop.load(input);

			// driver
			String driver = prop.getProperty("db.driver.class");
			if (driver != null)
				Class.forName(driver);

			// params
			String url = prop.getProperty("db.url");
			String login = prop.getProperty("db.login");
			String password = prop.getProperty("db.password");

			// connection
			connection = DriverManager.getConnection(url, login, password);

		} catch (IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
