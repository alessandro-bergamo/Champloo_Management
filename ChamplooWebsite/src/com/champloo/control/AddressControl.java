package com.champloo.control;

import com.champloo.bean.AddressBean;
import com.champloo.bean.UserBean;
import com.champloo.model.AddressDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;



@WebServlet("/Address")
public class AddressControl extends HttpServlet {

    public AddressControl()
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String operation = request.getParameter("operation");
        RequestDispatcher dispatcher;
    	HttpSession session = request.getSession(true);


        if (operation.equals("insert"))
        {
            AddressBean newAddress = new AddressBean();
            ArrayList<AddressBean> addresses = null;

            UserBean user = (UserBean) session.getAttribute("utenteLoggato");
            try {
                newAddress.setId_address(1); //USELESS
                newAddress.setRegistred_User(user.getID());
                newAddress.setAddress(request.getParameter("address"));
                newAddress.setCity(request.getParameter("city"));
                newAddress.setProvince(request.getParameter("province"));
                newAddress.setCivic_number(Integer.parseInt(request.getParameter("civic_number")));
                newAddress.setCAP(Integer.parseInt(request.getParameter("cap")));

                model_address.insertAddress(newAddress);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try
            {
                session.removeAttribute("addresses");
                addresses = model_address.retrieveByUserID(user.getID());
                session.setAttribute("addresses", addresses);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }

        } else if (operation.equals("deleteAddress"))
        {
            try {
                model_address.deleteAddress(Integer.parseInt(request.getParameter("id_address")));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ArrayList<AddressBean> addresses = null;
            UserBean user = (UserBean) session.getAttribute("utenteLoggato");

            try
            {
                session.removeAttribute("addresses");
                addresses = model_address.retrieveByUserID(user.getID());
                session.setAttribute("addresses", addresses);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        } else if (operation.equals("login"))
        {
        	ArrayList<AddressBean> addresses = null;

        	synchronized(session) {
	            try {
	            	UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
	                addresses = model_address.retrieveByUserID(user.getID());
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
			}
            session.setAttribute("addresses", addresses);

            dispatcher = request.getRequestDispatcher("PaymentMethod");
            dispatcher.forward(request,response);
        } else if (operation.equals("submitCheckout"))
        {
            ArrayList<AddressBean> addresses = null;

            try
            {
                UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
                addresses = model_address.retrieveByUserID(user.getID());
            } catch (SQLException e)
            {
                e.printStackTrace();
            }

            session.setAttribute("addresses", addresses);

            dispatcher = request.getRequestDispatcher("PaymentMethod");
            dispatcher.forward(request, response);
        }


        if (operation.equals("insert") || operation.equals("delete"))
            response.sendRedirect("user_area.jsp");
        else if(operation.equals("updateUser"))
            response.sendRedirect("user_area.jsp");

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }



    private static AddressDAO model_address = new AddressDAO();
    private static final long serialVersionUID = 1L;

}
