package com.champloo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;

public interface ProductModel 
{
	public boolean addProduct(ProductBean newProduct, ProductDetailsBean newProductDetails) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByModel(String model_prod) throws SQLException;
	
	public ArrayList<ProductBean> retrieveByCategory(String type_prod) throws SQLException;
	
	public ArrayList<ProductBean> retrieveByBrand(String brand_prod) throws SQLException;
	
	public ArrayList<ProductBean> retrieveByColor(String color_prod) throws SQLException;
	
	public ArrayList<ProductBean> retrieveBySize(String size_prod) throws SQLException;
	
	public ArrayList<ProductBean> retrieveByStatus(String status_prod) throws SQLException;
	
	public ArrayList<ProductBean> retrieveAll() throws SQLException;
	
	public boolean deleteProduct(ProductBean product) throws SQLException;
	
	public boolean updateProduct(ProductBean product) throws SQLException;
}