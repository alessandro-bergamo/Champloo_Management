package com.champloo.control;

import com.champloo.bean.*;
import com.champloo.model.CartDAO;
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
import java.util.HashMap;

@WebServlet("/Cart")
public class CartControl extends HttpServlet {
	
	public CartControl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String operation = request.getParameter("operation");
		System.out.println("OPERATION CARTCONTROL: "+operation);
		RequestDispatcher dispatcher;
		session = request.getSession(true);
		
		if(operation != null)
		{
			if(operation.equals("login"))
			{
				synchronized (session) {
					UserBean user = (UserBean) session.getAttribute("utenteLoggato");
					try {
						CartBean cart = cartDAO.retrieveCart(user);
						session.setAttribute("cart", cart);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			else if(operation.equals("insertProduct"))
			{
				int id_product_details = Integer.parseInt(request.getParameter("id_product_details"));
				
				synchronized(session) {
					CartBean cart = (CartBean) session.getAttribute("cart");
					try {
						cartDAO.insertProduct(cart, id_product_details);
						session.removeAttribute("productsInCart");
						session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));
					} catch (SQLException e) {	
						e.printStackTrace();
					}
				}
			}
			else if(operation.equals("modifyQuantity"))
			{
				int id_cart_item = Integer.parseInt(request.getParameter("id_cart_item"));
				int qnt = Integer.parseInt(request.getParameter("quantity"));
				
				try {
					CartItemBean cart_item = cartDAO.retrieveCartItem(id_cart_item);
					cartDAO.modifyQuantity(cart_item, qnt);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(operation.equals("retrieveNumberOfProducts"))
			{
				int numberOfProducts = 0;
				
				synchronized(session) {
					CartBean cart = (CartBean) session.getAttribute("cart");
					try {
						numberOfProducts = cartDAO.retrieveNumberOfProducts(cart);
					} catch (SQLException e) {	
						e.printStackTrace();
					}
				}
				request.setAttribute("numberOfProducts", numberOfProducts);
			}
			else if(operation.equals("retrieveProducts"))
			{
				System.out.println("RETRIEVE PRODUCTS");
				HashMap<Pair<ProductBean,ProductDetailsBean>, Integer> productsInCart = new HashMap<Pair<ProductBean,ProductDetailsBean>, Integer>();
				
				synchronized (session) {
					CartBean cart = (CartBean) session.getAttribute("cart");
					try {
						productsInCart = cartDAO.retrieveProducts(cart);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				session.setAttribute("productsInCart", productsInCart);
				session.setAttribute("redirectURL", "cart.jsp");

				dispatcher = request.getRequestDispatcher("Redirect");
				dispatcher.forward(request, response);
			}
			else if(operation.equals("retrieveTotal"))
			{
				float total = 0;
				
				synchronized (session) {
					CartBean cart = (CartBean) session.getAttribute("cart");
					
					try {
						total = cartDAO.retrieveTotal(cart);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				request.setAttribute("total", total);
			}
			else if(operation.equals("deleteProduct"))
			{
				int id_product_details = Integer.parseInt(request.getParameter("id_products_details"));
				
				synchronized (session) {
					CartBean cart = (CartBean) session.getAttribute("cart");
					
					try {
						cartDAO.deleteProduct(cart, id_product_details);
					} catch (Exception e) {
						e.printStackTrace();
					}		
				}				
			}
			else if(operation.equals("clearCart"))
			{
				synchronized (session) {
					CartBean cart = (CartBean) session.getAttribute("cart");
					
					try {
						cartDAO.clearCart(cart);
					} catch (Exception e) {
						e.printStackTrace();
					}		
				}				
			}
			else if(operation.equals("submitCheckout"))
			{
				Float total_price_order, shipping_price, total_price;
				total_price = Float.parseFloat(request.getParameter("total_price"));
				shipping_price = Float.parseFloat(request.getParameter("shipping_price"));
				total_price_order = Float.parseFloat(request.getParameter("total_price_order"));

				session.setAttribute("total_price", total_price);
				session.setAttribute("shipping_price", shipping_price);
				session.setAttribute("total_price_order", total_price_order);
			}


			if(operation.equals("login"))
				response.sendRedirect("index.jsp");
		}
	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }



	 private static final long serialVersionUID = 1L;
	 private static CartDAO cartDAO = new CartDAO();
	 private HttpSession session;
	 
}
