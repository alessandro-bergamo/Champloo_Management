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

    public int getId_order() { return id_order; }

    public void setId_order(int id_order) { this.id_order = id_order; }

    public float getPrice_in_order() { return price_in_order; }

    public void setPrice_in_order(float price_in_order) { this.price_in_order = price_in_order; }

    public int getId_product() { return id_product; }

    public void setId_product(int id_product) { this.id_product = id_product; }



    private int qnt_in_order, id_order, id_product;
    private float price_in_order;

}
