package com.champloo.bean;

import java.sql.Date;


public class OrderBean
{

    public float getTotal_price()
    {
        return total_price;
    }

    public void setTotal_price(float total_price)
    {
        this.total_price = total_price;
    }

    public Date getCreation_date()
    {
        return creation_date;
    }

    public void setCreation_date(Date creation_date)
    {
        this.creation_date = creation_date;
    }

    public Date getDelivery_date()
    {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date)
    {
        this.delivery_date = delivery_date;
    }

    public int getStatus_order() { return status_order; }

    public void setStatus_order(int status_order) { this.status_order = status_order; }

    public int getId_order() { return id_order; }

    public void setId_order(int id_order) { this.id_order = id_order; }

    public String getPayment_method() { return payment_method; }

    public void setPayment_method(String payment_method) { this.payment_method = payment_method; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getOrder_owner() { return order_owner; }

    public void setOrder_owner(String order_owner) { this.order_owner = order_owner; }

    public int getRegistred_User() { return Registred_User; }

    public void setRegistred_User(int registred_User) { Registred_User = registred_User; }



    private int id_order, status_order, Registred_User;
    private float total_price;
    private Date creation_date, delivery_date;
    private String payment_method, address, order_owner;

}
