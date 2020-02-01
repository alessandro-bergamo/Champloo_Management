package com.champloo.util;

import java.util.HashMap;
import java.util.Iterator;

import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;

import javafx.util.Pair;

public class ActiveCart {
	
	private HashMap<Pair<ProductBean, ProductDetailsBean>, Integer> cartList;
	
	public ActiveCart()
	{
		cartList = new HashMap<Pair<ProductBean, ProductDetailsBean>, Integer>();
	}
	
	public synchronized void insertProduct (ProductBean newProduct, ProductDetailsBean newProductDetails) 
	{
		boolean isInCart = false;
		Pair<ProductBean, ProductDetailsBean> newProductPair = new Pair<ProductBean, ProductDetailsBean>(newProduct, newProductDetails);
		
		if (cartList.isEmpty())
			cartList.put(newProductPair, 1);
		else
		{
			// hashMap -> entrySet -> Iterator
			Iterator iterator = cartList.entrySet().iterator();
			
			while(iterator.hasNext())
			{
				HashMap.Entry entry = (HashMap.Entry) iterator.next();
				
				Pair<ProductBean, ProductDetailsBean> pairInCart = (Pair)entry.getKey();
				ProductBean productInCart = (ProductBean) pairInCart.getKey();
				ProductDetailsBean productDetailsInCart = (ProductDetailsBean) pairInCart.getValue();
				
				int qntInCart = (int) entry.getKey();
				
				if(productInCart.getId_prod() == newProduct.getId_prod() && productDetailsInCart.getId_prod_details() == newProductDetails.getId_prod_details())
				{
					isInCart = true;
					cartList.put(newProductPair, qntInCart + 1);
				}
				
			}
			
			if(!isInCart)
				cartList.put(newProductPair, 1);
		}
	}
	
	public boolean removeProduct(ProductBean newProduct, ProductDetailsBean newProductDetails)
	{
		return cartList.remove(newProduct, newProductDetails);	
	}
	
	public void modifyQuantity(ProductBean newProduct, ProductDetailsBean newProductDetails)
	{
		
	}
	
	

}
