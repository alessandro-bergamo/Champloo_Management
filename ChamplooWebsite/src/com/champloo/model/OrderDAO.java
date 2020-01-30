package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.OrderItemBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.storage.ConnectionPool;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class OrderDAO implements OrderModel
{    	
	public OrderDAO()
	{
		try {
            connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/champloo_store_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
     * Creates an order
     * @param newOrder
     * @return order_created
     */
    public synchronized boolean createOrder(OrderBean newOrder, HashMap<Pair<ProductBean, ProductDetailsBean>, Integer> products_in_order) throws SQLException
    {
        int order_created = 0, id_order = 0, qntInStock = 0;
        connection = connectionPool.getConnection();

        query = "INSERT INTO orders (Registred_User, shipping_address, payment_method, order_owner, total_price, creation_date, delivery_date, status_order) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, newOrder.getRegistred_User());
            preparedStatement.setString(2, newOrder.getAddress());
            preparedStatement.setString(3, newOrder.getPayment_method());
            preparedStatement.setString(4, newOrder.getOrder_owner());
            preparedStatement.setFloat(5, newOrder.getTotal_price());
            preparedStatement.setDate(6, newOrder.getCreation_date());
            preparedStatement.setDate(7, newOrder.getDelivery_date());
            preparedStatement.setInt(8, newOrder.getStatus_order());

            order_created = preparedStatement.executeUpdate();

            ResultSet autokey = preparedStatement.getGeneratedKeys();
            
            if(autokey.first())
                id_order = autokey.getInt(1);
      
            Iterator iterator = products_in_order.entrySet().iterator();
            int num_products = 1;
            while (iterator.hasNext())
            {
            	query = "INSERT INTO order_item (Order_number, Product_details, payed_price, qnt_in_order) VALUES (?, ?, ?, ?)";
                
                HashMap.Entry entry = (HashMap.Entry) iterator.next();
                Pair<ProductBean, ProductDetailsBean> pairInCart = (Pair) entry.getKey();
                ProductBean product = (ProductBean) pairInCart.getKey();
                ProductDetailsBean productDetails = (ProductDetailsBean) pairInCart.getValue();

                int qntInCart = (int) entry.getValue();

                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setInt(1, id_order);
                preparedStatement.setInt(2, productDetails.getId_prod_details());
                preparedStatement.setFloat(3, product.getPrice()*qntInCart);
                preparedStatement.setInt(4, qntInCart);

                preparedStatement.executeUpdate();
                
                //SCRIVERE LE DUE QUERY CHE UPDATANO LA QUANTITA DEL PRODOTTO NEL DATABASE
                query = "SELECT qnt_stock FROM product_details WHERE id_product_details = '"+productDetails.getId_prod_details()+"'";
                statement = connection.createStatement();
                results = statement.executeQuery(query);
                
                if(results.first())
                	qntInStock = results.getInt(1);
                
                query = "UPDATE product_details SET qnt_stock = '"+(qntInStock - qntInCart)+"' WHERE id_product_details = '"+productDetails.getId_prod_details()+"' ";
                
                statement.executeUpdate(query);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            finally
            {
                connectionPool.releaseConnection(connection);
            }
        }

        return order_created != 0;
    }


    /**
     * Modifies an order
     * @param order
     * @return order_modified
     */
    public boolean modifyOrder(OrderBean order) throws SQLException
    {
       int order_modified = 0;
       connection = connectionPool.getConnection();

       query = "UPDATE orders SET shipping_address = '"+order.getAddress()+"', payment_method = '"+order.getPayment_method()+"', delivery_date = '"+order.getDelivery_date()+"', status_order = '"+order.getStatus_order()+"' WHERE id_order = '"+order.getId_order()+"'";

       try {
           statement = connection.createStatement();
           order_modified = statement.executeUpdate(query);
       } finally {
           try {
               if (statement != null)
                   statement.close();
           } finally {
        	   connectionPool.releaseConnection(connection);
           }
       }

       return order_modified != 0;
    }


    /**
     * Cancels an order
     * @param order
     * @return order_canceled
     */
    public boolean cancelOrder(OrderBean order) throws SQLException
    {
        int order_canceled = 0;
        connection = connectionPool.getConnection();

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return order_canceled != 0;
    }


    /**
     * Retrieves the order by his ID
     * @param order_id
     * @return order
     */
    public OrderBean retrieveByID(int order_id) throws SQLException
    {
        OrderBean order = new OrderBean();
        connection = connectionPool.getConnection();

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return order;
    }


    /**
     * Retrieves all the orders with a specific username
     * @param user_id
     * @return orders
     */
    public ArrayList<OrderBean> retrieveByUserID(int user_id) throws SQLException
    {
        ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
        connection = connectionPool.getConnection();

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return orders;
    }


    /**
     * Retrieves all the orders beyond this date, date included
     * @param start_date, end_date
     * @return orders
     */
    public HashSet<OrderBean> retrieveByDate(Date start_date, Date end_date) throws SQLException
    {
        HashSet<OrderBean> orders = new HashSet<OrderBean>();
        connection = connectionPool.getConnection();

        if(start_date == null && end_date != null)
            query="SELECT * FROM orders WHERE creation_date <= '"+end_date+"'";     //tutti gli ordini effettuati prima di "end"
        else if(start_date != null && end_date == null)
            query="SELECT * FROM orders WHERE creation_date >= '"+start_date+"'";       //tutti gli ordini effettuati dopo di "start"
        else if(start_date != null && end_date != null)
            query="SELECT * FROM orders WHERE creation_date >= '"+start_date+"' AND order_date <= '"+end_date+"'";      //tutti gli ordini effettuati compresi tra "start" ed "end"
        else if(start_date == null && end_date == null)
            query="SELECT * FROM orders ";      //tutti gli ordini

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return orders;
    }


    /**
     * Retrieves all the canceled orders
     * @param status_order
     * @return canceled_orders
     */
    public HashSet<OrderBean> retrieveCancelledOrders(int status_order) throws SQLException
    {
        HashSet<OrderBean> orders = new HashSet<OrderBean>();
        connection = connectionPool.getConnection();

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return orders;
    }


    /**
     * Retrieves all the orders
     * @param
     * @return orders
     */
    public HashSet<OrderBean> retrieveAll() throws SQLException
    {
        HashSet<OrderBean> orders = new HashSet<OrderBean>();
        connection = connectionPool.getConnection();

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return orders;
    }

    /**
     * Retrieves all the Order Items of a certain order
     * @param order_id
     * @return order_items
     */
    public ArrayList<OrderItemBean> retrieveByOrder(int order_id) throws SQLException
    {
        ArrayList<OrderItemBean> items_in_order = new ArrayList<OrderItemBean>();
        connection = connectionPool.getConnection();

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return items_in_order;
    }

    private static ConnectionPool connectionPool;
    private Connection connection;
    String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results;
}
