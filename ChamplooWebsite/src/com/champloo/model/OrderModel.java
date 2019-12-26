package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.OrderItemBean;
import com.champloo.bean.ProductBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;



public interface OrderModel
{

    //Method that creates an order
    boolean createOrder(OrderBean newOrder, ArrayList<ProductBean> products_in_order) throws SQLException;

    //Method that modifies an order
    boolean modifyOrder(OrderBean order) throws SQLException;

    //Method that cancels an order
    boolean cancelOrder(OrderBean order) throws SQLException;

    //Method that retrieves an order by his ID
    OrderBean retrieveByID(int id_order) throws SQLException;

    //Method that retrieves orders having a username
    HashSet<OrderBean> retrieveByUserID(int user_id) throws SQLException;

    //Method that retrieves order having a start date and an end date
    HashSet<OrderBean> retrieveByDate(Date start_date, Date end_date);

    //Method that retrieves cancelled orders
    HashSet<OrderBean> retrieveCancelledOrders(int status_order) throws SQLException;

    //Method that retrieves all the orders
    HashSet<OrderBean> retrieveAll() throws SQLException;

    //Method that retrieves all the Order_Items contained in an order.
    HashSet<OrderItemBean> retrieveByOrder(int id_order) throws SQLException;

}
