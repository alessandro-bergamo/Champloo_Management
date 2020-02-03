package com.champloo.control;

import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.model.ProductDAO;
import com.champloo.util.ActiveCart;

import javafx.util.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

@WebServlet("/Product")
public class ProductControl extends HttpServlet{
	
	public ProductControl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String operation = request.getParameter("operation");
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		
		if(operation != null)
		{
			if(operation.equals("addProduct"))
			{
				String name_product = request.getParameter("name_product");
				String brand_product = request.getParameter("brand_product");
				String model_product = request.getParameter("model_product");
				String type_product = request.getParameter("type_product");
				float price = Float.parseFloat(request.getParameter("price_product"));
				int status = Integer.parseInt(request.getParameter("status_product"));
				//total_ratign = 0
				// average_rating = 0.0
				//number_feedback_users = 0
				String description_product = request.getParameter("description_product");
				
				ProductBean product = new ProductBean();
				
				product.setName(name_product);
				product.setBrand(brand_product);
				product.setModel(model_product);
				product.setType(type_product);
				product.setPrice(price);
				product.setStatus(status);
				product.setTotal_rating(0);
				product.setAverage_rating(0);
				product.setNumber_feedback_users(0);
				product.setDescription(description_product);

				String color = request.getParameter("color_product");
				String size = request.getParameter("size_product");
				int discount_percent = Integer.parseInt(request.getParameter("discount_percent_product"));
				int qnt_stock = Integer.parseInt(request.getParameter("qnt_stock_product"));
				String img_path_folder = request.getParameter("img_path_folder_product");
				
				ProductDetailsBean productDetails = new ProductDetailsBean();
				
				productDetails.setColor(color);
				productDetails.setSize(size);
				productDetails.setDiscount_percent(discount_percent);
				productDetails.setQnt_stock(qnt_stock);
				productDetails.setImg_path_folder(img_path_folder);
				
				try {
					productDAO.addProduct(product, productDetails);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(operation.equals("createWindow"))
			{
				ArrayList<Pair<ProductBean, ProductDetailsBean>> window = new ArrayList<Pair<ProductBean,ProductDetailsBean>>();
				try {
					window = productDAO.createWindow();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				session.setAttribute("window", window);
				session.setAttribute("redirectURL", "index.jsp");
				
				dispatcher = request.getRequestDispatcher("Redirect");
				dispatcher.forward(request, response);
			}
			else if(operation.equals("showProduct"))
			{
				int idProduct = Integer.parseInt(request.getParameter("id_product"));
				//int idProductDetails = Integer.parseInt(request.getParameter("id_product_details"));
				String color = request.getParameter("color");
				
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> product = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				ProductBean productBean = new ProductBean();
				ArrayList<ProductDetailsBean> productDetails = new ArrayList<ProductDetailsBean>();
				ArrayList<ProductDetailsBean> selectedProductsByColor = new ArrayList<ProductDetailsBean>();
				
				try {
					product = productDAO.retrieveById(idProduct);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HashMap.Entry<ProductBean, ArrayList<ProductDetailsBean>> entry;
				entry = product.entrySet().iterator().next();
				productBean = entry.getKey();
				productDetails = entry.getValue();
				
				// selezione dei prodotti in base al colore passato dall'index
				for(int i = 0; i < productDetails.size(); i++)
				{
					if(productDetails.get(i).getColor().equals(color))
						selectedProductsByColor.add(productDetails.get(i));
				}
				
				session.setAttribute("product", product);
				session.setAttribute("selectedProductsByColor", selectedProductsByColor);
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
				String type_product = request.getParameter("category");
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				try {
					products = productDAO.retrieveByCategory(type_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				session.setAttribute("category", type_product);
				session.setAttribute("productsByCategory", products);
				session.setAttribute("redirectURL", "category.jsp");

				dispatcher = request.getRequestDispatcher("Redirect");
				dispatcher.forward(request, response);
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
				synchronized(session)
				{
					String status_product = request.getParameter("status_product");
					HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();

					try
					{
						products = productDAO.retrieveByStatus(status_product);
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					session.setAttribute("productsByStatus", products);
					session.setAttribute("redirectURL", "index.jsp");

					dispatcher = request.getRequestDispatcher("Redirect");
					dispatcher.forward(request, response);
				}
			}
			else if(operation.equals("retrieveByFeedbacks"))
			{
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();

				try {
					products = productDAO.retrieveByFeedbacks();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				session.setAttribute("category", "Scelte Popolari - Champloo");
				session.setAttribute("productsByCategory", products);
				session.setAttribute("redirectURL", "category.jsp");

				dispatcher = request.getRequestDispatcher("Redirect");
				dispatcher.forward(request, response);
			}
			else if(operation.equals("retrieveByAverage"))
			{
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();

				try {
					products = productDAO.retrieveByAverage();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				session.setAttribute("category", "I più amati - Champloo");
				session.setAttribute("productsByCategory", products);
				session.setAttribute("redirectURL", "category.jsp");

				dispatcher = request.getRequestDispatcher("Redirect");
				dispatcher.forward(request, response);
			}
			else if(operation.equals("retrieveNewProducts"))
			{
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();

				try {
					products = productDAO.retrieveNewProducts();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				session.setAttribute("category", "Nuovi Arrivi - Champloo");
				session.setAttribute("productsByCategory", products);
				session.setAttribute("redirectURL", "category.jsp");

				dispatcher = request.getRequestDispatcher("Redirect");
				dispatcher.forward(request, response);
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

				session.setAttribute("category", "Catalogo Prodotti");
				session.setAttribute("productsByCategory", products);
				session.setAttribute("redirectURL", "category.jsp");

				dispatcher = request.getRequestDispatcher("Redirect");
				dispatcher.forward(request, response);
			}
			else if(operation.equals("deleteProduct"))
			{
				int id_product = Integer.parseInt(request.getParameter("id_product"));

				System.out.println("ID: "+id_product);

				try {
					productDAO.deleteProduct(id_product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				session.removeAttribute("products");

				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();

				try {
					products = productDAO.retrieveAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				session.setAttribute("products", products);
			}
			else if(operation.equals("modifyProduct"))
			{

			}
			else if(operation.equals("updateProduct"))
			{
				String name_product = request.getParameter("name_product");
				String brand_product = request.getParameter("brand_product");
				String model_product = request.getParameter("model_product");
				String type_product = request.getParameter("type_product");
				float price = Float.parseFloat(request.getParameter("price_product"));
				int status = Integer.parseInt(request.getParameter("status_product"));
				String description_product = request.getParameter("description_product");
					
				ProductBean newProduct = new ProductBean();
				
				newProduct.setName(name_product);
				newProduct.setBrand(brand_product);
				newProduct.setModel(model_product);
				newProduct.setType(type_product);
				newProduct.setPrice(price);
				newProduct.setStatus(status);
				newProduct.setDescription(description_product);

				String color = request.getParameter("color_product");
				String size = request.getParameter("size_product");
				int discount_percent = Integer.parseInt(request.getParameter("discount_percent_product"));			
				int qnt_stock = Integer.parseInt(request.getParameter("qnt_stock_product"));
				String img_path_folder = request.getParameter("img_path_folder_product");
				
				ProductDetailsBean newProductDetails = new ProductDetailsBean();
				
				newProductDetails.setColor(color);
				newProductDetails.setSize(size);
				newProductDetails.setDiscount_percent(discount_percent);
				newProductDetails.setQnt_stock(qnt_stock);
				newProductDetails.setImg_path_folder(img_path_folder);
				
				try {
					productDAO.updateProduct(newProduct, newProductDetails);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(operation.equals("productManager"))
			{
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();

				try {
					products = productDAO.retrieveAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				session.setAttribute("products", products);
			}
			else if(operation.equals("insertProduct"))
			{
				int id_product = (int) request.getAttribute("id_product");
				int id_product_details = (int) request.getAttribute("id_product_details");
				ActiveCart activeCart = (ActiveCart) request.getAttribute("activeCart");
				
				try {
					Pair<ProductBean, ProductDetailsBean> product = productDAO.retrieveProductWithDetails(id_product, id_product_details);
					activeCart.insertProduct(product.getKey(), product.getValue());
					session.setAttribute("products_in_activeCart", activeCart.getHaspMap());
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}

		if(operation.equals("showProduct"))
			response.sendRedirect("product.jsp");
		else if(operation.equals("addProduct"))
			response.sendRedirect("area_admin.jsp");
		else if(operation.equals("deleteProduct"))
			response.sendRedirect("area_admin.jsp");
		else if(operation.equals("updateProduct"))
			response.sendRedirect("area_admin.jsp");
		else if(operation.equals("productManager"))
			response.sendRedirect("area_admin.jsp");
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
    
    private static final long serialVersionUID = 1L;
    private static ProductDAO productDAO = new ProductDAO();
	
}
