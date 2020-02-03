package com.champloo.model;

import com.champloo.bean.OrderBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.OrderItemBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.storage.ConnectionPool;
import javafx.util.Pair;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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
     * @param status, delivery_date
     * @return order_modified
     */
    public boolean modifyOrder(int order_id, int status, Date delivery_date) throws SQLException
    {
       int order_modified = 0;

       connection = connectionPool.getConnection();

       query = "UPDATE orders SET delivery_date = '"+delivery_date+"', status_order = '"+status+"' WHERE id_order = '"+order_id+"'";

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
     * @param order_id
     * @return order_canceled
     */
    public boolean cancelOrder(int order_id) throws SQLException
    {
        int order_canceled = 0;
        connection = connectionPool.getConnection();

        query = "UPDATE orders SET status_order = '5' WHERE id_order = '"+order_id+"'";

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
     * @return all the orders of a specific user
     */
    public LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> retrieveByUserID(int user_id) throws SQLException
    {
        LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap <OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();
        OrderBean order = null;
        ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>> products_in_order;
        OrderItemBean order_item = null;
        ProductDetailsBean productDetails = null;
        ProductBean product = null;

        connection = connectionPool.getConnection();

        query = "SELECT * FROM orders WHERE Registred_User = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            firstResults = preparedStatement.executeQuery();

            while(firstResults.next())
            {
                order = new OrderBean();

                order.setId_order(firstResults.getInt(1));
                order.setRegistred_User(firstResults.getInt(2));
                order.setAddress(firstResults.getString(3));
                order.setPayment_method(firstResults.getString(4));
                order.setOrder_owner(firstResults.getString(5));
                order.setTotal_price(firstResults.getFloat(6));
                order.setCreation_date(firstResults.getDate(7));
                order.setDelivery_date(firstResults.getDate(8));
                order.setStatus_order(firstResults.getInt(9));

                query = "SELECT * FROM order_item WHERE Order_number = ?";

                products_in_order = new ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>();

                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, order.getId_order());
                    secondResults = preparedStatement.executeQuery();

                    while(secondResults.next())
                    {
                        order_item = new OrderItemBean();

                        order_item.setId_order_item(secondResults.getInt(1));
                        order_item.setId_order(secondResults.getInt(2));
                        order_item.setId_product(secondResults.getInt(3));
                        order_item.setPrice_in_order(secondResults.getFloat(4));
                        order_item.setQnt_in_order(secondResults.getInt(5));

                        query = "SELECT * FROM product_details WHERE id_product_details = ?";

                        try {
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, order_item.getId_product());
                            thirdResults = preparedStatement.executeQuery();

                            if(thirdResults.first())
                            {
                                productDetails = new ProductDetailsBean();

                                productDetails.setId_prod_details(thirdResults.getInt(1));
                                productDetails.setProduct(thirdResults.getInt(2));
                                productDetails.setColor(thirdResults.getString(3));
                                productDetails.setSize(thirdResults.getString(4));
                                productDetails.setDiscount_percent(thirdResults.getInt(5));
                                productDetails.setQnt_stock(thirdResults.getInt(6));
                                productDetails.setImg_path_folder(thirdResults.getString(7));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        query = "SELECT * FROM products WHERE id_product = ?";

                        try {
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, productDetails.getProduct());
                            fourthResults = preparedStatement.executeQuery();

                            if(fourthResults.first())
                            {
                                product = new ProductBean();

                                product.setId_prod(fourthResults.getInt(1));
                                product.setName(fourthResults.getString(2));
                                product.setBrand(fourthResults.getString(3));
                                product.setModel(fourthResults.getString(4));
                                product.setType(fourthResults.getString(5));
                                product.setPrice(fourthResults.getFloat(6));
                                product.setStatus(fourthResults.getInt(7));
                                product.setTotal_rating(fourthResults.getInt(8));
                                product.setAverage_rating(fourthResults.getFloat(9));
                                product.setNumber_feedback_users(fourthResults.getInt(10));
                                product.setDescription(fourthResults.getString(11));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        products_in_order.add(new Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>(order_item, new Pair<ProductBean, ProductDetailsBean>(product, productDetails)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                orders.put(order, products_in_order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
     * @return all the orders
     */
    public HashMap<OrderBean, ArrayList<OrderItemBean>> retrieveAll() throws SQLException
    {
        HashMap<OrderBean, ArrayList<OrderItemBean>> orders = new HashMap<OrderBean, ArrayList<OrderItemBean>>();
        ArrayList<OrderItemBean> order_items;

        connection = connectionPool.getConnection();

        query = "SELECT * FROM orders";

        try {

            preparedStatement = connection.prepareStatement(query);
            firstResults = preparedStatement.executeQuery();

            while(firstResults.next())
            {
                OrderBean order = new OrderBean();

                order.setId_order(firstResults.getInt(1));
                order.setRegistred_User(firstResults.getInt(2));
                order.setAddress(firstResults.getString(3));
                order.setPayment_method(firstResults.getString(4));
                order.setOrder_owner(firstResults.getString(5));
                order.setTotal_price(firstResults.getFloat(6));
                order.setCreation_date(firstResults.getDate(7));
                order.setDelivery_date(firstResults.getDate(8));
                order.setStatus_order(firstResults.getInt(9));

                query = "SELECT * FROM order_item WHERE Order_number = ?";

                order_items = new ArrayList<OrderItemBean>();

                try
                {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, order.getId_order());
                    secondResults = preparedStatement.executeQuery();

                    while(secondResults.next())
                    {
                        OrderItemBean order_item = new OrderItemBean();

                        order_item.setId_order_item(secondResults.getInt(1));
                        order_item.setId_order(secondResults.getInt(2));
                        order_item.setId_product(secondResults.getInt(3));
                        order_item.setPrice_in_order(secondResults.getFloat(4));
                        order_item.setQnt_in_order(secondResults.getInt(5));

                        order_items.add(order_item);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                orders.put(order, order_items);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
    public LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> retrieveByOrder(int order_id) throws SQLException
    {
        LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap <OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();
        OrderBean order = null;
        ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>> products_in_order;
        OrderItemBean order_item = null;
        ProductDetailsBean productDetails = null;
        ProductBean product = null;

        connection = connectionPool.getConnection();

        query = "SELECT * FROM orders WHERE id_order = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order_id);
            firstResults = preparedStatement.executeQuery();

            if(firstResults.first())
            {
                order = new OrderBean();

                order.setId_order(firstResults.getInt(1));
                order.setRegistred_User(firstResults.getInt(2));
                order.setAddress(firstResults.getString(3));
                order.setPayment_method(firstResults.getString(4));
                order.setOrder_owner(firstResults.getString(5));
                order.setTotal_price(firstResults.getFloat(6));
                order.setCreation_date(firstResults.getDate(7));
                order.setDelivery_date(firstResults.getDate(8));
                order.setStatus_order(firstResults.getInt(9));

                query = "SELECT * FROM order_item WHERE Order_number = ?";

                products_in_order = new ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>();

                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, order.getId_order());
                    secondResults = preparedStatement.executeQuery();

                    while(secondResults.next())
                    {
                        order_item = new OrderItemBean();

                        order_item.setId_order_item(secondResults.getInt(1));
                        order_item.setId_order(secondResults.getInt(2));
                        order_item.setId_product(secondResults.getInt(3));
                        order_item.setPrice_in_order(secondResults.getFloat(4));
                        order_item.setQnt_in_order(secondResults.getInt(5));

                        query = "SELECT * FROM product_details WHERE id_product_details = ?";

                        try {
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, order_item.getId_product());
                            thirdResults = preparedStatement.executeQuery();

                            if(thirdResults.first())
                            {
                                productDetails = new ProductDetailsBean();

                                productDetails.setId_prod_details(thirdResults.getInt(1));
                                productDetails.setProduct(thirdResults.getInt(2));
                                productDetails.setColor(thirdResults.getString(3));
                                productDetails.setSize(thirdResults.getString(4));
                                productDetails.setDiscount_percent(thirdResults.getInt(5));
                                productDetails.setQnt_stock(thirdResults.getInt(6));
                                productDetails.setImg_path_folder(thirdResults.getString(7));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        query = "SELECT * FROM products WHERE id_product = ?";

                        try {
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, productDetails.getProduct());
                            fourthResults = preparedStatement.executeQuery();

                            if(fourthResults.first())
                            {
                                product = new ProductBean();

                                product.setId_prod(fourthResults.getInt(1));
                                product.setName(fourthResults.getString(2));
                                product.setBrand(fourthResults.getString(3));
                                product.setModel(fourthResults.getString(4));
                                product.setType(fourthResults.getString(5));
                                product.setPrice(fourthResults.getFloat(6));
                                product.setStatus(fourthResults.getInt(7));
                                product.setTotal_rating(fourthResults.getInt(8));
                                product.setAverage_rating(fourthResults.getFloat(9));
                                product.setNumber_feedback_users(fourthResults.getInt(10));
                                product.setDescription(fourthResults.getString(11));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        products_in_order.add(new Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>(order_item, new Pair<ProductBean, ProductDetailsBean>(product, productDetails)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                orders.put(order, products_in_order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }

        return orders;
    }

    private static ConnectionPool connectionPool;
    private Connection connection;
    String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results, firstResults, secondResults, thirdResults, fourthResults;
}
