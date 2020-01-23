package com.champloo.bean;

import java.util.Date;

public class UserBean {
	
	public static final int NORMAL_USER = 1;
	public static final int USERS_ADMIN = 2;
	public static final int ORDERS_ADMIN = 3;
	public static final int PRODUCTS_ADMIN = 4;
	public static final int BANNED_USER = 99;
	
	private int id;
	private String firstname;
	private String surname;
	private String username;
	private String email;
	private String password;
	private Date registration_date;
	private int type;
	
	public UserBean() {
		
	}
	public UserBean(String firstname, String surname, String username, String email, String password, Date registration_date, int type) {
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.registration_date = registration_date;
		this.type = type;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String toString() {
		return "Name: " + getFirstName() + "\n" + "Surname: " + getSurname() + "\n" + "Username: " + getUsername() + "\n" + "Email: " + getEmail() + "\n" + "Password: " + getPassword() + "\n" + "Registration date: " + getRegistration_date() + "\n" + "Type: " + getType() + "\n";
	}

	public Boolean isEmpty() {
		if(firstname == null && surname == null && username == null && email == null && password == null && registration_date == null) {
			return true;
		}
		return false;
	}

	public Boolean isNotEmpty() {
		if(firstname != null && surname != null && username != null && email != null && password != null && registration_date != null) {
			return true;
		}

		return false;
	}
	
}
