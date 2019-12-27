package com.champloo.control;

import com.champloo.bean.PaymentMethodBean;
import com.champloo.model.PaymentMethodDAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;


@WebServlet("/PaymentMethod")
public class PaymentMethodControl extends HttpServlet
{

    public PaymentMethodControl() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String operation = request.getParameter("operation");

        if(operation.equals("insert"))
        {
            PaymentMethodBean payment_method = new PaymentMethodBean();

            try
            {
                payment_method.setId_method(1);
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
        } else if(operation.equals("delete"))
        {
            try {
                model_pmethod.deletePMethod(Integer.parseInt(request.getParameter("id")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(operation.equals("retrieveMethods"))
        {
            HashSet<PaymentMethodBean> pMethods = null;
            try {
                pMethods = model_pmethod.retrieveByUserID(Integer.parseInt(request.getParameter("id_user")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("methods", pMethods);
        }


        if(operation.equals("insert") || operation.equals("delete"))
            response.sendRedirect("user_area.jsp");
        else
            response.sendRedirect("index.jsp");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }



    private static PaymentMethodDAO model_pmethod = new PaymentMethodDAO();
    private static final long serialVersionUID = 1L;

}