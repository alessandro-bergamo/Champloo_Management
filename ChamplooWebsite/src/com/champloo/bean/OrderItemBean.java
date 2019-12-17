package com.champloo.bean;

public class OrderItemBean
{

    public int getQnt_in_order()
    {
        return qnt_in_order;
    }


    public void setQnt_in_order(int qnt_in_order)
    {
        this.qnt_in_order = qnt_in_order;
    }


    public float getPrice()
    {
        return price;
    }


    public void setPrice(float price)
    {
        this.price = price;
    }


    public ProductBean getProduct()
    {
        return product;
    }


    public void setProduct(ProductBean product)
    {
        this.product = product;
    }


    private int qnt_in_order;
    private float price;
    private ProductBean product;

}
