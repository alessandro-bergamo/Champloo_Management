 package com.champloo.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.champloo.bean.UserBean;
import com.champloo.model.UserDAO;

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
		
		/*
		 * Stringa che identifica la pagina dove reindirizzare l'utente a seconda dell'esito del login
		 * */
		String redirectedPage = new String();
		
		if(operation != null) {
			
			if(operation.equals("login")) 
			{
				request.removeAttribute("login");
				
				String user_email = request.getParameter("email");
				String user_password = request.getParameter("password");
			
				HttpSession session = request.getSession(true);
				synchronized(session) {
					
					if(userDAO.login(user_email, user_password)) {
						session.setAttribute("utenteLoggato", userDAO.getUserByEmail(user_email));
						session.setAttribute("email", user_email);
						request.setAttribute("login", true);
						RequestDispatcher rd = request.getRequestDispatcher("Address");
						rd.forward(request,response);
					} else {
						request.setAttribute("login", false);
						redirectedPage = "user_log.jsp";
					}
				}
				
			}
			else if(operation.equals("getUserByEmail"))
			{
				String userEmail = request.getParameter("email");
				UserBean user = new UserBean();
				user = userDAO.getUserByEmail(userEmail);
				
				request.setAttribute("userById", user);
			}
			else if(operation.equals("getUserByUsername"))
			{
				String username = request.getParameter("username");
				UserBean user = new UserBean();
				user = userDAO.getUserByUsername(username);
				
				request.setAttribute("userByUsername", user);
			}
			else if(operation.equals("getAllUsers"))
			{
				ArrayList<UserBean> allUsers = new ArrayList<>();
				allUsers = userDAO.getAllUsers();
				
				request.setAttribute("allUsers", allUsers);
				
			}
			else if(operation.equals("registerUser")) 
			{
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
				user.setType(UserBean.NORMAL_USER);
				//SET DEL TIPO DELLO USER DISCUTERNE
				
				userDAO.registerUser(user);
				
			}
			else if(operation.equals("updateUser")) 
			{
				UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
				UserBean updatedUser = new UserBean();

				updatedUser.setFirstName(request.getParameter("firstname"));
				updatedUser.setSurname(request.getParameter("lastname"));
				updatedUser.setPassword(user.getPassword());
				updatedUser.setEmail(user.getEmail());
				updatedUser.setUsername(user.getUsername());

				userDAO.updateUser(updatedUser);

				RequestDispatcher rd = request.getRequestDispatcher("Address");
				rd.forward(request,response);
			}
			else if(operation.equals("deleteUser")) 
			{
				UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
				userDAO.deleteUser(user);				
			}
			else if(operation.equals("blockUser"))
			{
				String username = request.getParameter("username");
				userDAO.blockUser(username);
			}
		}
		

		
		if(operation.equals("registerUser"))
			response.sendRedirect("user_log.jsp");
		
		if(operation.equals("deleteUser"))
			response.sendRedirect("user_area.jsp");
		
		if(operation.equals("blockUSer"))
			response.sendRedirect("area_admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	private static final long serialVersionUID = 1L;
	private static UserDAO userDAO = new UserDAO();

}
