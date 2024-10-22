package com.champloo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;

import javafx.util.Pair;

public interface ProductModel 
{
	public boolean addProduct(ProductBean newProduct, ProductDetailsBean newProductDetails) throws SQLException;
	
	public Pair<ProductBean, ProductDetailsBean> retrieveProductWithDetails (int id_product, int id_product_details) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveById(int id_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByModel(String model_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByCategory(String type_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByBrand(String brand_product) throws SQLException;

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByFeedbacks() throws SQLException;

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByAverage() throws SQLException;

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByColor(String color_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveBySize(String size_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByStatus(String status_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveAll() throws SQLException;
	
	public ArrayList<Pair<ProductBean, ProductDetailsBean>> createWindow() throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveNewProducts() throws SQLException;
	
	public boolean updateRating(int id_product,int ratingScore) throws SQLException;
	
	public boolean deleteProduct(int id_product) throws SQLException;
	
	public boolean updateProduct(ProductBean newProduct, ProductDetailsBean newProductDetails) throws SQLException;
}