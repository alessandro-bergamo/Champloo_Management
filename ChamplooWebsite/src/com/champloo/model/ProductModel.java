package com.champloo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;

public interface ProductModel 
{
	public boolean addProduct(ProductBean newProduct, ProductDetailsBean newProductDetails) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByModel(String model_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByCategory(String type_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByBrand(String brand_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByColor(String color_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveBySize(String size_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByStatus(String status_product) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveAll() throws SQLException;
	
	public boolean deleteProduct(ProductBean product) throws SQLException;
	
	public boolean updateProduct(ProductBean newProduct, ProductDetailsBean newProductDetails) throws SQLException;
}