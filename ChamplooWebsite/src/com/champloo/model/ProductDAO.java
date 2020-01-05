package com.champloo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.storage.ConnectionPool;

public class ProductDAO implements ProductModel 
{
	public ProductDAO()
	{
		try {
			//FINIRE A DISCUTERNE CON DAVID/ ALESSANDRO
			connectionPool = ConnectionPool.create("", "", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
     * Adds a new product into DB
     * param newProduct
     * return product_created
     */
	// SOLUZIONE DA DISCUTERE, TROPPO ARTICOLATA
	public boolean addProduct(ProductBean newProduct, ProductDetailsBean newProductDetails) throws SQLException
	{
		int isProduct_added = 0;
		int isProduct_details_added = 0;
		int product_auto_key = 0;
		
		connection = connectionPool.getConnection();
		
		query = "SELECT id_product, name_product, brand_product, model_product, type_product FROM products WHERE name_product='"+newProduct.getName()+"' AND brand_product='"+newProduct.getBrand()+"' AND model_product='"+newProduct.getModel()+"' AND type_prod='"+newProduct.getType()+"'";                            
		
		statement = connection.createStatement();
		results = statement.executeQuery(query);
		
		if(!results.first())
		{
		// SE IL PRODOTTO NON ESISTE NEL DB, AGGIUNGI IL PRODOTTO E I SUOI DETTAGLI
		
		query = "INSERT INTO products(name_product, brand_product, model_product, type_product, description_product) VALUES (?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, newProduct.getName());
			preparedStatement.setString(2, newProduct.getBrand());
			preparedStatement.setString(3, newProduct.getModel());
			preparedStatement.setString(4, newProduct.getType());
			preparedStatement.setString(5, newProduct.getDescription());
			
			isProduct_added = preparedStatement.executeUpdate();
			
			product_auto_key = preparedStatement.getGeneratedKeys().getInt(1);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		query = "INSERT INTO product_details(Product, color, size, price, discount_percent, discounted_price, qnt_stock, status, average_rating, number_feedback_users, img_folder_path) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
		try{
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, product_auto_key);
			preparedStatement.setString(2, newProductDetails.getColor());
			preparedStatement.setString(3, newProductDetails.getSize());
			preparedStatement.setFloat(4, newProductDetails.getPrice());
			preparedStatement.setInt(5, newProductDetails.getDiscount_percent());
			preparedStatement.setFloat(6, newProductDetails.getDiscounted_price());
			preparedStatement.setInt(7, newProductDetails.getQnt_stock());
			preparedStatement.setInt(8, newProductDetails.getStatus());
			preparedStatement.setFloat(9, 0);
			preparedStatement.setInt(10, 0);
			preparedStatement.setString(11, newProductDetails.getImg_path_folder());
			
			isProduct_details_added = preparedStatement.executeUpdate();	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
			
		}
	}
	else 
	{
	//IL PRODOTTO GIà ESISTE NEL DB, VENGONO INSERITI SOLAMENTE I RELATIVI DETTAGLI
		product_auto_key = results.getInt(1);
		
		query = "INSERT INTO product_details(Product, color, size, price, discount_percent, discounted_price, qnt_stock, status, average_rating, number_feedback_users, img_folder_path) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, product_auto_key);
			preparedStatement.setString(2, newProductDetails.getColor());
			preparedStatement.setString(3, newProductDetails.getSize());
			preparedStatement.setFloat(4, newProductDetails.getPrice());
			preparedStatement.setInt(5, newProductDetails.getDiscount_percent());
			preparedStatement.setFloat(6, newProductDetails.getDiscounted_price());
			preparedStatement.setInt(7, newProductDetails.getQnt_stock());
			preparedStatement.setInt(8, newProductDetails.getStatus());
			preparedStatement.setFloat(9, 0);
			preparedStatement.setInt(10, 0);
			preparedStatement.setString(11, newProductDetails.getImg_path_folder());
			
			isProduct_details_added = preparedStatement.executeUpdate();	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
			
		}
		
	}		
		return isProduct_added != 0 && isProduct_details_added != 0;
	}

	/**
     * Retrieves all products with a given model
     * param model_prod
     * return products
     */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByModel(String model_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE model_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, model_product);
			
			results = preparedStatement.executeQuery();
			
			while(results.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setDescription(results.getString(6));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					results = preparedStatement.executeQuery();	
					
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
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				products.put(product, productsDetails);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
	}

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByCategory(String type_product) throws SQLException{
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE type_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, type_product);
			
			results = preparedStatement.executeQuery();
			
			while(results.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setDescription(results.getString(6));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					results = preparedStatement.executeQuery();	
					
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
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				products.put(product, productsDetails);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
	}

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByBrand(String brand_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE brand_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, brand_product);
			
			results = preparedStatement.executeQuery();
			
			while(results.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setDescription(results.getString(6));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					results = preparedStatement.executeQuery();	
					
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
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				products.put(product, productsDetails);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
	}

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByColor(String color_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE color_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, color_product);
			
			results = preparedStatement.executeQuery();
			
			while(results.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setDescription(results.getString(6));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					results = preparedStatement.executeQuery();	
					
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
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				products.put(product, productsDetails);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
	}

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveBySize(String size_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE size_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, size_product);
			
			results = preparedStatement.executeQuery();
			
			while(results.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setDescription(results.getString(6));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					results = preparedStatement.executeQuery();	
					
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
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				products.put(product, productsDetails);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
	}

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByStatus(String status_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE status_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, status_product);
			
			results = preparedStatement.executeQuery();
			
			while(results.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setDescription(results.getString(6));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					results = preparedStatement.executeQuery();	
					
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
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				products.put(product, productsDetails);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
	}

	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveAll() throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products";
		
		try {
			preparedStatement = connection.prepareStatement(query);
		
			results = preparedStatement.executeQuery();
			
			while(results.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setDescription(results.getString(6));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					results = preparedStatement.executeQuery();	
					
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
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				products.put(product, productsDetails);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
            }
		}
		
		return products;
		
	}

