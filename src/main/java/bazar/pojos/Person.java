package bazar.pojos;

import java.time.LocalDate;

public class Person {
	private Integer id;
	private String name;
	private String surname;
	private String lastname;
	private LocalDate birthday;
	private Contact contact;
	private Direction direction;
	
	public Person(Integer id, String name, String surname, String lastname, LocalDate birthday, Contact contact,
			Direction direction) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.contact = contact;
		this.direction = direction;
	}
	
	public Person(Integer id, String name, String surname, String lastname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.lastname = lastname;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
