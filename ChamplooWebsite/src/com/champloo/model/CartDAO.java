package com.champloo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.champloo.bean.CartBean;
import com.champloo.bean.CartItemBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.storage.ConnectionPool;

public class CartDAO implements CartModel
{

	public CartDAO()
	{
		
		try {
			//FINIRE A DISCUTERNE CON DAVID/ ALESSANDRO
			connectionPool = ConnectionPool.create("jdbc:mysql://localhost:3306/champloo_store_db", "root", "rootroot");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Override
	public boolean insertProduct(CartBean cart, ProductDetailsBean productDetails) throws SQLException {
		
		int isAdded = 0;
		connection = connectionPool.getConnection();
		
		try {
			//controlla se nel carrello è già presente il prodotto
			query = "SELECT * FORM cart_item WHERE Cart='"+cart.getId_cart()+"' AND Product_Details='"+productDetails.getId_prod_details()+"'";
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			if(results.first())
			{
				// SE IL PRODOTTO NON ESISTE NEL CARRELLO, AGGIUNGILO
				query = "INSERT INTO cart_item(Cart, Product_Details, qnt_in_cart) VALUES (?,?,?)";
				
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setInt(1, cart.getId_cart());
				preparedStatement.setInt(2, productDetails.getId_prod_details());
				preparedStatement.setInt(3, 1); 	// aggiunta del primo prodotto
				
				isAdded = preparedStatement.executeUpdate();	
			}
			else 
			{
				// IL PRODOTTO è GIà PRESENTE NEL CARRELLO, AGGIUNGE +1 ALLA QUANTITà
				int qnt_in_cart = results.getInt("qnt_in_cart");
				query = "UPDATE cart_item SET qnt_in_cart = '"+qnt_in_cart+++"' WHERE Product_Details='"+productDetails.getId_prod_details()+"'";
				
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
	public int retrieveNumberOfProducts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<ProductBean, ProductDetailsBean> retrieveProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float retrieveTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteProduct(CartBean cart, ProductDetailsBean productDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean clearCart(CartBean cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeCart(CartBean cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CartBean retrieveCart(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static ConnectionPool connectionPool;
	private Connection connection;
	String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results;
}
