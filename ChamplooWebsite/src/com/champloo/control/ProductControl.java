package com.champloo.control;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.model.ProductDAO;

@WebServlet("/Product")
public class ProductControl extends HttpServlet{
	
	public ProductControl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String operation = request.getParameter("operation");
		
		if(operation != null)
		{
			if(operation.equals("addProduct"))
			{
				String name_product = request.getParameter("name_product");
				String brand_product = request.getParameter("brand_product");
				String model_product = request.getParameter("model_product");
				String type_product = request.getParameter("type_product");
				String description_product = request.getParameter("description_product");
				
				ProductBean product = new ProductBean();
				
				product.setName(name_product);
				product.setBrand(brand_product);
				product.setModel(model_product);
				product.setType(type_product);
				product.setDescription(description_product);

				String color = request.getParameter("color_product");
				String size = request.getParameter("size_product");
				float price = Float.parseFloat(request.getParameter("price_product"));
				int discount_percent = Integer.parseInt(request.getParameter("discount_percent_product"));
				float discounted_price = Float.parseFloat(request.getParameter("discounted_price_product"));
				int qnt_stock = Integer.parseInt(request.getParameter("qnt_stock_product"));
				int status = Integer.parseInt(request.getParameter("status_product"));
				// average_rating = 0.0
				//number_feedback_users = 0
				String img_path_folder = request.getParameter("img_path_folder_product");
				
				ProductDetailsBean productDetails = new ProductDetailsBean();
				
				productDetails.setColor(color);
				productDetails.setSize(size);
				productDetails.setPrice(price);
				productDetails.setDiscount_percent(discount_percent);
				productDetails.setDiscounted_price(discounted_price);
				productDetails.setQnt_stock(qnt_stock);
				productDetails.setStatus(status);
				productDetails.setAverage_rating(0);
				productDetails.setNumber_feedback_users(0);
				productDetails.setImg_path_folder(img_path_folder);
				
				try {
					productDAO.addProduct(product, productDetails);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(operation.equals("retrieveById"))
			{
				int idProduct = Integer.parseInt(request.getParameter("id_product"));
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				try {
					products = productDAO.retrieveById(idProduct);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("productsByModel", products);
			}
			else if(operation.equals("retrieveByModel"))
			{
				String model_product = request.getParameter("model_product");
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				try {
					products = productDAO.retrieveByModel(model_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("productsByModel", products);
			}
			else if(operation.equals("retrieveByCategory"))
			{
				String type_product = request.getParameter("type_product");
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				try {
					products = productDAO.retrieveByCategory(type_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("productsByCategory", products);
			}
			else if(operation.equals("retrieveByBrand"))
			{
				String brand_product = request.getParameter("brand_product");
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				try {
					products = productDAO.retrieveByModel(brand_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("productsByBrand", products);
			}
			else if(operation.equals("retrieveByColor"))
			{
				String color_product = request.getParameter("color_product");
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				try {
					products = productDAO.retrieveByColor(color_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("productsByColor", products);
			}
			else if(operation.equals("retrieveBySize"))
			{
				String size_product = request.getParameter("size_product");
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				try {
					products = productDAO.retrieveByModel(size_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("productsBySize", products);
			}
			else if(operation.equals("retrieveByStatus"))
			{
				String status_product = request.getParameter("status_product");
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				try {
					products = productDAO.retrieveByModel(status_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("productsByStatus", products);
			}
			else if(operation.equals("retrieveAll"))
			{
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();			

				try {
					products = productDAO.retrieveAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("AllProducts", products);
			}
			else if(operation.equals("deleteProduct"))
			{
				int id_product = Integer.parseInt(request.getParameter("id_product"));
				
				try {
					productDAO.deleteProduct(id_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(operation.equals("updateProduct"))
			{
				String name_product = request.getParameter("name_product");
				String brand_product = request.getParameter("brand_product");
				String model_product = request.getParameter("model_product");
				String type_product = request.getParameter("type_product");
				String description_product = request.getParameter("description_product");
				
				ProductBean newProduct = new ProductBean();
				
				newProduct.setName(name_product);
				newProduct.setBrand(brand_product);
				newProduct.setModel(model_product);
				newProduct.setType(type_product);
				newProduct.setDescription(description_product);

				String color = request.getParameter("color_product");
				String size = request.getParameter("size_product");
				float price = Float.parseFloat(request.getParameter("price_product"));
				int discount_percent = Integer.parseInt(request.getParameter("discount_percent_product"));
				float discounted_price = Float.parseFloat(request.getParameter("discounted_price_product"));
				int qnt_stock = Integer.parseInt(request.getParameter("qnt_stock_product"));
				int status = Integer.parseInt(request.getParameter("status_product"));
				// average_rating invariato
				//number_feedback_users invariato
				String img_path_folder = request.getParameter("img_path_folder_product");
				
				ProductDetailsBean newProductDetails = new ProductDetailsBean();
				
				newProductDetails.setColor(color);
				newProductDetails.setSize(size);
				newProductDetails.setPrice(price);
				newProductDetails.setDiscount_percent(discount_percent);
				newProductDetails.setDiscounted_price(discounted_price);
				newProductDetails.setQnt_stock(qnt_stock);
				newProductDetails.setStatus(status);
				newProductDetails.setAverage_rating(0);
				newProductDetails.setNumber_feedback_users(0);
				newProductDetails.setImg_path_folder(img_path_folder);
				
				try {
					productDAO.updateProduct(newProduct, newProductDetails);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(operation.equals("addProduct"))
			response.sendRedirect("area_admin.jsp");
		if(operation.equals("deleteProduct"))
			response.sendRedirect("area_admin.jsp");
		if(operation.equals("updateProduct"))
			response.sendRedirect("area_admin.jsp");
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
    
    private static final long serialVersionUID = 1L;
    private static ProductDAO productDAO = new ProductDAO();
	
}
