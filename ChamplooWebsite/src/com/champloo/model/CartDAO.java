package com.champloo.model;

import com.champloo.bean.*;
import com.champloo.storage.ConnectionPool;
import com.champloo.util.ActiveCart;

import javafx.util.Pair;

import java.sql.*;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;

public class CartDAO implements CartModel
{

	public CartDAO()
	{
		try {
			connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/champloo_store_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
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
			query = "SELECT * FROM cart_item WHERE Cart='"+cart.getId_cart()+"' AND Product_Details='"+id_product_details+"'";
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			if(results.first())
			{
				// IL PRODOTTO E' GIA PRESENTE NEL CARRELLO, AGGIUNGE +1 ALLA QUANTIT�
				int qnt_in_cart = results.getInt("qnt_in_cart");
				qnt_in_cart += 1;
				query = "UPDATE cart_item SET qnt_in_cart = '"+qnt_in_cart+"' WHERE Product_Details='"+id_product_details+"'";

				statement = connection.createStatement();
				isAdded = statement.executeUpdate(query);
			}
			else 
			{
				// SE IL PRODOTTO NON ESISTE NEL CARRELLO, AGGIUNGILO
				query = "INSERT INTO cart_item(Cart, Product_Details, qnt_in_cart) VALUES (?,?,?)";

				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setInt(1, cart.getId_cart());
				preparedStatement.setInt(2, id_product_details);
				preparedStatement.setInt(3, 1); 	// aggiunta del primo prodotto

				isAdded = preparedStatement.executeUpdate();
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
	public boolean modifyQuantity(int id_cart, int id_product_details, String operator) throws SQLException
	{
		int isQuantityModified = 0;
		connection = connectionPool.getConnection();
		
		try {
			query = "SELECT * FROM cart_item WHERE Cart = ? AND Product_Details = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, id_cart);
			preparedStatement.setInt(2, id_product_details);
			results = preparedStatement.executeQuery();
			
			CartItemBean cartItem = new CartItemBean();
			
			if(results.first())
			{
				cartItem.setId_cart_item(results.getInt(1));
				cartItem.setCart(results.getInt(2));
				cartItem.setProduct_details(results.getInt(3));
				cartItem.setQnt_in_cart(results.getInt(4));	
			}
			
			int qnt = cartItem.getQnt_in_cart();
			
			if(operator.equals("-"))
			{
				qnt--;
				query = "UPDATE cart_item SET qnt_in_cart = '"+qnt+"' WHERE id_cart_item = '"+cartItem.getId_cart_item()+"'";
				statement = connection.createStatement();
				isQuantityModified = statement.executeUpdate(query);
			}
			else if(operator.equals("+"))
			{
				qnt++;
				query = "UPDATE cart_item SET qnt_in_cart = '"+qnt+"' WHERE id_cart_item = '"+cartItem.getId_cart_item()+"'";
				statement = connection.createStatement();
				isQuantityModified = statement.executeUpdate(query);
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
	public HashMap<Pair<ProductBean,ProductDetailsBean>, Integer> retrieveProducts(CartBean cart) throws SQLException {
		
		HashMap<Pair<ProductBean,ProductDetailsBean>, Integer> products = new HashMap<Pair<ProductBean,ProductDetailsBean>, Integer>(); 
		
		connection = connectionPool.getConnection();
		
		try {
			// seleziona tutti i prodotti(generici) presenti nel carrello 
			query ="SELECT * FROM products WHERE id_product IN (\r\n" +
					"SELECT id_product FROM products WHERE id_product IN (\r\n" +
					"SELECT Product FROM product_details INNER JOIN cart_item ON id_product_details = Product_Details AND Cart = '"+cart.getId_cart()+"' ))";

			
			statement = connection.createStatement();
			firstResults = statement.executeQuery(query);
			
			while(firstResults.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(firstResults.getInt(1));
				product.setName(firstResults.getString(2));
				product.setBrand(firstResults.getString(3));
				product.setModel(firstResults.getString(4));
				product.setType(firstResults.getString(5));
				product.setPrice(firstResults.getFloat(6));
				product.setStatus(firstResults.getInt(7));
				product.setTotal_rating(firstResults.getInt(8));
				product.setAverage_rating(firstResults.getFloat(9));
				product.setNumber_feedback_users(firstResults.getInt(10));
				product.setDescription(firstResults.getString(11));
				
				// seleziona tutti i detteagli dei prodotti(generici) presenti nel carrello 
				query = "SELECT * FROM product_details WHERE id_product_details IN(\r\n" + 
						"	SELECT Product_Details FROM cart_item INNER JOIN product_details ON Product_Details = id_product_details AND Product = '"+product.getId_prod()+"' AND Cart = '"+cart.getId_cart()+"')";
				
				statement = connection.createStatement();
				secondResults = statement.executeQuery(query);
				
				while(secondResults.next())
				{
					ProductDetailsBean productDetails = new ProductDetailsBean();
					
					productDetails.setId_prod_details(secondResults.getInt(1));
					productDetails.setProduct(secondResults.getInt(2));
					productDetails.setColor(secondResults.getString(3));
					productDetails.setSize(secondResults.getString(4));
					productDetails.setDiscount_percent(secondResults.getInt(5));
					productDetails.setQnt_stock(secondResults.getInt(6));
					productDetails.setImg_path_folder(secondResults.getString(7));
					
					query = "SELECT qnt_in_cart FROM cart_item WHERE Cart = '"+cart.getId_cart()+"' AND Product_Details = '"+productDetails.getId_prod_details()+"'";			
					
					statement = connection.createStatement();
					results = statement.executeQuery(query);
					
					int qnt = 0;
					
					if(results.first())
						qnt = results.getInt(1);
				
					Pair<ProductBean, ProductDetailsBean> newPair = new Pair<ProductBean, ProductDetailsBean>(product, productDetails);
					products.put(newPair, qnt);
				}
				
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
	public float retrieveTotal(CartBean cart) throws SQLException 
	{	
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
	public boolean deleteProduct(int id_cart, int id_product_details) throws SQLException {
		
		int isDeleted = 0;
		
		connection = connectionPool.getConnection();
		
		try {			
			query = "DELETE FROM cart_item WHERE Product_Details='"+id_product_details+"' AND Cart='"+id_cart+"'";
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
			
			query = "DELETE FROM cart_item WHERE Cart ='"+cart.getId_cart()+"'";
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
			} else {
				query = "INSERT INTO carts(Registred_User) VALUES (?)";

				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, user.getID());

				preparedStatement.executeUpdate();
				query = "SELECT * FROM carts WHERE Registred_User = '"+user.getID()+"'";

				statement = connection.createStatement();

				results = statement.executeQuery(query);

				if(results.first())
				{
					cart = new CartBean();
					cart.setId_cart(results.getInt(1));
					cart.setUser(results.getInt(2));
				}
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
		
		CartItemBean cartItem = new CartItemBean();
		
		connection = connectionPool.getConnection();
		
		try {
			query = "SELECT * FROM cart_item WHERE id_cart_item='"+id_cart_item+"'";
			statement = connection.createStatement();
			
			results = statement.executeQuery(query);
			
			if(results.first())
			{
				cartItem.setId_cart_item(results.getInt(1));
				cartItem.setCart(results.getInt(2));
				cartItem.setProduct_details(results.getInt(3));
				cartItem.setQnt_in_cart(results.getInt(4));
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
		
		return cartItem;
	}
	
	@Override
	public void storeActiveCartInDb(ActiveCart activeCart, int id_user) throws SQLException {
		
		Iterator<Entry<Pair<ProductBean, ProductDetailsBean>, Integer>> iterator =  activeCart.getCartIterator();
		CartBean cart = new CartBean();
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM carts WHERE Registred_User = '"+id_user+"'";
		
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			if (results.first())
			{
				cart.setId_cart(results.getInt(1));
				cart.setUser(results.getInt(2));
			}
			else 
			{
				query = "SELECT * FROM registred_user WHERE id_user = '"+id_user+"'";
				statement = connection.createStatement();
				results = statement.executeQuery(query);
					
				if(results.first())
				{
					UserBean user = new UserBean();
					
					user.setID(results.getInt(1));
					user.setFirstName(results.getString(2));
					user.setSurname(results.getString(3));
					user.setUsername(results.getString(4));
					user.setEmail(results.getString(5));
					user.setPassword(results.getString(6));
					user.setRegistration_date(results.getDate(7));
					user.setType(results.getInt(8));
				
					createCart(user);
					
					query = "SELECT * FROM carts WHERE Registred_User = '"+id_user+"'";
					statement = connection.createStatement();
					results = statement.executeQuery(query);
					
					if (results.first())
					{
						cart.setId_cart(results.getInt(1));
						cart.setUser(results.getInt(2));
					}
				}
			}
			
			while(iterator.hasNext())
			{
				Entry<Pair<ProductBean, ProductDetailsBean>, Integer> entry = iterator.next();
				
				Pair<ProductBean, ProductDetailsBean> productPair = entry.getKey();
				ProductBean product = productPair.getKey();
				ProductDetailsBean productDetails = productPair.getValue();
				int qntInCart = (int) entry.getValue();
				
				query = "INSERT INTO cart_item (Cart, Product_Details, qnt_in_cart) VALUES (?,?,?)";
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, cart.getId_cart());
				preparedStatement.setInt(2, productDetails.getId_prod_details());
				preparedStatement.setInt(3, qntInCart);
				
				preparedStatement.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
	}
	
	private static ConnectionPool connectionPool;
	private Connection connection;
	String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results, firstResults, secondResults;
	
}
