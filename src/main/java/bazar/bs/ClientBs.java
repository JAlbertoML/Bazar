package bazar.bs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import bazar.dao.ClientDao;
import bazar.pojos.Client;
import bazar.pojos.Contact;
import bazar.pojos.Direction;
import bazar.pojos.ProductInCart;
import bazar.pojos.User;

public class ClientBs {
	public static Client getClientByUsername(String username) {
		return ClientDao.getClientByUsername(username);
	}

	public static Client getClientByEmail(String email) {
		return ClientDao.getClientByEmail(email);
	}

	public static Client getClientByPhone(String phone) {
		return ClientDao.getClientByPhone(phone);
	}

	public static Boolean addClient(Map<String, String[]> parameterMap) {
		Boolean correct = false;
		String name = parameterMap.get("nameTxt")[0];
		String surname = parameterMap.get("surnameTxt")[0];
		String lastname = parameterMap.get("lastnameTxt")[0];
		String phone = parameterMap.get("phoneTxt")[0];
		String email = parameterMap.get("emailTxt")[0];
		String street = parameterMap.get("streetTxt")[0];
		String number = parameterMap.get("numberTxt")[0];
		String suburb = parameterMap.get("suburbTxt")[0];
		String townhall = parameterMap.get("townhallTxt")[0];
		String postalCode = parameterMap.get("postalCodeTxt")[0];
		String state = parameterMap.get("stateTxt")[0];
		String country = parameterMap.get("countryTxt")[0];
		String username = parameterMap.get("usernameTxt")[0];
		String password = MD5.getMd5(parameterMap.get("passwordTxt")[0]);
		String[] dateParts = parameterMap.get("birthdayDate")[0].split("-");
		LocalDate birthday = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
		Contact contact = new Contact(email, phone);
		Direction direction = new Direction(street, Integer.parseInt(number), suburb, townhall, postalCode, state, country);
		User user = new User(username, password, User.TYPE_CLIENT);
		Client client = new Client(0, name, surname, lastname, birthday, contact, direction, null, null, user);
		if(ClientDao.addClient(client)) {
			correct = true;
		}
		return correct;
	}
	
	public static Boolean editClient(Map<String, String[]> parameterMap, Client client) {
		Boolean correct = false;
		String name = parameterMap.get("nameTxt")[0];
		String surname = parameterMap.get("surnameTxt")[0];
		String lastname = parameterMap.get("lastnameTxt")[0];
		String phone = parameterMap.get("phoneTxt")[0];
		String email = parameterMap.get("emailTxt")[0];
		String street = parameterMap.get("streetTxt")[0];
		String number = parameterMap.get("numberTxt")[0];
		String suburb = parameterMap.get("suburbTxt")[0];
		String townhall = parameterMap.get("townhallTxt")[0];
		String postalCode = parameterMap.get("postalCodeTxt")[0];
		String state = parameterMap.get("stateTxt")[0];
		String country = parameterMap.get("countryTxt")[0];
		String username;
		if(parameterMap.get("usernameTxt")[0].equals(""))
			username = client.getUser().getUser();
		else
			username = parameterMap.get("usernameTxt")[0];
		String password;
		if(parameterMap.get("passwordTxt")[0].equals(""))
			password = client.getUser().getPassword();
		else
			password = MD5.getMd5(parameterMap.get("passwordTxt")[0]);
		String[] dateParts = parameterMap.get("birthdayDate")[0].split("-");
		LocalDate birthday = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
		Contact contact = new Contact(email, phone);
		Direction direction = new Direction(street, Integer.parseInt(number), suburb, townhall, postalCode, state, country);
		User user = new User(username, password, User.TYPE_CLIENT);
		client = new Client(client.getId(), name, surname, lastname, birthday, contact, direction, null, null, user);
		if(ClientDao.editClient(client)) {
			correct = true;
		}
		return correct;
	}
	
	public static Boolean editPhoto(Client client) {
		return ClientDao.editPhoto(client);
	}
	
	public static Boolean deleteClient(Client client) {
		return ClientDao.deleteClient(client);
	}
	
	public static Boolean editCart(ArrayList<ProductInCart> cart, String username) {
		return ClientDao.editCart(cart, username);
	}
}
