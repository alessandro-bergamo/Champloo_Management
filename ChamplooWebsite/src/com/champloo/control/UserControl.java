package com.champloo.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.champloo.bean.UserBean;
import com.champloo.model.UserDAO;
import com.champloo.storage.ConnectionPool;

/**
 * Servlet implementation class UserControl
 */
@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
       
    public UserControl() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		
		if(operation != null) {
			if(operation.equals("registerUser")) {
				UserBean user = new UserBean();
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String firstName = request.getParameter("firstname");
				String lastName = request.getParameter("lastname");
				String password = request.getParameter("password");
				
				user.setUsername(username);
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setSurname(lastName);
				user.setPassword(password);
				user.setRegistration_date(new Date(System.currentTimeMillis()));
				user.setType(NORMAL_USER);
				//SET DEL TIPO DELLO USER DISCUTERNE
				
				userDAO.registerUser(user);
				response.sendRedirect("userArea.jsp");
				
			}
			
			
			if(operation.equals("updateUser")) {
				UserBean user = (UserBean) request.getSession().getAttribute("user");
				UserBean updatedUser = new UserBean();
				updatedUser.setFirstName(request.getParameter("firstname"));
				updatedUser.setSurname(request.getParameter("lastname"));
				updatedUser.setPassword(user.getPassword());
				updatedUser.setEmail(user.getEmail());
				updatedUser.setUsername(user.getUsername());
				userDAO.updateUser(updatedUser);
			}
			
			if(operation.equals("deleteUser")) {
				UserBean user = (UserBean) request.getSession().getAttribute("user");
				userDAO.deleteUser(user);
				response.sendRedirect("userArea.jsp");
			}
			
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	private static final long serialVersionUID = 1L;
	private static UserDAO userDAO = new UserDAO();
	private static final int NORMAL_USER = 1;
	private static final int USERS_ADMIN = 2;
	private static final int ORDERS_ADMIN = 3;
	private static final int PRODUCTS_ADMIN = 4;
	// DISCUTERNE CON ALESSANDRO private static final int BANNED_USER = ?

}