	public boolean deleteProduct(ProductBean product) throws SQLException {

		int product_deleted = 0;
		
		connection = connectionPool.getConnection();
		
		query = "DELETE FROM products WHERE id_product = '"+product.getId_prod()+"'";
		
		try {
            
			statement = connection.createStatement();
            product_deleted = statement.executeUpdate(query);
        
		} finally {
            try {
                
            	if (statement != null)
                    statement.close();
            
            } finally {
            	connectionPool.releaseConnection(connection);
            }
        }

        return product_deleted != 0;
	}

	public boolean updateProduct(ProductBean newProduct, ProductDetailsBean newProductDetails) throws SQLException {
	
		connection = connectionPool.getConnection();
		
		try {
		// update del prodotto
		statement.executeUpdate(updateProduct("name_product", newProduct.getName(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("brand_product", newProduct.getBrand(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("model_product", newProduct.getModel(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("type_product", newProduct.getType(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("description_product", newProduct.getDescription(), newProduct.getId_prod()));
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
		//update dei dettagli del prodotto
		statement.executeUpdate(updateProduct("color", newProductDetails.getColor(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("size", newProductDetails.getSize(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("price", ""+newProductDetails.getPrice(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("discount_percent", ""+newProductDetails.getDiscount_percent(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("discounted_price", ""+newProductDetails.getDiscounted_price(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("qnt_stock", ""+newProductDetails.getQnt_stock(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("status", ""+newProductDetails.getStatus(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("img_path_folder", newProductDetails.getColor(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	private static String updateProduct(String columnName, String value, int id_product) {
		return "UPDATE products SET "+columnName+" = '"+value+"' WHERE id_product = '"+id_product+"' ";
	}
	
	private static String updateProduct(String columnName, String value, int product, int id_product_details) {
		return "UPDATE products SET "+columnName+" = '"+value+"' WHERE product = '"+product+"' AND id_product_details = '"+id_product_details+"'";
	}
	
	private static ConnectionPool connectionPool;
    private Connection connection;
    String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results;
}
