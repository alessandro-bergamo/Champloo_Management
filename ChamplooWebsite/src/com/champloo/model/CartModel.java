package com.champloo.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.champloo.bean.CartBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;

public interface CartModel {
	
	public boolean insertProduct(ProductBean product, ProductDetailsBean productDetails);
	
	public boolean modifyQuantity(int quantity);
	
	public int retrieveNumberOfProducts();
	
	public HashMap<ProductBean, ProductDetailsBean> retrieveProducts();
	
	public float retrieveTotal();
	
	public boolean deleteProduct(ProductBean productBean, ProductDetailsBean productDetails);
	
	public boolean clearCart(CartBean cart);
	
	public boolean storeCart(CartBean cart);
	
	public CartBean retrieveCart(String username);
}
