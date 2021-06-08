package bazar.pojos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client extends Person{
	
	private ArrayList<ProductInCart> cart;
	private String photo;
	private User user;
	
	public Client(Integer id, String name, String surname, String lastname, LocalDate birthday, Contact contact,
			Direction direction, ArrayList<ProductInCart> cart, String photo, User user) {
		super(id, name, surname, lastname, birthday, contact, direction);
		this.cart = cart;
		this.photo = photo;
		this.user = user;
	}
	
	public ArrayList<ProductInCart> getCart() {
		return cart;
	}
	public void setCart(ArrayList<ProductInCart> cart) {
		this.cart = cart;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
