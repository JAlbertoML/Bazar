package bazar.dao;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import bazar.pojos.Client;
import bazar.pojos.Contact;
import bazar.pojos.Direction;
import bazar.pojos.ProductInCart;
import bazar.pojos.User;

public class ClientDao {
	public static Client getClientByUsername(String username) {
		Client client = null;
		Gson gson = new Gson();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Client where username = ?");
			preparedStatement.setString(1, username);
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			if(result.next()) {
				Type productListType = new TypeToken<ArrayList<ProductInCart>>() {}.getType();
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String surname = result.getString("surname");
				String lastname = result.getString("lastname");
				LocalDate birthday = result.getDate("birthday").toLocalDate();
				String email = result.getString("email");
				String phone = result.getString("phone");
				Contact contact = new Contact(email, phone);
				String street = result.getString("street");
				Integer number = result.getInt("number");
				String suburb = result.getString("suburb");
				String townhall = result.getString("townhall");
				String postalCode = result.getString("postalcode");
				String state = result.getString("state");
				String country = result.getString("country");
				Direction direction = new Direction(street, number, suburb, townhall, postalCode, state, country);
				ArrayList<ProductInCart> cart = gson.fromJson(result.getString("cart"), productListType);
				String photo = result.getString("photo");
				if(photo == null || photo.equals("")) {
					photo = "images/logo.png";
				}
				String password = result.getString("password");
				User user = new User(username, password, User.TYPE_CLIENT);
				client = new Client(id, name, surname, lastname, birthday, contact, direction, cart, photo, user);
			}
		} catch (SQLException e) {
			System.out.println("--> Error en la consulta de cliente");
			e.printStackTrace();
		}
		return client;
	}
	
	public static Client getClientByEmail(String email) {
		Client client = null;
		Gson gson = new Gson();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Client where email = ?");
			preparedStatement.setString(1, email);
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			if(result.next()) {
				Type productListType = new TypeToken<ArrayList<ProductInCart>>() {}.getType();
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String surname = result.getString("surname");
				String lastname = result.getString("lastname");
				LocalDate birthday = result.getDate("birthday").toLocalDate();
				String phone = result.getString("phone");
				Contact contact = new Contact(email, phone);
				String street = result.getString("street");
				Integer number = result.getInt("number");
				String suburb = result.getString("suburb");
				String townhall = result.getString("townhall");
				String postalCode = result.getString("postalcode");
				String state = result.getString("state");
				String country = result.getString("country");
				Direction direction = new Direction(street, number, suburb, townhall, postalCode, state, country);
				ArrayList<ProductInCart> cart = gson.fromJson(result.getString("cart"), productListType);
				String photo = result.getString("photo");
				if(photo == null || photo.equals("")) {
					photo = "images/logo.png";
				}
				String username = result.getString("username");
				String password = result.getString("password");
				User user = new User(username, password, User.TYPE_CLIENT);
				client = new Client(id, name, surname, lastname, birthday, contact, direction, cart, photo, user);
			}
		} catch (SQLException e) {
			System.out.println("--> Error en la consulta de cliente");
			e.printStackTrace();
		}
		return client;
	}
	
	public static Client getClientByPhone(String phone) {
		Client client = null;
		Gson gson = new Gson();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Client where phone = ?");
			preparedStatement.setString(1, phone);
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			if(result.next()) {
				Type productListType = new TypeToken<ArrayList<ProductInCart>>() {}.getType();
				Integer id = result.getInt("id");
				String name = result.getString("name");
				String surname = result.getString("surname");
				String lastname = result.getString("lastname");
				LocalDate birthday = result.getDate("birthday").toLocalDate();
				String email = result.getString("email");
				Contact contact = new Contact(email, phone);
				String street = result.getString("street");
				Integer number = result.getInt("number");
				String suburb = result.getString("suburb");
				String townhall = result.getString("townhall");
				String postalCode = result.getString("postalcode");
				String state = result.getString("state");
				String country = result.getString("country");
				Direction direction = new Direction(street, number, suburb, townhall, postalCode, state, country);
				ArrayList<ProductInCart> cart = gson.fromJson(result.getString("cart"), productListType);
				String photo = result.getString("photo");
				if(photo == null || photo.equals("")) {
					photo = "images/logo.png";
				}
				String username = result.getString("username");
				String password = result.getString("password");
				User user = new User(username, password, User.TYPE_CLIENT);
				client = new Client(id, name, surname, lastname, birthday, contact, direction, cart, photo, user);
			}
		} catch (SQLException e) {
			System.out.println("--> Error en la consulta de cliente");
			e.printStackTrace();
		}
		return client;
	}
	
	public static Boolean addClient(Client client) {
		boolean correct = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Client(name, surname, lastname, birthday, email, phone, street, number, suburb, townhall, state, country, username, password, postalcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurname());
			preparedStatement.setString(3, client.getLastname());
			preparedStatement.setDate(4, java.sql.Date.valueOf(client.getBirthday()));
			preparedStatement.setString(5, client.getContact().getEmail());
			preparedStatement.setString(6, client.getContact().getPhone());
			preparedStatement.setString(7, client.getDirection().getStreet());
			preparedStatement.setInt(8, client.getDirection().getNumber());
			preparedStatement.setString(9, client.getDirection().getSuburb());
			preparedStatement.setString(10, client.getDirection().getTownhall());
			preparedStatement.setString(11, client.getDirection().getState());
			preparedStatement.setString(12, client.getDirection().getCountry());
			preparedStatement.setString(13, client.getUser().getUser());
			preparedStatement.setString(14, client.getUser().getPassword());
			preparedStatement.setString(15, client.getDirection().getPostalCode());
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
	
	public static Boolean editClient(Client client) {
		boolean correct = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE Client SET name = ?, surname = ?, lastname = ?, birthday = ?, email = ?, phone = ?, street = ?, number = ?, suburb = ?, townhall = ?, state = ?, country = ?, username = ?, password = ?, postalcode = ? WHERE id = ?");
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurname());
			preparedStatement.setString(3, client.getLastname());
			preparedStatement.setDate(4, java.sql.Date.valueOf(client.getBirthday()));
			preparedStatement.setString(5, client.getContact().getEmail());
			preparedStatement.setString(6, client.getContact().getPhone());
			preparedStatement.setString(7, client.getDirection().getStreet());
			preparedStatement.setInt(8, client.getDirection().getNumber());
			preparedStatement.setString(9, client.getDirection().getSuburb());
			preparedStatement.setString(10, client.getDirection().getTownhall());
			preparedStatement.setString(11, client.getDirection().getState());
			preparedStatement.setString(12, client.getDirection().getCountry());
			preparedStatement.setString(13, client.getUser().getUser());
			preparedStatement.setString(14, client.getUser().getPassword());
			preparedStatement.setString(15, client.getDirection().getPostalCode());
			preparedStatement.setInt(16, client.getId());
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
	
	public static Boolean editPhoto(Client client) {
		boolean correct = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE Client SET photo = ? WHERE id = ?");
			preparedStatement.setString(1, client.getPhoto());
			preparedStatement.setInt(2, client.getId());
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
	
	public static Boolean deleteClient(Client client) {
		boolean correct = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"DELETE FROM Client WHERE id = ?");
			preparedStatement.setInt(1, client.getId());
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
	
	public static Boolean editCart(ArrayList<ProductInCart> cart, String username) {
		boolean correct = false;
		Gson gson = new Gson();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE Client SET cart = ? WHERE username = ?");
			String cartJson = gson.toJson(cart);
			preparedStatement.setString(1, cartJson);
			preparedStatement.setString(2, username);
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
