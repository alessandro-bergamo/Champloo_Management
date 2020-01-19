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
import javax.servlet.http.HttpSession;

import com.champloo.bean.CartBean;
import com.champloo.bean.CartItemBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.bean.UserBean;
import com.champloo.model.CartDAO;

@WebServlet("/Cart")
public class CartControl extends HttpServlet {
	
	public CartControl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String operation = request.getParameter("operation");
		session = request.getSession(true);
		
		if(operation != null)
		{
			if (operation.equals("createCart")) 
			{
				UserBean user = (UserBean)request.getAttribute("utenteLoggato");
				
				try {
					if(cartDAO.createCart(user))
					{
						CartBean cart = cartDAO.retrieveCart(user);
						
						synchronized(session) {
							session.setAttribute("cart", cart);				
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(operation.equals("insertProduct"))
			{
				int id_product_details = Integer.parseInt(request.getParameter("id_product_request"));
				
				synchronized(session) {
					CartBean cart = (CartBean) session.getAttribute("cart");
					try {
						cartDAO.insertProduct(cart, id_product_details);
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
				HashMap<ProductBean, ArrayList<ProductDetailsBean>> productsInCart = new HashMap<ProductBean, ArrayList<ProductDetailsBean>>();
				
				synchronized (session) {
					CartBean cart = (CartBean) session.getAttribute("cart");
					
					try {
						productsInCart = cartDAO.retrieveProducts(cart);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				request.setAttribute("productsInCart", productsInCart);
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
			else if(operation.equals("retrieveCart"))
			{
				
				synchronized (session) {
					UserBean user = (UserBean)session.getAttribute("utenteLoggato");
					try {
						CartBean cart = cartDAO.retrieveCart(user);
						session.setAttribute("cart", cart);
					} catch (Exception e) {
						e.printStackTrace();
					}		
				}				
			}
			
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
