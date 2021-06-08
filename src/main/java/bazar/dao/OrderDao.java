package bazar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.google.gson.Gson;
import bazar.pojos.Order;

public class OrderDao {
	public static Boolean addOrder(Order order) {
		boolean correct = false;
		Gson gson = new Gson();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO \"Order\"(dateOrder, products, amount, status, Clientid) VALUES (?, ?, ?, ?, ?)");
			String productsJson = gson.toJson(order.getProducts());
			preparedStatement.setDate(1, java.sql.Date.valueOf(order.getDate()));
			preparedStatement.setString(2, productsJson);
			preparedStatement.setBigDecimal(3, order.getAmount());
			preparedStatement.setString(4, order.getStatus());
			preparedStatement.setInt(5, order.getClientId());
			Integer result = preparedStatement.executeUpdate();
			if (result > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar registro");
			e.printStackTrace();
		}
		return correct;
	}
}
