package com.champloo.bean;

public class CartBean {

	public CartBean(int user, int id_cart) {
		this.user = user;
		this.id_cart = id_cart;
	}

	public CartBean() {

	}

	private int user;
	private int id_cart;
	
	public void setId_cart(int id_cart) {
		this.id_cart = id_cart;
	}
	public void setUser(int user) {
		this.user = user;
	}
		public int getUser() {
		return user;
	}
		public int getId_cart() {
		return id_cart;
	}

}
