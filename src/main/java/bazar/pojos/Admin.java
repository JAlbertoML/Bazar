package bazar.pojos;

public class Admin extends Person {
	private User user;

	public Admin(Integer id, String name, String surname, String lastname, User user) {
		super(id, name, surname, lastname);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
