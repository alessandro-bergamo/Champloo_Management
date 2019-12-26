package com.champloo.control;

import com.champloo.bean.PaymentMethodBean;
import com.champloo.model.PaymentMethodDAO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;



@WebServlet("/PaymentMethod")
public class PaymentMethodControl extends HttpServlet
{

    public PaymentMethodControl() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        String operation = request.getParameter("operation");

        if(operation.equals("insert"))
        {
            PaymentMethodBean payment_method = new PaymentMethodBean();

            try
            {
                payment_method.setId_method(Integer.parseInt(request.getParameter("id")));
                payment_method.setCard_cvc(Integer.parseInt(request.getParameter("cvc")));
                payment_method.setCard_number(request.getParameter("number"));
                payment_method.setCard_bank(request.getParameter("bank"));
                payment_method.setCard_owner(request.getParameter("owner"));
                payment_method.setUsername(request.getParameter("username"));
                payment_method.setExpiry_date(LocalDate.parse(request.getParameter("expiry")));
                payment_method.setRegistration_method_date(LocalDate.parse(request.getParameter("registration_date")));

                model_pmethod.insertPMethod(payment_method);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }


        if(operation.equals("insert"))
            response.sendRedirect("index.jsp");
        else
            response.sendRedirect("reso.jsp");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }



    private static PaymentMethodDAO model_pmethod = new PaymentMethodDAO();
    private static final long serialVersionUID = 1L;

}