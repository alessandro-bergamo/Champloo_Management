package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.OrderItemBean;

import java.util.Date;
import java.util.HashSet;

public interface OrderModel
{

    //Method that creates an order
    public boolean createOrder();

    //Method that modifies an order
    public boolean modifyOrder();

    //Method that cancels an order
    public boolean cancelOrder();

    //Method that retrieves an order by his ID
    public OrderBean retrieveByID(int order_id);

    //Method that retrieves orders having a username
    public HashSet<OrderBean> retrieveByUsername(String username);

    //Method that retrieves order having a date
    public HashSet<OrderBean> retrieveByDate(Date date);

    //Method that retrieves cancelled orders
    public HashSet<OrderBean> retrieveCancelledOrders(int status_order);

    //Method that retrieves all the orders
    public HashSet<OrderBean> retrieveAll();

    //Method that retrieves all the Order Item contained in an order.
    public OrderItemBean retrieveByOrder(int order_id);

}
