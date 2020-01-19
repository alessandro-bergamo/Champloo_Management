package com.champloo.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.champloo.bean.CartBean;
import com.champloo.bean.CartItemBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.bean.UserBean;

public interface CartModel {
	
	public boolean insertProduct(CartBean cart, int id_product_details) throws SQLException;
	
	public boolean modifyQuantity(CartItemBean cartItem, int quantity) throws SQLException;
	
	public int retrieveNumberOfProducts(CartBean cart) throws SQLException;
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveProducts(CartBean cart) throws SQLException;
	
	public float retrieveTotal(CartBean cart) throws SQLException;
	
	public boolean deleteProduct(CartBean cart, int id_product_details) throws SQLException;
	
	public boolean clearCart(CartBean cart) throws SQLException;
	
	public boolean createCart(UserBean user) throws SQLException;
	
	public CartBean retrieveCart(UserBean user) throws SQLException;
	
	public CartItemBean retrieveCartItem (int id_cart_item) throws SQLException;
}
