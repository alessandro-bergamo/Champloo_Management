package com.champloo.bean;

public class ProductDetailsBean {
	
	private int id_prod_details, product, discount_percent, qnt_stock;	
	private String color, size, img_path_folder;

	public ProductDetailsBean(int id_prod_details, int product, int discount_percent, int qnt_stock, String color, String size, String img_path_folder) {
		this.id_prod_details = id_prod_details;
		this.product = product;
		this.discount_percent = discount_percent;
		this.qnt_stock = qnt_stock;
		this.color = color;
		this.size = size;
		this.img_path_folder = img_path_folder;
	}

	public ProductDetailsBean(int product, int discount_percent, int qnt_stock, String color, String size, String img_path_folder) {
		this.product = product;
		this.discount_percent = discount_percent;
		this.qnt_stock = qnt_stock;
		this.color = color;
		this.size = size;
		this.img_path_folder = img_path_folder;
	}

	public ProductDetailsBean() {

	}

	public int getId_prod_details() {
		return id_prod_details;
	}
	public void setId_prod_details(int id_prod_details) {
		this.id_prod_details = id_prod_details;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getDiscount_percent() {
		return discount_percent;
	}
	public void setDiscount_percent(int discount_percent) {
		this.discount_percent = discount_percent;
	}
	public int getQnt_stock() {
		return qnt_stock;
	}
	public void setQnt_stock(int qnt_stock) {
		this.qnt_stock = qnt_stock;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getImg_path_folder() {
		return img_path_folder;
	}
	public void setImg_path_folder(String img_path_folder) {
		this.img_path_folder = img_path_folder;
	}

	public boolean isEmpty() {
		return product == 0 && discount_percent == 0 && qnt_stock == 0 && color == null && size == null && size == null && img_path_folder == null;
	}
}
