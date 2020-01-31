package com.champloo.model;

import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.storage.ConnectionPool;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class ProductDAO implements ProductModel 
{
	public ProductDAO()
	{
		try {
			connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/champloo_store_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
		} catch (SQLException e) {
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
		
		query = "SELECT * FROM products WHERE name_product='"+newProduct.getName()+"' AND brand_product='"+newProduct.getBrand()+"' AND model_product='"+newProduct.getModel()+"' AND type_product='"+newProduct.getType()+"'";                            
		
		try 
		{		
			statement = connection.createStatement();
			results = statement.executeQuery(query);
					
			if(!results.first())
			{
			// SE IL PRODOTTO NON ESISTE NEL DB, AGGIUNGI IL PRODOTTO E I SUOI DETTAGLI
			
				query = "INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES (?,?,?,?,?,?,?,?,?,?)";
				
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					
				preparedStatement.setString(1, newProduct.getName());
				preparedStatement.setString(2, newProduct.getBrand());
				preparedStatement.setString(3, newProduct.getModel());
				preparedStatement.setString(4, newProduct.getType());
				preparedStatement.setFloat(5, newProduct.getPrice());
				preparedStatement.setInt(6, newProduct.getStatus());
				//total_rating
				preparedStatement.setInt(7, 0);
				//average_rating
				preparedStatement.setFloat(8, 0);
				//number_feedback_users
				preparedStatement.setInt(9, 0);
				preparedStatement.setString(10, newProduct.getDescription());
					
				isProduct_added = preparedStatement.executeUpdate();
					
				ResultSet autokey = preparedStatement.getGeneratedKeys();
				
				if( autokey.first())
					product_auto_key = autokey.getInt(1);
							
				query = "INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES (?,?,?,?,?,?)";
					
				preparedStatement = connection.prepareStatement(query);
					
				preparedStatement.setInt(1, product_auto_key);
				preparedStatement.setString(2, newProductDetails.getColor());
				preparedStatement.setString(3, newProductDetails.getSize());
				preparedStatement.setInt(4, newProductDetails.getDiscount_percent());
				preparedStatement.setInt(5, newProductDetails.getQnt_stock());
				preparedStatement.setString(6, newProductDetails.getImg_path_folder());
					
				isProduct_details_added = preparedStatement.executeUpdate();	
						
			}
			else 
			{
			//IL PRODOTTO GI� ESISTE NEL DB, VENGONO INSERITI SOLAMENTE I RELATIVI DETTAGLI
				product_auto_key = results.getInt(1);
				
				query = "INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES (?,?,?,?,?,?)";
				
				preparedStatement = connection.prepareStatement(query);
					
				preparedStatement.setInt(1, product_auto_key);
				preparedStatement.setString(2, newProductDetails.getColor());
				preparedStatement.setString(3, newProductDetails.getSize());
				preparedStatement.setInt(4, newProductDetails.getDiscount_percent());
				preparedStatement.setInt(5, newProductDetails.getQnt_stock());
				preparedStatement.setString(6, newProductDetails.getImg_path_folder());
					
				isProduct_details_added = preparedStatement.executeUpdate();				
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
		return isProduct_added != 0 || isProduct_details_added != 0;
	}
	
	@Override
	public Pair<ProductBean, ProductDetailsBean> retrieveProductWithDetails(int id_product, int id_product_details) throws SQLException {
		
		connection = connectionPool.getConnection();
		
		Pair<ProductBean, ProductDetailsBean> productWithDetails = null;
		ProductBean product = new ProductBean();
		ProductDetailsBean productDetails = new ProductDetailsBean();
		
		try {
			query = "SELECT * FROM products WHERE id_product = '"+id_product+"'";
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			if(results.first())
			{
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setPrice(results.getFloat(6));
				product.setStatus(results.getInt(7));
				product.setTotal_rating(results.getInt(8));
				product.setAverage_rating(results.getFloat(9));
				product.setNumber_feedback_users(results.getInt(10));
				product.setDescription(results.getString(11));
			}
			
			query = "SELECT * FROM product_details WHERE Product = '"+id_product+"' AND id_product_details = '"+id_product_details+"' ";
			statement = connection.createStatement();
			secondResults = statement.executeQuery(query);
			
			if(secondResults.first())
			{
				productDetails.setId_prod_details(secondResults.getInt(1));
				productDetails.setProduct(secondResults.getInt(2));
				productDetails.setColor(secondResults.getString(3));
				productDetails.setSize(secondResults.getString(4));
				productDetails.setDiscount_percent(secondResults.getInt(5));
				productDetails.setQnt_stock(secondResults.getInt(6));
				productDetails.setImg_path_folder(secondResults.getString(7));
			}
			
			productWithDetails = new Pair<ProductBean, ProductDetailsBean>(product, productDetails);
			
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
		
		return productWithDetails;
	}
	
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveById(int id_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
		ArrayList<ProductDetailsBean> productsDetails = null; 
		
		connection = connectionPool.getConnection();
		try {
			statement = connection.createStatement();
	
			query = "SELECT * FROM products WHERE id_product = '"+id_product+"'";
			
			firstResults = statement.executeQuery(query);
			
			ProductBean product = new ProductBean();
			
			while(firstResults.next())
			{
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
			}
			
			query = "SELECT * FROM product_details WHERE product = '"+id_product+"'";
			
			secondResults = statement.executeQuery(query);
			
			productsDetails = new ArrayList<ProductDetailsBean>();
			
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
				
				productsDetails.add(productDetails);
			}
			
			products.put(product, productsDetails);
		
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
		
		return products;
	}
	
	/**
     * Retrieves all products with a given model
     * param model_prod
     * return products
     */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByModel(String model_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = null;
		
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE model_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, model_product);
			
			firstResults = preparedStatement.executeQuery();
			
			while(firstResults.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setPrice(results.getFloat(6));
				product.setStatus(results.getInt(7));
				product.setTotal_rating(results.getInt(8));
				product.setAverage_rating(results.getFloat(9));
				product.setNumber_feedback_users(results.getInt(10));
				product.setDescription(results.getString(11));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					secondResults = preparedStatement.executeQuery();
					
					productsDetails = new ArrayList<ProductDetailsBean>();
					
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

	/**
     * Retrieves all products with a given category(expressed by type_product)
     * param type_product
     * return products
     */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByCategory(String type_product) throws SQLException{
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = null;
		
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE type_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, type_product);
			
			firstResults = preparedStatement.executeQuery();
			
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
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					secondResults = preparedStatement.executeQuery();
					
					productsDetails = new ArrayList<ProductDetailsBean>();
					
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

	/**
     * Retrieves all products with a given brand
     * param brand_product
     * return an HashMap of products with their relatives details
     */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByBrand(String brand_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = null;
		
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE brand_product = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, brand_product);
			
			firstResults = preparedStatement.executeQuery();
			
			while(firstResults.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setPrice(results.getFloat(6));
				product.setStatus(results.getInt(7));
				product.setTotal_rating(results.getInt(8));
				product.setAverage_rating(results.getFloat(9));
				product.setNumber_feedback_users(results.getInt(10));
				product.setDescription(results.getString(11));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					secondResults = preparedStatement.executeQuery();
					
					productsDetails = new ArrayList<ProductDetailsBean>();
					
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

	/**
	 * Retrieves all products with rating > 60
	 * param
	 * return an HashMap of products with their relatives details
	 */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByFeedbacks() throws SQLException {

		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
		ArrayList<ProductDetailsBean> productsDetails = null;

		connection = connectionPool.getConnection();

		query = "SELECT * FROM products WHERE number_feedback_users < 30";

		try {
			preparedStatement = connection.prepareStatement(query);

			firstResults = preparedStatement.executeQuery();

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

				query = "SELECT * FROM product_details WHERE Product = ?";

				try {
					preparedStatement = connection.prepareStatement(query);

					preparedStatement.setInt(1, product.getId_prod());

					secondResults = preparedStatement.executeQuery();

					productsDetails = new ArrayList<ProductDetailsBean>();

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

	/**
	 * Retrieves all products with average rating > 3
	 * param
	 * return an HashMap of products with their relatives details
	 */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByAverage() throws SQLException {

		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
		ArrayList<ProductDetailsBean> productsDetails = null;

		connection = connectionPool.getConnection();

		query = "SELECT * FROM products WHERE total_rating/number_feedback_users > 3";

		try {
			preparedStatement = connection.prepareStatement(query);

			firstResults = preparedStatement.executeQuery();

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

				query = "SELECT * FROM product_details WHERE Product = ?";

				try {
					preparedStatement = connection.prepareStatement(query);

					preparedStatement.setInt(1, product.getId_prod());

					secondResults = preparedStatement.executeQuery();

					productsDetails = new ArrayList<ProductDetailsBean>();

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

	/**
     * Retrieves all products with a given color
     * param color_product
     * return an HashMap of products with their relatives details
     */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByColor(String color_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = null;
		
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE id_product IN (\r\n" + 
				"		SELECT id_product FROM products INNER JOIN product_details ON id_product = Product AND color = ? );";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, color_product);
			
			firstResults = preparedStatement.executeQuery();
			
			while(firstResults.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setPrice(results.getFloat(6));
				product.setStatus(results.getInt(7));
				product.setTotal_rating(results.getInt(8));
				product.setAverage_rating(results.getFloat(9));
				product.setNumber_feedback_users(results.getInt(10));
				product.setDescription(results.getString(11));
				
				query = "SELECT * FROM product_details WHERE Product = ? AND color = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					preparedStatement.setString(2, color_product);
					
					secondResults = preparedStatement.executeQuery();	
					
					productsDetails = new ArrayList<ProductDetailsBean>();
					
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

	/**
     * Retrieves all products with a given size
     * param size_product
     * return an HashMap of products with their relatives details
     */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveBySize(String size_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = null;
		
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE id_product IN (\r\n" + 
				"		SELECT id_product FROM products INNER JOIN product_details ON id_product = Product AND size = ? );";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, size_product);
			
			firstResults = preparedStatement.executeQuery();
			
			while(firstResults.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setPrice(results.getFloat(6));
				product.setStatus(results.getInt(7));
				product.setTotal_rating(results.getInt(8));
				product.setAverage_rating(results.getFloat(9));
				product.setNumber_feedback_users(results.getInt(10));
				product.setDescription(results.getString(11));
				
				query = "SELECT * FROM product_details WHERE Product = ? AND size = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					preparedStatement.setString(2, size_product);
					
					secondResults = preparedStatement.executeQuery();	
					
					productsDetails = new ArrayList<ProductDetailsBean>();
					
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

	/**
     * Retrieves all products with a given status
     * param status_product
     * return an HashMap of products with their relatives details
     */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveByStatus(String status_product) throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = new ArrayList<ProductDetailsBean>();
		
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE status_product = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, status_product);
			
			firstResults = preparedStatement.executeQuery();
	
			while(firstResults.next())
			{
				ProductBean product = new ProductBean();
				
				product.setId_prod(results.getInt(1));
				product.setName(results.getString(2));
				product.setBrand(results.getString(3));
				product.setModel(results.getString(4));
				product.setType(results.getString(5));
				product.setPrice(results.getFloat(6));
				product.setStatus(results.getInt(7));
				product.setTotal_rating(results.getInt(8));
				product.setAverage_rating(results.getFloat(9));
				product.setNumber_feedback_users(results.getInt(10));
				product.setDescription(results.getString(11));
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					secondResults = preparedStatement.executeQuery();

					productsDetails = new ArrayList<ProductDetailsBean>();
					
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
					
						productsDetails.add(productDetails);			
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

	/**
     * Retrieves all products
     * param *none*
     * return an HashMap of all products with their relatives details
     */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveAll() throws SQLException {
		
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>(); 
		ArrayList<ProductDetailsBean> productsDetails = null;
		
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products";
		
		try {
			preparedStatement = connection.prepareStatement(query);
		
			firstResults = preparedStatement.executeQuery();
			
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
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				try {
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, product.getId_prod());
					
					secondResults = preparedStatement.executeQuery();	
					
					while(secondResults.next())
					{
						productsDetails = new ArrayList<ProductDetailsBean>();
						
						ProductDetailsBean productDetails = new ProductDetailsBean();
						
						productDetails.setId_prod_details(secondResults.getInt(1));
						productDetails.setProduct(secondResults.getInt(2));
						productDetails.setColor(secondResults.getString(3));
						productDetails.setSize(secondResults.getString(4));
						productDetails.setDiscount_percent(secondResults.getInt(5));
						productDetails.setQnt_stock(secondResults.getInt(6));
						productDetails.setImg_path_folder(secondResults.getString(7));
					
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
	
	public ArrayList<Pair<ProductBean, ProductDetailsBean>> createWindow() throws SQLException
	{
		ArrayList<Pair<ProductBean, ProductDetailsBean>> window = new ArrayList<Pair<ProductBean,ProductDetailsBean>>();
		ArrayList<ProductDetailsBean> details = new ArrayList<ProductDetailsBean>();
		boolean isInWindow = false;
			
		connection = connectionPool.getConnection();
		
		query = "SELECT * FROM products WHERE status_product = '"+ProductBean.WINDOW_PRODUCT+"'";
		
		try {
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
				
				query = "SELECT * FROM product_details WHERE Product = ?";
				
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, product.getId_prod());
				
				secondResults = preparedStatement.executeQuery();
						
				while(secondResults.next())
				{
					isInWindow = false;
					
					ProductDetailsBean productDetails = new ProductDetailsBean();
					
					productDetails.setId_prod_details(secondResults.getInt(1));
					productDetails.setProduct(secondResults.getInt(2));
					productDetails.setColor(secondResults.getString(3));
					productDetails.setSize(secondResults.getString(4));
					productDetails.setDiscount_percent(secondResults.getInt(5));
					productDetails.setQnt_stock(secondResults.getInt(6));
					productDetails.setImg_path_folder(secondResults.getString(7));
						
					//details.add(productDetails);
					
					if(window.isEmpty())
						window.add(new Pair<ProductBean, ProductDetailsBean>(product, productDetails));			
					else 
					{
						for(int i = 0; i < window.size(); i++)
						{
							if(productDetails.getProduct() == window.get(i).getValue().getProduct() && productDetails.getColor().equals(window.get(i).getValue().getColor()) )							
								isInWindow = true;
						}
					
						if(!isInWindow)
							window.add(new Pair<ProductBean, ProductDetailsBean>(product, productDetails));
					}
				}	
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

		return window;
	}

	/**
	 * Retrieves all products with average rating > 3
	 * param
	 * return an HashMap of products with their relatives details
	 * @return
	 */
	public HashMap<ProductBean, ArrayList<ProductDetailsBean>> retrieveNewProducts() throws SQLException
	{
		HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
		ArrayList<ProductDetailsBean> productsDetails = null;

		connection = connectionPool.getConnection();

		query = "SELECT * FROM products WHERE status_product = '"+ProductBean.SLIDER_PRODUCT+"'";

		try {
			preparedStatement = connection.prepareStatement(query);

			firstResults = preparedStatement.executeQuery();

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

				query = "SELECT * FROM product_details WHERE Product = ?";

				try {
					preparedStatement = connection.prepareStatement(query);

					preparedStatement.setInt(1, product.getId_prod());

					secondResults = preparedStatement.executeQuery();

					productsDetails = new ArrayList<ProductDetailsBean>();

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
	
	

	/**
     * Deletes a given product
     * param product
     * return an boolean outcome 
     * */
	public boolean deleteProduct(int id_product) throws SQLException {

		int product_deleted = 0;
		
		connection = connectionPool.getConnection();
		
		query = "DELETE FROM products WHERE id_product = '"+id_product+"'";
		
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

	/**
     * Updates a given product
     * param product
     * return an boolean outcome 
     * */
	public boolean updateProduct(ProductBean newProduct, ProductDetailsBean newProductDetails) throws SQLException {
	
		connection = connectionPool.getConnection();
		statement = connection.createStatement();
		
		try {
		// update del prodotto
		statement.executeUpdate(updateProduct("name_product", newProduct.getName(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("brand_product", newProduct.getBrand(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("model_product", newProduct.getModel(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("type_product", newProduct.getType(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("price_product", ""+newProduct.getPrice(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("status_product", ""+newProduct.getStatus(), newProduct.getId_prod()));
		statement.executeUpdate(updateProduct("description_product", newProduct.getDescription(), newProduct.getId_prod()));

		//update dei dettagli del prodotto
		statement.executeUpdate(updateProduct("color", newProductDetails.getColor(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("size", newProductDetails.getSize(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("discount_percent", ""+newProductDetails.getDiscount_percent(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("qnt_stock", ""+newProductDetails.getQnt_stock(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));
		statement.executeUpdate(updateProduct("img_path_folder", newProductDetails.getColor(), newProduct.getId_prod(), newProductDetails.getId_prod_details()));

		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				connectionPool.releaseConnection(connection);
			}
		}
		return true;
	}
	
	/**
     * Updates a single column with a given value.The record is identified by "id_product"
     * param columnName, value, id_product
     * return an boolean outcome 
     * */
	// metodo ausiliario interno per l'update di una singola colonna della tabella 'products'
	private static String updateProduct(String columnName, String value, int id_product) {
		return "UPDATE products SET "+columnName+" = '"+value+"' WHERE id_product = '"+id_product+"' ";
	}
	
	/**
     * Updates a single column with a given value.The record is identified by "product" and "id_product_details"
     * param columnName, value, product, id_product_details
     * return an boolean outcome 
     * */
	// metodo ausiliario interno per l'update di una singola colonna della tabella 'product_details'
	private static String updateProduct(String columnName, String value, int product, int id_product_details) {
		return "UPDATE product_details SET "+columnName+" = '"+value+"' WHERE Product = '"+product+"' AND id_product_details = '"+id_product_details+"'";
	}
	
	private static ConnectionPool connectionPool;
    private Connection connection;
    String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet firstResults, secondResults, results;

}
