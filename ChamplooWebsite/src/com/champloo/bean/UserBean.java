package com.champloo.bean;

import java.sql.Date;

public class UserBean {
	private int id;
	private String name;
	private String username;
	private String email;
	private String password;
	private Date registration_date;
	private int type;
	
	public UserBean(String name, String username, String email, String password, Date registration_date) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.registration_date = registration_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
}
