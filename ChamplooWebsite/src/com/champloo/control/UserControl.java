package com.champloo.control;

import java.io.IOException;
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
	private static final long serialVersionUID = 1L;
       
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
				//SET DEL TIPO DELLO USER DISCUTERNE
				
				ConnectionPool connectionPool = new ConnectionPool();
				if(connectionPool.getConnection() == null) {
					try {
						connectionPool.setConnection(connectionPool.InitializeConnection());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				UserDAO userDAO = new UserDAO(connectionPool.getConnection());
				userDAO.registerUser(user);
			}
			
			if(operation.equals("getAllUsers")) {
				ConnectionPool connectionPool = new ConnectionPool();
				if(connectionPool.getConnection() == null) {
					try {
						connectionPool.setConnection(connectionPool.InitializeConnection());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				//DA IMPLEMENTARE O RIFARE
				UserDAO userDAO = new UserDAO(connectionPool.getConnection());
				userDAO.getAllUsers();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
