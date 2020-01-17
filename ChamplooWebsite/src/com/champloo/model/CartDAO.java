package com.champloo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;

import com.champloo.bean.CartBean;
import com.champloo.bean.CartItemBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.bean.UserBean;
import com.champloo.storage.ConnectionPool;
import com.sun.org.apache.regexp.internal.REUtil;

public class CartDAO implements CartModel
{

	public CartDAO()
	{
		try {
			connectionPool = ConnectionPool.create("jdbc:mysql://localhost:3306/champloo_store_db", "root", "rootroot");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public boolean insertProduct(CartBean cart, int id_product_details) throws SQLException {
		
		int isAdded = 0;
		connection = connectionPool.getConnection();
		
		try {
			//controlla se nel carrello è già presente il prodotto
			query = "SELECT * FORM cart_item WHERE Cart='"+cart.getId_cart()+"' AND Product_Details='"+id_product_details+"'";
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			if(results.first())
			{
				// SE IL PRODOTTO NON ESISTE NEL CARRELLO, AGGIUNGILO
				query = "INSERT INTO cart_item(Cart, Product_Details, qnt_in_cart) VALUES (?,?,?)";
				
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setInt(1, cart.getId_cart());
				preparedStatement.setInt(2, id_product_details);
				preparedStatement.setInt(3, 1); 	// aggiunta del primo prodotto
				
				isAdded = preparedStatement.executeUpdate();	
			}
			else 
			{
				// IL PRODOTTO è GIà PRESENTE NEL CARRELLO, AGGIUNGE +1 ALLA QUANTITà
				int qnt_in_cart = results.getInt("qnt_in_cart");
				query = "UPDATE cart_item SET qnt_in_cart = '"+qnt_in_cart+++"' WHERE Product_Details='"+id_product_details+"'";
				
				statement = connection.createStatement();
				isAdded = statement.executeUpdate(query);			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
					
		return isAdded != 0;
	}

	@Override
	public boolean modifyQuantity(CartItemBean cartItem, int quantity) throws SQLException {
		
		int isQuantityModified = 0;
		connection = connectionPool.getConnection();
		
		try {
			int new_quantity = cartItem.getQnt_in_cart() + quantity;
			
			query = "UPDATE car_item SET qnt_in_cart ='"+new_quantity+"' WHERE id_cart_item='"+cartItem.getId_cart_item()+"'";
			statement = connection.createStatement();
			isQuantityModified = statement.executeUpdate(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (statement != null)
                    statement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return isQuantityModified != 0;
	}

	@Override
	public int retrieveNumberOfProducts(CartBean cart) throws SQLException {
		int products = 0;
		
		connection = connectionPool.getConnection();
		
		try {
			query = "SELECT * FROM cart_item WHERE Cart='"+cart.getId_cart()+"'";
			statement = connection.createStatement();
			
			results = statement.executeQuery(query);
			
			while(results.next())
			{
				products++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (statement != null)
                    statement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
	}

	@Override
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveProducts(CartBean cart) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		
		connection = connectionPool.getConnection();
		
		try {
			// seleziona tutti i prodotti(generici) presenti nel carrello 
			query ="\"SELECT * FROM products WHERE id_product IN (\\r\\n\" + \r\n" + 
					"				\"	SELECT id_product FROM products WHERE id_product IN (\\r\\n\" + \r\n" + 
					"				\"		SELECT Product FROM product_details INNER JOIN cart_item ON id_product_details = Product_Details AND Cart = '\"++\"\"'\\r\\n\" + \r\n" + 
					"				\"	)    \\r\\n\" + \r\n" + 
					"				\") \"";
			
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			while(results.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setDescription(results.getString(6));
				
				// seleziona tutti i detteagli dei prodotti(generici) presenti nel carrello 
				query = "SELECT * FROM product_details WHERE id_product_details IN(\r\n" + 
						"	SELECT Product_Details FROM cart_item INNER JOIN product_details ON Product_Details = id_product_details AND Product = '"+product.getId_prod()+"'\r\n" + 
						")";
				
				statement = connection.createStatement();
				results = statement.executeQuery(query);
				
				while(results.next())
				{
					ProductDetailsBean productDetails = new ProductDetailsBean();
					
					productDetails.setId_prod_details(results.getInt(1));
					productDetails.setProduct(results.getInt(2));
					productDetails.setColor(results.getString(3));
					productDetails.setSize(results.getString(4));
					productDetails.setPrice(results.getFloat(5));
					productDetails.setDiscount_percent(results.getInt(6));
					productDetails.setDiscounted_price(results.getFloat(7));
					productDetails.setQnt_stock(results.getInt(8));
					productDetails.setStatus(results.getInt(9));
					productDetails.setAverage_rating(results.getFloat(10));
					productDetails.setNumber_feedback_users(results.getInt(11));
					productDetails.setImg_path_folder(results.getString(12));
				
					productsDetails.add(productDetails);			
				}
				products.put(product, productsDetails);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (statement != null)
                    statement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
	}

	@Override
	public float retrieveTotal(CartBean cart) throws SQLException {
		
		float total = 0;
		
		connection = connectionPool.getConnection();
		
		try {
			query = "SELECT price,qnt_in_cart FROM product_details INNER JOIN cart_item ON id_product_details = Product_Details AND Cart = '"+cart.getId_cart()+"'";
			
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			while(results.next())
			{
				float singleProductPrice = results.getFloat(1);
				int productQnt = results.getInt(2);
				
				total += (singleProductPrice*productQnt);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (statement != null)
                    statement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return total;
	}

	@Override
	public boolean deleteProduct(ProductDetailsBean productDetails) throws SQLException {
		
		int isDeleted = 0;
		
		connection = connectionPool.getConnection();
		
		try {
			
			query = "DELETE FROM cart_item WHERE id_product_details='"+productDetails.getId_prod_details()+"'";
			statement = connection.createStatement();
			isDeleted = statement.executeUpdate(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (statement != null)
                    statement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return isDeleted != 0;
	}

	@Override
	public boolean clearCart(CartBean cart) throws SQLException {
		
		int isCleared = 0;
		
		connection = connectionPool.getConnection();
		
		try {
			
			query = "DELETE FROM cart WHERE id_cart ='"+cart.getId_cart()+"'";
			statement = connection.createStatement();
			isCleared = statement.executeUpdate(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (statement != null)
                    statement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return isCleared != 0;
	}

	@Override
	public boolean createCart(UserBean user) throws SQLException {
		
		int isCreated = 0;
		
		connection = connectionPool.getConnection();
		
		try {
			
			query = "INSERT INTO carts(Registred_User) VALUES (?)";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, user.getID());
			
			isCreated = preparedStatement.executeUpdate();
			
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
		
		return isCreated != 0;
	}

	@Override
	public CartBean retrieveCart(UserBean user) throws SQLException {
		
		CartBean cart = null;
		connection = connectionPool.getConnection();
		
		try {
			query = "SELECT * FROM carts WHERE Registred_User = '"+user.getID()+"'";
			statement = connection.createStatement();
			
			results = statement.executeQuery(query);
			
			if(results.first())
			{
				cart = new CartBean();
				cart.setId_cart(results.getInt(1));
				cart.setUser(results.getInt(2));
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if (statement != null)
                    statement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return cart;
	}
	
	@Override
	public CartItemBean retrieveCartItem(int id_cart_item) throws SQLException {
		
		CartItemBean cartItem  = null;
		
		return cartItem;
	}
	
	private static ConnectionPool connectionPool;
	private Connection connection;
	String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results;
	
}
