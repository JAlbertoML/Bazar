package bazar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bazar.pojos.Admin;
import bazar.pojos.User;

public class AdminDao {
	public static Admin getAdminByUsername(String username) {
		Admin admin = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Admin where username = ?");
			preparedStatement.setString(1, username);
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			if(result.next()) {
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String surname = result.getString("surname");
				String lastname = result.getString("lastname");
				String password = result.getString("password");
				User user = new User(username, password, User.TYPE_ADMIN);
				admin = new Admin(id, name, surname, lastname, user);
			}
		} catch (SQLException e) {
			System.out.println("--> Error en la consulta de administrador");
			e.printStackTrace();
		}
		return admin;
	}
}
