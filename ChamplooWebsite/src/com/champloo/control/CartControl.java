package com.champloo.control;

import com.champloo.bean.*;
import com.champloo.model.CartDAO;
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
		System.out.println("-----> RIGA 29 CARTCONTROL - OPERATION CARTCONTROL: "+operation);
		RequestDispatcher dispatcher;
		session = request.getSession(true);
		
		if(operation != null)
		{
			if(operation.equals("login"))
			{
				synchronized (session) {
					UserBean user = (UserBean) session.getAttribute("utenteLoggato");
					System.out.println("-----> RIGA 39 CARTCONTROL - UTENTE: "+user.getFirstName());
					try {
						CartBean cart = cartDAO.retrieveCart(user);
						System.out.println("-----> RIGA 42 CARTCONTROL - CART: "+cart);
						System.out.println("-----> RIGA 42 CARTCONTROL - CART: "+cart.getId_cart());
						session.setAttribute("cart", cart);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			else if(operation.equals("insertProduct"))
			{		
				synchronized(session) {
					int id_product_details = Integer.parseInt(request.getParameter("id_product_details"));
					int id_product = Integer.parseInt(request.getParameter("id_parameter"));
					CartBean cart = (CartBean) session.getAttribute("cart");
					ActiveCart activeCart = (ActiveCart) session.getAttribute("activeCart");
					if(cart != null)
					{
						try {
							cartDAO.insertProduct(cart, id_product_details);
							session.removeAttribute("productsInCart");
							session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));
						} catch (SQLException e) {	
							e.printStackTrace();
						}
					}
					else if (activeCart != null)
					{
						request.removeAttribute("operation");
						request.setAttribute("operation", "addToActiveCart");
						request.setAttribute("id_product_details", id_product_details);
						request.setAttribute("id_product", id_product);
						request.setAttribute("activeCart", activeCart);
						dispatcher = request.getRequestDispatcher("Product");
						dispatcher.forward(request, response);
						
						//activeCart.insertProduct(newProduct, newProductDetails);
					}
				}
			}
			else if(operation.equals("modifyQuantity"))
			{
				int id_cart = Integer.parseInt(request.getParameter("id_cart"));
				int id_product_details = Integer.parseInt(request.getParameter("id_product_details"));
				String operator = request.getParameter("operator");
				
				try {
					//CartItemBean cart_item = cartDAO.retrieveCartItem(id_cart_item);
					cartDAO.modifyQuantity(id_cart, id_product_details, operator);
						CartBean cart = (CartBean) session.getAttribute("cart");
						session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));
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
				int id_cart = Integer.parseInt(request.getParameter("id_cart"));
				
				synchronized (session) {
					try {
						cartDAO.deleteProduct(id_cart, id_product_details);
						CartBean cart = (CartBean) session.getAttribute("cart");
						session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));
						
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
						session.setAttribute("productsInCart", cartDAO.retrieveProducts(cart));
						dispatcher = request.getRequestDispatcher("cart.jsp");
						dispatcher.forward(request, response);
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
