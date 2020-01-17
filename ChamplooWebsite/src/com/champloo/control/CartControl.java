package com.champloo.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.lookup.ImplicitNullAnnotationVerifier;

import com.champloo.bean.CartBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.bean.UserBean;
import com.champloo.model.CartDAO;
import com.mysql.cj.Query;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory.Zephyr;

import javafx.util.Pair;

@WebServlet("/Cart")
public class CartControl extends HttpServlet {
	
	public CartControl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String operation = request.getParameter("operation");
		
		if(operation != null)
		{
			if (operation.equals("createCart")) 
			{
				UserBean user = (UserBean)request.getAttribute("utenteLoggato");
				
				try {
					if(cartDAO.createCart(user))
					{
						CartBean cart = cartDAO.retrieveCart(user);
						request.setAttribute("cart", cart);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(operation.equals("insertProduct"))
			{
				int id_product_details = Integer.parseInt(request.getParameter("id_product_request"));
				
				HttpSession session = request.getSession(true);
				
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
				
			}
		}
	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
    
	 private static final long serialVersionUID = 1L;
	 private static CartDAO cartDAO = new CartDAO();
	
}
