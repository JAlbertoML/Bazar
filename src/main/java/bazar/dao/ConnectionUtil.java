package bazar.dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;

public class ConnectionUtil {
	private static ComboPooledDataSource comboPooledDataSource;

	static {
		try {
			comboPooledDataSource = new ComboPooledDataSource();
			comboPooledDataSource.setDriverClass("org.postgresql.Driver");
			comboPooledDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/bazar");
			comboPooledDataSource.setUser(System.getenv().get("psqlUser"));
			comboPooledDataSource.setPassword(System.getenv().get("psqlPass"));
			comboPooledDataSource.setAcquireIncrement(100);
			comboPooledDataSource.setInitialPoolSize(5);
			comboPooledDataSource.setMaxPoolSize(1000);
			comboPooledDataSource.setMaxIdleTime(0);
			comboPooledDataSource.setMinPoolSize(5);
		} catch (PropertyVetoException e) {
			// TODO: handle exception
		}

	}

	public static Connection getConnection() throws SQLException {
		return comboPooledDataSource.getConnection();
	}
}
