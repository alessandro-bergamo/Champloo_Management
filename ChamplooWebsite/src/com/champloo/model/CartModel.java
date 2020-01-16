package com.champloo.model;

import java.sql.SQLException;

import java.util.HashMap;

import com.champloo.bean.CartBean;
import com.champloo.bean.CartItemBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;

public interface CartModel {
	
	public boolean insertProduct(CartBean cart, ProductDetailsBean productDetails) throws SQLException;
	
	public boolean modifyQuantity(CartItemBean cartItemBean, int quantity) throws SQLException;
	
	public int retrieveNumberOfProducts() throws SQLException;
	
	public HashMap<ProductBean, ProductDetailsBean> retrieveProducts() throws SQLException;
	
	public float retrieveTotal() throws SQLException;
	
	public boolean deleteProduct(CartBean cart, ProductDetailsBean productDetails) throws SQLException;
	
	public boolean clearCart(CartBean cart) throws SQLException;
	
	public boolean storeCart(CartBean cart) throws SQLException;
	
	public CartBean retrieveCart(String username) throws SQLException;
}
