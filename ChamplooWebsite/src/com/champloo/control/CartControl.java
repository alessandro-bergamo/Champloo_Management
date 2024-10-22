package com.champloo.control;

import com.champloo.bean.*;
import com.champloo.model.CartDAO;
import com.champloo.model.ProductDAO;
import com.champloo.util.ActiveCart;
import com.sun.mail.handlers.image_gif;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;

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
		RequestDispatcher dispatcher;
		session = request.getSession(true);
		
		if(operation != null)
		{
			if(operation.equals("login"))
			{
				synchronized (session) {
					ActiveCart activeCart = (ActiveCart) session.getAttribute("activeCart");
					UserBean user = (UserBean) session.getAttribute("utenteLoggato");
					if(activeCart != null)
					{
						try {
							cartDAO.storeActiveCartInDb(activeCart, user.getID());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
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
				synchronized(session) {
					try {
						int id_product_details = Integer.parseInt(request.getParameter("id_product_details"));
						int id_product = Integer.parseInt(request.getParameter("id_product"));
						int status_product = Integer.parseInt(request.getParameter("status_product"));
						if(status_product == 6)
							response.setStatus(500);
						
						CartBean cart = (CartBean) session.getAttribute("cart");
						ActiveCart activeCart = (ActiveCart) session.getAttribute("activeCart");
						
						if(cart != null)
						{
								cartDAO.insertProduct(cart, id_product_details);
								session.removeAttribute("productsInCart");
								session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));	
						}
						else 
						{
							if (activeCart == null)
							{
								activeCart = new ActiveCart();
								session.setAttribute("activeCart", activeCart);
							}
							
							request.removeAttribute("operation");
							request.setAttribute("operation", "addToActiveCart");
							request.setAttribute("id_product_details", id_product_details);
							request.setAttribute("id_product", id_product);
							request.setAttribute("activeCart", activeCart);
							dispatcher = request.getRequestDispatcher("Product");
							dispatcher.forward(request, response);
						}
				
					} catch (Exception e) {
						response.setStatus(500);
						e.printStackTrace();
					}
				}
			}
			else if(operation.equals("modifyQuantity"))
			{
				int id_product = Integer.parseInt(request.getParameter("id_product"));
				int id_product_details = Integer.parseInt(request.getParameter("id_product_details"));
				
				int id_cart = 0;
				String checkCart = request.getParameter("id_cart");
				
				if(checkCart != null)
					id_cart = Integer.parseInt(checkCart);
				
				String operator = request.getParameter("operator");
				
				synchronized (session) {
					try {
						CartBean cart = (CartBean) session.getAttribute("cart");
						if (cart != null)
						{
							cartDAO.modifyQuantity(id_cart, id_product_details, operator);
							session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));
						}
						else
						{
								ActiveCart activeCart = (ActiveCart) session.getAttribute("activeCart");
								activeCart.modifyQuantity(id_product,id_product_details,operator);
								session.setAttribute("activeCart", activeCart);
								session.setAttribute("redirectURL", "cart.jsp");
								dispatcher = request.getRequestDispatcher("Redirect");
								dispatcher.forward(request, response);
						}								
					} catch (Exception e) {
						e.printStackTrace();
					}		
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
				HashMap<Pair<ProductBean,ProductDetailsBean>, Integer> productsInCart = new HashMap<Pair<ProductBean,ProductDetailsBean>, Integer>();
				ActiveCart activeCart;
				
				synchronized (session) {
					UserBean utenteLoggato = (UserBean) session.getAttribute("utenteLoggato");
					if (utenteLoggato != null)
					{
						CartBean cart = (CartBean) session.getAttribute("cart");
						try {
							productsInCart = cartDAO.retrieveProducts(cart);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						session.setAttribute("productsInCart", productsInCart);
						session.setAttribute("redirectURL", "cart.jsp");

						dispatcher = request.getRequestDispatcher("Redirect");
						dispatcher.forward(request, response);
					}
					else
					{
						activeCart = (ActiveCart) session.getAttribute("activeCart");
						
						if(activeCart == null)
						{
							activeCart = new ActiveCart();
							session.setAttribute("activeCart", activeCart);
							session.setAttribute("redirectURL", "cart.jsp");
							dispatcher = request.getRequestDispatcher("Redirect");
							dispatcher.forward(request, response);
						}
						else
						{
							activeCart = (ActiveCart) session.getAttribute("activeCart");
							session.setAttribute("activeCart", activeCart);
							session.setAttribute("redirectURL", "cart.jsp");
							dispatcher = request.getRequestDispatcher("Redirect");
							dispatcher.forward(request, response);
						}
					}					
				}				
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
				int id_product_details = Integer.parseInt(request.getParameter("id_product_details"));
				int id_product = Integer.parseInt(request.getParameter("id_product"));
				
				int id_cart = 0;
				String checkCart = request.getParameter("id_cart");
				
				if(checkCart != null)
					id_cart = Integer.parseInt(checkCart);
				
				synchronized (session) {
					try {
						CartBean cart = (CartBean) session.getAttribute("cart");
						if (cart != null)
						{
							cartDAO.deleteProduct(id_cart, id_product_details);
							session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));
						}
						else
						{
							ActiveCart activeCart = (ActiveCart) session.getAttribute("activeCart");
							if (activeCart != null)
							{
								activeCart.removeProduct(id_product, id_product_details);
							}	
						}								
					} catch (Exception e) {
						e.printStackTrace();
					}		
				}				
			}
			else if(operation.equals("clearCart"))
			{
				synchronized (session) {
					try {
						CartBean cart = (CartBean) session.getAttribute("cart");
						if(cart != null)
						{	
							cartDAO.clearCart(cart);
							session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));
							dispatcher = request.getRequestDispatcher("cart.jsp");
							dispatcher.forward(request, response);
						}
						else 
						{
							ActiveCart activeCart = (ActiveCart) session.getAttribute("activeCart");
							if(activeCart != null)
							{
								activeCart.clearCart();
								session.setAttribute("activeCart", activeCart);
								dispatcher = request.getRequestDispatcher("cart.jsp");
								dispatcher.forward(request, response);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}		
				}	
			}
			else if(operation.equals("submitCheckout"))
			{
				// controllare se l'ordine � effettuato su un ActiveCart (utente non loggato) 
				ActiveCart activeCart = (ActiveCart) session.getAttribute("activeCart");
				UserBean user = (UserBean) session.getAttribute("utenteLoggato");
				if(user == null)
				{
					response.setStatus(500);
				}
				else 
				{
					Float total_price_order, shipping_price, total_price;
					total_price = Float.parseFloat(request.getParameter("total_price"));
					shipping_price = Float.parseFloat(request.getParameter("shipping_price"));
					total_price_order = Float.parseFloat(request.getParameter("total_price_order"));

					session.setAttribute("total_price", total_price);
					session.setAttribute("shipping_price", shipping_price);
					session.setAttribute("total_price_order", total_price_order);
					
					response.setStatus(200);
				}
			}
			else if(operation.equals("createOrder"))
			{
				CartBean cart = (CartBean) session.getAttribute("cart");

				try
				{
					cartDAO.clearCart(cart);
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}

			if(operation.equals("login"))
				response.sendRedirect("index.jsp");
			else if(operation.equals("createOrder"))
				response.sendRedirect("user_area.jsp");
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