package com.champloo.model;

import java.sql.SQLException;

import java.util.HashMap;

import com.champloo.bean.CartBean;
import com.champloo.bean.CartItemBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.bean.UserBean;

import javafx.util.Pair;

public interface CartModel {
	
	public boolean insertProduct(CartBean cart, int id_product_details) throws SQLException;
	
	public boolean modifyQuantity(int id_cart, int id_product_details, String operation) throws SQLException;
	
	public int retrieveNumberOfProducts(CartBean cart) throws SQLException;
	
	public HashMap<Pair<ProductBean, ProductDetailsBean>, Integer> retrieveProducts(CartBean cart) throws SQLException;
	
	public float retrieveTotal(CartBean cart) throws SQLException;
	
	public boolean deleteProduct(CartBean cart, int id_product_details) throws SQLException;
	
	public boolean clearCart(CartBean cart) throws SQLException;
	
	public boolean createCart(UserBean user) throws SQLException;
	
	public CartBean retrieveCart(UserBean user) throws SQLException;
	
	public CartItemBean retrieveCartItem (int id_cart_item) throws SQLException;
}
