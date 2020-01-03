package com.champloo.model;

import java.util.ArrayList;

import com.champloo.bean.ProductBean;

public interface ProductModel 
{
	public boolean addProduct(ProductBean newProduct);
	
	public ArrayList<ProductBean> retrieveByModel(String model_prod);
	
}
