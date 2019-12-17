package com.champloo.bean;

import java.util.Date;
import java.util.HashSet;

public class OrderBean
{

    public AddressBean getOrder_address()
    {
        return order_address;
    }

    public void setOrder_address(AddressBean order_address)
    {
        this.order_address = order_address;
    }

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

    public int getOrder_id() { return order_id; }

    public void setOrder_id(int order_id) { this.order_id = order_id; }

    public int getOrder_status() { return order_status; }

    public void setOrder_status(int order_status) { this.order_status = order_status; }

    public PaymentMethodBean getOrder_payment_method() { return order_payment_method; }

    public void setOrder_payment_method(PaymentMethodBean order_payment_method) { this.order_payment_method = order_payment_method; }

    public HashSet<OrderItemBean> getOrder_items() { return order_items; }

    public void setOrder_items(HashSet<OrderItemBean> order_items) { this.order_items = order_items; }

    private int order_id;
    private int order_status;
    private float total_price;
    private Date creation_date, delivery_date;
    private PaymentMethodBean order_payment_method;
    private AddressBean order_address;
    private HashSet<OrderItemBean> order_items;

}
