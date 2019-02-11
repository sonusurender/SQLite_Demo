package com.sqllite_demo;

public class Data_Model {

	String name, email, address;

	// Setter for database
	public Data_Model(String name, String email, String address) {
		this.name = name;
		this.email = email;
		this.address = address;
	}

	// Getter
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

}
