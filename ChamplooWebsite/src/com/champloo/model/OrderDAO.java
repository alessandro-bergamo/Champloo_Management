package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.OrderItemBean;

import java.util.Date;
import java.util.HashSet;

public class OrderDAO implements OrderModel
{

    /**
     * Creates an order
     * @param newOrder
     * @return order_created
     */
    @Override
    public boolean createOrder(OrderBean newOrder)
    {
        return false;
    }


    /**
     * Modifies an order
     * @param order
     * @return order_modified
     */
    @Override
    public boolean modifyOrder(OrderBean order)
    {
        return false;
    }


    /**
     * Cancels an order
     * @param order
     * @return order_canceled
     */
    @Override
    public boolean cancelOrder(OrderBean order)
    {
        return false;
    }


    /**
     * Retrieves the order by his ID
     * @param order_id
     * @return order
     */
    @Override
    public OrderBean retrieveByID(int order_id)
    {
        return null;
    }


    /**
     * Retrieves all the orders with a specific username
     * @param username
     * @return orders
     */
    @Override
    public HashSet<OrderBean> retrieveByUsername(String username)
    {
        return null;
    }


    /**
     * Retrieves all the orders beyond this date, date included
     * @param date
     * @return orders
     */
    @Override
    public HashSet<OrderBean> retrieveByDate(Date date)
    {
        return null;
    }


    /**
     * Retrieves all the canceled orders
     * @param status_order
     * @return canceled_orders
     */
    @Override
    public HashSet<OrderBean> retrieveCancelledOrders(int status_order)
    {
        return null;
    }


    /**
     * Retrieves all the orders
     * @return orders
     */
    @Override
    public HashSet<OrderBean> retrieveAll()
    {
        return null;
    }


    /**
     * Retrieves all the Order Items of a certain order
     * @param order_id
     * @return order_items
     */
    @Override
    public HashSet<OrderItemBean> retrieveByOrder(int order_id)
    {
        return null;
    }

}
