package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.OrderItemBean;

import java.util.Date;
import java.util.HashSet;

public interface OrderModel
{

    //Method that creates an order
    boolean createOrder(OrderBean newOrder);

    //Method that modifies an order
    boolean modifyOrder(OrderBean order);

    //Method that cancels an order
    boolean cancelOrder(OrderBean order);

    //Method that retrieves an order by his ID
    OrderBean retrieveByID(int id_order);

    //Method that retrieves orders having a username
    HashSet<OrderBean> retrieveByUsername(String username);

    //Method that retrieves order having a date
    HashSet<OrderBean> retrieveByDate(Date date);

    //Method that retrieves cancelled orders
    HashSet<OrderBean> retrieveCancelledOrders(int status_order);

    //Method that retrieves all the orders
    HashSet<OrderBean> retrieveAll();

    //Method that retrieves all the Order_Items contained in an order.
    HashSet<OrderItemBean> retrieveByOrder(int id_order);

}
