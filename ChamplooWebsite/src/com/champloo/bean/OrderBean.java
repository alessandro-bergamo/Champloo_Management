package com.champloo.bean;

import java.util.Date;
import java.util.HashSet;

public class OrderBean
{

    public AddressBean getOrder_address()
    {
        return address;
    }

    public void setOrder_address(AddressBean address)
    {
        this.address = address;
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

    public int getOrder_id() { return id_order; }

    public void setOrder_id(int id_order) { this.id_order = id_order; }

    public int getStatus_order() { return status_order; }

    public void setStatus_order(int status_order) { this.status_order = status_order; }

    public PaymentMethodBean getPayment_method() { return payment_method; }

    public void setPayment_method(PaymentMethodBean payment_method) { this.payment_method = payment_method; }

    public HashSet<OrderItemBean> getItems_order() { return items_order; }

    public void setItems_order(HashSet<OrderItemBean> items_order) { this.items_order = items_order; }

    private int id_order;
    private int status_order;
    private float total_price;
    private Date creation_date, delivery_date;
    private PaymentMethodBean payment_method;
    private AddressBean address;
    private HashSet<OrderItemBean> items_order;

}
