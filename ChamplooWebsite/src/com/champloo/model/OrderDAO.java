package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.OrderItemBean;

import java.util.Date;
import java.util.HashSet;

public class OrderDAO implements OrderModel
{

    @Override
    public boolean createOrder()
    {
        return false;
    }

    @Override
    public boolean modifyOrder()
    {
        return false;
    }

    @Override
    public boolean cancelOrder()
    {
        return false;
    }

    @Override
    public OrderBean retrieveByID(int order_id)
    {
        return null;
    }

    @Override
    public HashSet<OrderBean> retrieveByUsername(String username)
    {
        return null;
    }

    @Override
    public HashSet<OrderBean> retrieveByDate(Date date)
    {
        return null;
    }

    @Override
    public HashSet<OrderBean> retrieveCancelledOrders(int status_order)
    {
        return null;
    }

    @Override
    public HashSet<OrderBean> retrieveAll()
    {
        return null;
    }

    @Override
    public OrderItemBean retrieveByOrder(int order_id)
    {
        return null;
    }

}
