package com.champloo.bean;

public class ProductBean {
	
	public static final int NORMAL_PRODUCT = 1;
	public static final int ON_SALE_PRODUCT = 2;
	public static final int SLIDER_PRODUCT = 3;
	public static final int WINDOW_PRODUCT = 4;
	public static final int NEW_PRODUCT = 5;
	
	private int id_prod, status, number_feedback_users, total_rating;
	private float average_rating, price;
	private String name, brand, model, type, description;

	public ProductBean(int id_prod, int status, int number_feedback_users, int total_rating, float average_rating, float price, String name, String brand, String model, String type, String description) {
		this.id_prod = id_prod;
		this.status = status;
		this.number_feedback_users = number_feedback_users;
		this.total_rating = total_rating;
		this.average_rating = average_rating;
		this.price = price;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.description = description;
	}

	public ProductBean(int status, int number_feedback_users, int total_rating, float average_rating, float price, String name, String brand, String model, String type, String description) {
		this.status = status;
		this.number_feedback_users = number_feedback_users;
		this.total_rating = total_rating;
		this.average_rating = average_rating;
		this.price = price;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.description = description;
	}

	public ProductBean() {

	}
	
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNumber_feedback_users() {
		return number_feedback_users;
	}
	public void setNumber_feedback_users(int number_feedback_users) {
		this.number_feedback_users = number_feedback_users;
	}
	public int getTotal_rating() {
		return total_rating;
	}
	public void setTotal_rating(int total_rating) {
		this.total_rating = total_rating;
	}
	public float getAverage_rating() {
		return average_rating;
	}
	public void setAverage_rating(float average_rating) {
		this.average_rating = average_rating;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isEmpty() {
		return status == 0 && number_feedback_users == 0 && total_rating == 0 && average_rating == 0 && price == 0 && name == null && brand == null && model == null && type == null && description == null;
	}
	
}
