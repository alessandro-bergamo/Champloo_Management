package com.champloo.model;

import java.util.HashMap;

import com.champloo.bean.CartBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;

public class CartDAO implements CartModel
{

	@Override
	public boolean insertProduct(ProductBean product, ProductDetailsBean productDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyQuantity(int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int retrieveNumberOfProducts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<ProductBean, ProductDetailsBean> retrieveProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float retrieveTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteProduct(ProductBean productBean, ProductDetailsBean productDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean clearCart(CartBean cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeCart(CartBean cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CartBean retrieveCart(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
