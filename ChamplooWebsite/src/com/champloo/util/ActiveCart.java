package com.champloo.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
			
		Iterator<Entry<Pair<ProductBean, ProductDetailsBean>, Integer>> iterator = cartList.entrySet().iterator();
			
			while(iterator.hasNext())
			{
				Entry<Pair<ProductBean, ProductDetailsBean>, Integer> entry = iterator.next();
				
				Pair<ProductBean, ProductDetailsBean> pairInCart = (Pair<ProductBean, ProductDetailsBean>)entry.getKey();
				ProductBean productInCart = (ProductBean) pairInCart.getKey();
				ProductDetailsBean productDetailsInCart = (ProductDetailsBean) pairInCart.getValue();
				
				int qntInCart = (int) entry.getValue();
				
				if(productInCart.getId_prod() == newProduct.getId_prod() && productDetailsInCart.getId_prod_details() == newProductDetails.getId_prod_details())
				{
					isInCart = true;
					Pair<ProductBean, ProductDetailsBean> newProductPair = new Pair<ProductBean, ProductDetailsBean>(newProduct, newProductDetails);
					entry.setValue(qntInCart + 1);
				}
				
			}
			System.out.println("1° isInCart -> "+isInCart);
		if(!isInCart)
		{
			System.out.println("2° isInCart -> "+isInCart);
			Pair<ProductBean, ProductDetailsBean> newProductPair = new Pair<ProductBean, ProductDetailsBean>(newProduct, newProductDetails);
			cartList.put(newProductPair, 1);
		}
	}
	
	public boolean removeProduct(int id_product, int id_product_details)
	{
		boolean isRemoved = false;
		Iterator<Entry<Pair<ProductBean, ProductDetailsBean>, Integer>> iterator = cartList.entrySet().iterator();
		ProductBean productBean = null;
		ProductDetailsBean productDetailsBean = null;
		while (iterator.hasNext())
		{
			Entry<Pair<ProductBean, ProductDetailsBean>, Integer> entry = iterator.next();
			Pair<ProductBean, ProductDetailsBean> pairInCart = (Pair<ProductBean, ProductDetailsBean>)entry.getKey();
			productBean = pairInCart.getKey();
			productDetailsBean = pairInCart.getValue();
			if(productBean.getId_prod() == id_product && productDetailsBean.getId_prod_details() == id_product_details)
			{
				cartList.remove(pairInCart);
				isRemoved = true;
			}
		}
		return isRemoved;
	}
	
	public void modifyQuantity(int id_product, int id_product_details, String operator)
	{
		Iterator<Entry<Pair<ProductBean, ProductDetailsBean>, Integer>> iterator = cartList.entrySet().iterator();
		
		while(iterator.hasNext())
		{
			Entry<Pair<ProductBean, ProductDetailsBean>, Integer> entry = iterator.next();
			
			Pair<ProductBean, ProductDetailsBean> pairInCart = (Pair<ProductBean, ProductDetailsBean>)entry.getKey();
			ProductBean productInCart = (ProductBean) pairInCart.getKey();
			ProductDetailsBean productDetailsInCart = (ProductDetailsBean) pairInCart.getValue();
			
			int qntInCart = (int) entry.getValue();
			
			if(productInCart.getId_prod() == id_product && productDetailsInCart.getId_prod_details() == id_product_details)
			{
				if(operator.equals("-"))
					entry.setValue(qntInCart - 1);
				else if (operator.equals("+"))
					entry.setValue(qntInCart + 1);		
			}	
		}
	}
	
	public Iterator<Entry<Pair<ProductBean, ProductDetailsBean>, Integer>> getCartIterator()
	{
		Iterator<Entry<Pair<ProductBean, ProductDetailsBean>, Integer>> iterator = cartList.entrySet().iterator();
		return iterator;
	}
	
	public HashMap<Pair<ProductBean, ProductDetailsBean>, Integer> getHaspMap()
	{
		return cartList;
	}
	
	public void clearCart()
	{
		System.out.println("AcitveCart -> cleatrCart line 112");
		cartList.clear();
		cartList = new HashMap<Pair<ProductBean, ProductDetailsBean>, Integer>();
	}
	
	public void storeActiveCartInDb()
	{
		
	}

}
