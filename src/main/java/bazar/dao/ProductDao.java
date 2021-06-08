package bazar.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bazar.pojos.Product;

public class ProductDao {
	public static ArrayList<Product> getAllProducts() {
		Product product = null;
		ArrayList<Product> arrayProducts = new ArrayList<Product>();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Product");
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			while(result.next()) {
				Integer id = result.getInt("id");
				String name = result.getString("name");
				BigDecimal price = result.getBigDecimal("price");
				String description = result.getString("description");
				String photo = result.getString("photo");
				Integer quantity = result.getInt("quantity");
				Integer category = result.getInt("category");
				Integer quantitySold = result.getInt("quantitySold");
				BigDecimal discount = result.getBigDecimal("discount");
				product = new Product(id, name, price, description, photo, quantity, category, quantitySold, discount);
				arrayProducts.add(product);
			}
		} catch (SQLException e) {
			System.out.println("--> Error en la consulta de cliente");
			e.printStackTrace();
		}
		return arrayProducts;
	}
	
	public static Product getProductById(Integer id) {
		Product product = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Product where id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			while(result.next()) {
				String name = result.getString("name");
				BigDecimal price = result.getBigDecimal("price");
				String description = result.getString("description");
				String photo = result.getString("photo");
				Integer quantity = result.getInt("quantity");
				Integer category = result.getInt("category");
				Integer quantitySold = result.getInt("quantitySold");
				BigDecimal discount = result.getBigDecimal("discount");
				product = new Product(id, name, price, description, photo, quantity, category, quantitySold, discount);
			}
		} catch (SQLException e) {
			System.out.println("--> Error en la consulta de cliente");
			e.printStackTrace();
		}
		return product;
	}
	
	public static Boolean addProduct(Product product) {
		boolean correct = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Product(name, price, description, photo, quantity, category, quantitySold, discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, product.getName());
			preparedStatement.setBigDecimal(2, product.getPrice());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setString(4, product.getPhoto());
			preparedStatement.setInt(5, product.getQuantity());
			preparedStatement.setInt(6, product.getCategory());
			preparedStatement.setInt(7, product.getQuantitySold());
			preparedStatement.setBigDecimal(8, product.getDiscount());
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
	
	public static Boolean deleteProduct(Integer id) {
		boolean correct = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"DELETE FROM Product WHERE id = ?");
			preparedStatement.setInt(1, id);
			Integer result = preparedStatement.executeUpdate();
			if (result > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al eliminar registro");
			e.printStackTrace();
		}
		return correct;
	}
	
	public static Boolean editProduct(Product product) {
		boolean correct = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE Product SET name = ?, price = ?, description = ?, photo = ?, quantity = ?, category = ?, quantitySold = ?, discount = ? WHERE id = ?");
			preparedStatement.setString(1, product.getName());
			preparedStatement.setBigDecimal(2, product.getPrice());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setString(4, product.getPhoto());
			preparedStatement.setInt(5, product.getQuantity());
			preparedStatement.setInt(6, product.getCategory());
			preparedStatement.setInt(7, product.getQuantitySold());
			preparedStatement.setBigDecimal(8, product.getDiscount());
			preparedStatement.setInt(9, product.getId());
			Integer result = preparedStatement.executeUpdate();
			if (result > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar registro");
			e.printStackTrace();
		}
		return correct;
	}
	
	public static Boolean editQuantities(Product product) {
		boolean correct = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE Product SET quantity = ?, quantitySold = ? WHERE id = ?");
			preparedStatement.setInt(1, product.getQuantity());
			preparedStatement.setInt(2, product.getQuantitySold());
			preparedStatement.setInt(3, product.getId());
			Integer result = preparedStatement.executeUpdate();
			if (result > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar registro");
			e.printStackTrace();
		}
		return correct;
	}
}
