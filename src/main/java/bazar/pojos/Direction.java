package bazar.pojos;

public class Direction {
	private String street;
	private Integer Number;
	private String suburb;
	private String townhall;
	private String postalCode;
	private String state;
	private String country;
	
	public Direction(String street, Integer number, String suburb, String townhall, String postalCode, String state, String country) {
		super();
		this.street = street;
		Number = number;
		this.suburb = suburb;
		this.townhall = townhall;
		this.postalCode = postalCode;
		this.state = state;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return Number;
	}

	public void setNumber(Integer number) {
		Number = number;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getTownhall() {
		return townhall;
	}

	public void setTownhall(String townhall) {
		this.townhall = townhall;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
