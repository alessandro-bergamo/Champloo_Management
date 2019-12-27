package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.OrderItemBean;
import com.champloo.storage.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;



public class OrderDAO implements OrderModel
{

    /**
     * Creates an order
     * param newOrder
     * return order_created
     */
    public synchronized boolean createOrder(OrderBean newOrder, ArrayList<ProductBean> products_in_order) throws SQLException
    {
        int order_created = 0;

        ArrayList<OrderItemBean> items_in_order = new ArrayList<OrderItemBean>();

        for(int I=0; I<products_in_order.size(); I++)
        {
            OrderItemBean item_in_order = new OrderItemBean();
            item_in_order.setId_order(newOrder.getId_order());
            item_in_order.setId_order_item(1);      //USELESS
            item_in_order.setId_product(products_in_order.get(I).hashCode());       //CAMBIARE NELL'ID DEL PRODOTTO QUANDO CI SARANNO GETTER AND SETTERS
            item_in_order.setPrice_in_order(products_in_order.get(I).hashCode());   //CAMBIARE NEL PREZZO DEL PRODOTTO
            item_in_order.setQnt_in_order(products_in_order.get(I).hashCode());     //CAMBIARE NELLA QUANTITA' DEL PRODOTTO IN CARRELLO

            items_in_order.add(item_in_order);
        }


        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            query = "INSERT INTO order_item (Order_number, Product_details, payed_price, qnt_in_order) VALUES (?, ?, ?, ?)";

            try {
                for(int I=0; I<items_in_order.size(); I++)
                {
                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setInt(1, items_in_order.get(I).getId_order());
                    preparedStatement.setInt(2, items_in_order.get(I).getId_product());
                    preparedStatement.setFloat(3, items_in_order.get(I).getPrice_in_order());
                    preparedStatement.setInt(4, items_in_order.get(I).getQnt_in_order());

                    preparedStatement.executeUpdate();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }


            query = "INSERT INTO orders (Registred_User, shipping_address, payment_method, order_owner, total_price, creation_date, delivery_date, status_order) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, newOrder.getRegistred_User());
                preparedStatement.setString(2, newOrder.getAddress());
                preparedStatement.setString(3, newOrder.getPayment_method());
                preparedStatement.setString(4, newOrder.getOrder_owner());
                preparedStatement.setFloat(5, newOrder.getTotal_price());
                preparedStatement.setDate(6, newOrder.getCreation_date());
                preparedStatement.setDate(7, newOrder.getDelivery_date());
                preparedStatement.setInt(8, newOrder.getStatus_order());

                order_created = preparedStatement.executeUpdate();
            } catch (Exception e3) {
                e3.printStackTrace();
            }


            //SCRIVERE LE DUE QUERY CHE UPDATANO LA QUANTITA DEL PRODOTTO NEL DATABASE

        } finally {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } finally {
            if (connection != null)
                connection.close();
        }
    }


        return order_created != 0;
    }


    /**
     * Modifies an order
     * param order
     * return order_modified
     */
    public boolean modifyOrder(OrderBean order) throws SQLException
    {
       int order_modified = 0;

       query = "UPDATE orders SET shipping_address = '"+order.getAddress()+"', payment_method = '"+order.getPayment_method()+"', delivery_date = '"+order.getDelivery_date()+"', status_order = '"+order.getStatus_order()+"' WHERE id_order = '"+order.getId_order()+"'";

       try {
           statement = connection.createStatement();
           order_modified = statement.executeUpdate(query);
       } finally {
           try {
               if (statement != null)
                   statement.close();
           } finally {
               if (connection != null)
                   connection.close();
           }
       }

       return order_modified != 0;
    }


    /**
     * Cancels an order
     * param order
     * return order_canceled
     */
    public boolean cancelOrder(OrderBean order) throws SQLException
    {
        int order_canceled = 0;

        //SETTARE LO STATUS AD ANNULLATO (3 E' COME ESEMPIO)
        query = "UPDATE orders SET status_order = '3' WHERE id_order = '"+order.getId_order()+"'";

        try {
            statement = connection.createStatement();
            order_canceled = statement.executeUpdate(query);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return order_canceled != 0;
    }


    /**
     * Retrieves the order by his ID
     * param order_id
     * return order
     */
    public OrderBean retrieveByID(int order_id) throws SQLException
    {
        OrderBean order = new OrderBean();

        query = "SELECT * FROM orders WHERE id_order = '"+order_id+"'";

        try {
            statement = connection.createStatement();
            results = statement.executeQuery(query);

            while(results.next())
            {
                order.setId_order(results.getInt(1));
                order.setRegistred_User(results.getInt(2));
                order.setAddress(results.getString(3));
                order.setPayment_method(results.getString(4));
                order.setOrder_owner(results.getString(5));
                order.setCreation_date(results.getDate(6));
                order.setDelivery_date(results.getDate(7));
                order.setStatus_order(results.getInt(8));
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return order;
    }


    /**
     * Retrieves all the orders with a specific username
     * param username
     * return orders
     */
    public ArrayList<OrderBean> retrieveByUserID(int user_id) throws SQLException
    {
        ArrayList<OrderBean> orders = new ArrayList<OrderBean>();

        if(user_id==0)
            query = "SELECT * FROM orders";
        else
            query = "SELECT * FROM orders WHERE Registred_User = '"+user_id+"'";

        try {
            statement = connection.createStatement();
            results = statement.executeQuery(query);

            while(results.next())
            {
                OrderBean order = new OrderBean();

                order.setId_order(results.getInt(1));
                order.setRegistred_User(results.getInt(2));
                order.setAddress(results.getString(3));
                order.setPayment_method(results.getString(4));
                order.setOrder_owner(results.getString(5));
                order.setCreation_date(results.getDate(6));
                order.setDelivery_date(results.getDate(7));
                order.setStatus_order(results.getInt(8));

                orders.add(order);
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return orders;
    }


    /**
     * Retrieves all the orders beyond this date, date included
     * param date
     * return orders
     */
    public HashSet<OrderBean> retrieveByDate(Date start_date, Date end_date) throws SQLException
    {
        HashSet<OrderBean> orders = new HashSet<OrderBean>();

        if(start_date == null && end_date != null)
            query="SELECT * FROM orders WHERE creation_date <= '"+end_date+"'";     //tutti gli ordini effettuati prima di "end"
        else if(start_date != null && end_date == null)
            query="SELECT * FROM orders WHERE creation_date >= '"+start_date+"'";       //tutti gli ordini effettuati dopo di "start"
        else if(start_date != null && end_date != null)
            query="SELECT * FROM orders WHERE creation_date >= '"+start_date+"' AND order_date <= '"+end_date+"'";      //tutti gli ordini effettuati compresi tra "start" ed "end"
        else if(start_date == null && end_date == null)
            query="SELECT * FROM orders ";      //tutti gli ordini

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        statement = connection.createStatement();
        results = statement.executeQuery(query);

        try {
            while(results.next())
            {
                OrderBean order = new OrderBean();

                order.setId_order(results.getInt(1));
                order.setRegistred_User(results.getInt(2));
                order.setAddress(results.getString(3));
                order.setPayment_method(results.getString(4));
                order.setOrder_owner(results.getString(5));
                order.setCreation_date(results.getDate(6));
                order.setDelivery_date(results.getDate(7));
                order.setStatus_order(results.getInt(8));

                orders.add(order);
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return orders;
    }


    /**
     * Retrieves all the canceled orders
     * param status_order
     * return canceled_orders
     */
    public HashSet<OrderBean> retrieveCancelledOrders(int status_order) throws SQLException
    {
        HashSet<OrderBean> orders = new HashSet<OrderBean>();

        query = "SELECT * FROM orders WHERE status_order = '"+status_order+"'";

        try {
            statement = connection.createStatement();
            results = statement.executeQuery(query);

            while(results.next())
            {
                OrderBean order = new OrderBean();

                order.setId_order(results.getInt(1));
                order.setRegistred_User(results.getInt(2));
                order.setAddress(results.getString(3));
                order.setPayment_method(results.getString(4));
                order.setOrder_owner(results.getString(5));
                order.setCreation_date(results.getDate(6));
                order.setDelivery_date(results.getDate(7));
                order.setStatus_order(results.getInt(8));

                orders.add(order);
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return orders;
    }


    /**
     * Retrieves all the orders
     * return orders
     */
    public HashSet<OrderBean> retrieveAll() throws SQLException
    {
        HashSet<OrderBean> orders = new HashSet<OrderBean>();

        query = "SELECT * FROM orders";

        try {
            statement = connection.createStatement();
            results = statement.executeQuery(query);

            while(results.next())
            {
                OrderBean order = new OrderBean();

                order.setId_order(results.getInt(1));
                order.setRegistred_User(results.getInt(2));
                order.setAddress(results.getString(3));
                order.setPayment_method(results.getString(4));
                order.setOrder_owner(results.getString(5));
                order.setCreation_date(results.getDate(6));
                order.setDelivery_date(results.getDate(7));
                order.setStatus_order(results.getInt(8));

                orders.add(order);
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return orders;
    }


    /**
     * Retrieves all the Order Items of a certain order
     * param order_id
     * return order_items
     */
    public ArrayList<OrderItemBean> retrieveByOrder(int order_id) throws SQLException
    {
        ArrayList<OrderItemBean> items_in_order = new ArrayList<OrderItemBean>();

        query = "SELECT * FROM order_item WHERE Order_number = '"+order_id+"'";

        try {
            statement = connection.createStatement();
            results = statement.executeQuery(query);

            while(results.next())
            {
                OrderItemBean item_in_order = new OrderItemBean();

                item_in_order.setId_order_item(results.getInt(1));
                item_in_order.setId_order(results.getInt(2));
                item_in_order.setId_product(results.getInt(3));
                item_in_order.setPrice_in_order(results.getFloat(4));
                item_in_order.setQnt_in_order(results.getInt(5));

                items_in_order.add(item_in_order);
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return items_in_order;
    }



    private ConnectionPool connectionPool = new ConnectionPool();
    private Connection connection;
    String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results;

}
