package domain;

import java.util.*;

public class Client extends User {

	private String name;
	private Gender gender;
	private String city;

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	private Set<Book> books = new HashSet<>();


	public Client(String name, Gender gender, String city) {
		super(UUID.randomUUID().toString(), name, name.toLowerCase().replace(" ", "") + "@example.com");
		this.name = name;
		this.gender = gender;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public String getCity() {
		return city;
	}


	public String getClientGreeting() {
		if (gender != null) {
			return gender.getGreeting() + " " + name;
		} else {
			return name;
		}
	}

	@Override
	public String toString() {
		return getClientGreeting();
	}

	@Override
	public String getRole() {
		return "Client";
	}

}
