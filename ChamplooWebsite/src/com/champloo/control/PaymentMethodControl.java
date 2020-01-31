package com.champloo.control;

import com.champloo.bean.PaymentMethodBean;
import com.champloo.bean.UserBean;
import com.champloo.model.PaymentMethodDAO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;


@WebServlet("/PaymentMethod")
public class PaymentMethodControl extends HttpServlet
{

    public PaymentMethodControl() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String operation = request.getParameter("operation");
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(true);

        if(operation.equals("insert"))
        {
            PaymentMethodBean payment_method = new PaymentMethodBean();

            UserBean user = (UserBean) session.getAttribute("utenteLoggato");

            try
            {
                payment_method.setId_method(1);  //USELESS
                payment_method.setCard_cvc(Integer.parseInt(request.getParameter("cvc")));
                payment_method.setCard_number(request.getParameter("number"));
                payment_method.setCard_bank(request.getParameter("bank"));
                payment_method.setCard_owner(request.getParameter("owner"));
                payment_method.setRegistred_User(user.getID());
                payment_method.setExpiry_date(LocalDate.parse(request.getParameter("expiry")));
                payment_method.setRegistration_method_date(LocalDate.parse(request.getParameter("registration_date")));

                model_pmethod.insertPMethod(payment_method);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        } else if(operation.equals("delete"))
        {
            try {
                UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
                model_pmethod.deletePMethod(user.getID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(operation.equals("submitCheckout"))
        {
            ArrayList<PaymentMethodBean> methods = null;

            try {
                UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
                methods = model_pmethod.retrieveByUserID(user.getID());
            } catch(SQLException e) {
                e.printStackTrace();
            }

            session.setAttribute("methods", methods);
        } else if(operation.equals("login"))
        {
            ArrayList<PaymentMethodBean> methods = new ArrayList<PaymentMethodBean>();

            synchronized (session) {
                try {
                    UserBean user = (UserBean) request.getSession().getAttribute("utenteLoggato");
                    methods = model_pmethod.retrieveByUserID(user.getID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                session.setAttribute("methods", methods);

                dispatcher = request.getRequestDispatcher("Cart");
                dispatcher.forward(request,response);
            }
        }


        if(operation.equals("insert") || operation.equals("delete"))
            response.sendRedirect("user_area.jsp");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }



    private static PaymentMethodDAO model_pmethod = new PaymentMethodDAO();
    private static final long serialVersionUID = 1L;

}