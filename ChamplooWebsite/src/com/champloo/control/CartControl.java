package com.champloo.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.champloo.bean.CartBean;
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
