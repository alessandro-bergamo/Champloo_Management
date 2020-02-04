package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.OrderItemBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public interface OrderModel
{

    //Method that creates an order
    boolean createOrder(OrderBean newOrder, HashMap<Pair<ProductBean, ProductDetailsBean>, Integer> products_in_order) throws SQLException;

    //Method that modifies an order
    boolean modifyOrder(int order_id, int status, Date delivery_date) throws SQLException;

    //Method that cancels an order
    boolean cancelOrder(int order_id) throws SQLException;

    //Method that retrieves an order by his ID
    OrderBean retrieveByID(int order_id) throws SQLException;

    //Method that retrieves orders having a username
    LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> retrieveByUserID(int user_id) throws SQLException;

    //Method that retrieves order having a start date and an end date
    HashSet<OrderBean> retrieveByDate(Date start_date, Date end_date) throws Exception;

    //Method that retrieves cancelled orders
    HashSet<OrderBean> retrieveCancelledOrders(int status_order) throws SQLException;

    //Method that retrieves all the orders
    HashMap<OrderBean, ArrayList<OrderItemBean>> retrieveAll() throws SQLException;

    //Method that retrieves all the Order_Items contained in an order.
    LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> retrieveByOrder(int id_order) throws SQLException;

}
