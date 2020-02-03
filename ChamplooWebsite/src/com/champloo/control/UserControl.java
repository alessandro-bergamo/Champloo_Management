 package com.champloo.control;

import com.champloo.bean.UserBean;
import com.champloo.model.UserDAO;
import com.champloo.util.Mailer;
import com.champloo.util.PasswordGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
//mamaa
/**
 * Servlet implementation class UserControl
 */
@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
       
    public UserControl() { super();	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String operation = request.getParameter("operation");
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		
		/*
		 * Stringa che identifica la pagina dove reindirizzare l'utente a seconda dell'esito del login
		 * */
		String redirectedPage = new String();
		
		if(operation != null)
		{
			if(operation.equals("login")) 
			{
				request.removeAttribute("login");
				
				String user_email = request.getParameter("email");
				String user_password = request.getParameter("password");

				synchronized(session) {
					if(userDAO.login(user_email, user_password)) {
						session.setAttribute("utenteLoggato", userDAO.getUserByEmail(user_email));
						session.setAttribute("email", user_email);
						request.setAttribute("login", true);
						System.out.println("SONO QUI, RIGA 52 USERCONTROL");
						dispatcher = request.getRequestDispatcher("Address");
						dispatcher.forward(request,response);
					} else {
						System.out.println("Utente non loggato");
						response.setStatus(500);
					}
				}
			}
			else if(operation.equals("forgetPassword")) {
				String user_email = request.getParameter("email");
				UserBean userBean = userDAO.getUserByEmail(user_email);
				if(userBean.isEmpty()) {
					request.setAttribute("accreditate", false);
				} else {
					PasswordGenerator passwordGenerator = new PasswordGenerator();
					String nuovaPsw = passwordGenerator.generate(10);
					String newPassword = nuovaPsw;
					userBean.setPassword(newPassword);
					Mailer.send(user_email, "FORGET PASSWORD", "La tua nuova password � " + nuovaPsw + ". Puoi modificarla una volta effettuato l'accesso.");
					request.setAttribute("accreditate", true);
				}
			}
			else if(operation.equals("modifyPassword")) {
				String username = request.getParameter("username");
				System.out.println("USERNAME: "+username);
				UserBean user = userDAO.getUserByUsername(username);
				System.out.println("USER: "+user.getFirstName()+" PASSWORD: "+user.getPassword());
				String newPassword = request.getParameter("new_psw");
				boolean result = userDAO.changePassword(user, newPassword);
				request.setAttribute("result", result);
				session.invalidate();
			}
			else if(operation.equals("logout"))
			{
				synchronized(session)
				{
					request.setAttribute("result", true);
					session.invalidate();
					response.sendRedirect("index.jsp");
				}
			}
			else if(operation.equals("getUserByEmail"))
			{
				String userEmail = request.getParameter("email");
				UserBean user = new UserBean();
				user = userDAO.getUserByEmail(userEmail);
				
				request.setAttribute("userByEmail", user);
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
				synchronized(session)
				{
					ArrayList<UserBean> allUsers;
					allUsers = userDAO.getAllUsers();
					session.setAttribute("allUsers", allUsers);
					session.setAttribute("redirectURL", "area_admin.jsp");
					request.setAttribute("allUsers", allUsers); // for testing

					dispatcher = request.getRequestDispatcher("Redirect");
					dispatcher.forward(request, response);
				}
			}
			else if(operation.equals("registerUser")) {
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String firstName = request.getParameter("firstname");
				String lastName = request.getParameter("lastname");
				String password = request.getParameter("password");
				Mailer.send(email, "REGISTRAZIONE", "Clicca sul seguente link per completare la registrazione: http://localhost:8080/ChamplooWebsite_war_exploded/registration_validation.jsp?username="+ username + "&email=" + email + "&firstname=" + firstName + "&lastname=" + lastName + "&password=" + password +"");
				request.setAttribute("accreditate", true);
			}
			else if(operation.equals("validateUser")) 
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
				
				boolean result = userDAO.registerUser(user);
				request.setAttribute("accreditate", result);
				response.sendRedirect("user_log.jsp");
			}
			else if(operation.equals("updateUser")) 
			{
				UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
				session.removeAttribute("utenteLoggato");
				UserBean updatedUser = new UserBean();

				updatedUser.setFirstName(request.getParameter("firstname"));
				updatedUser.setSurname(request.getParameter("lastname"));
				updatedUser.setPassword(user.getPassword());
				updatedUser.setEmail(user.getEmail());
				updatedUser.setUsername(user.getUsername());

				boolean result = userDAO.updateUser(updatedUser);
				request.setAttribute("accreditate", result);
				session.setAttribute("utenteLoggato", updatedUser);
			}
			else if(operation.equals("deleteUser")) 
			{
				UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
				boolean result = userDAO.deleteUser(user);
				request.setAttribute("accreditate", result);
			}
			else if(operation.equals("userManager"))
			{
				ArrayList<UserBean> users = new ArrayList<UserBean>();

				users = userDAO.getAllUsers();

				session.setAttribute("users", users);
			}
			else if(operation.equals("blockUser"))
			{
				int user_id = Integer.parseInt(request.getParameter("user_id"));
				boolean result = userDAO.blockUser(user_id);
				request.setAttribute("accreditate", result);

				ArrayList<UserBean> users = new ArrayList<UserBean>();

				users = userDAO.getAllUsers();

				session.setAttribute("users", users);
			}
		}


		if(operation.equals("registerUser"))
			response.sendRedirect("user_log.jsp");
		else if(operation.equals("deleteUser"))
			response.sendRedirect("user_area.jsp");
		else if(operation.equals("blockUser"))
			response.sendRedirect("area_admin.jsp");
		else if(operation.equals("updateUser"))
			response.sendRedirect("user_area.jsp");
		else if(operation.equals("userManager"))
			response.sendRedirect("area_admin.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	private static final long serialVersionUID = 1L;
	private static UserDAO userDAO = new UserDAO();

}
