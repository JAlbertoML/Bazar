package bazar.pojos;

public class User {
	public static final Integer TYPE_ADMIN = 0;
	public static final Integer TYPE_CLIENT  = 1;	
	
	private String user;
	private String password;
	private Integer type;
	
	public User(String user, String password, Integer type) {
		super();
		this.user = user;
		this.password = password;
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}	
