package com.champloo.control;


import com.champloo.model.OrderDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Order")
public class OrderControl extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private static OrderDAO model_order = new OrderDAO();

    public OrderControl()
    {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if(action != null)
        {
            if(action.equals("cancelOrder"))
            {
                int id_order = Integer.parseInt(request.getParameter("id_order"));
                model_order.cancelOrder(model_order.retrieveByID(id_order));
            }
        }

        RequestDispatcher dispatcher;
        if(action.equals("cancelOrder"))
            dispatcher = getServletContext().getRequestDispatcher("/area-utente.jsp");
        else
            dispatcher = getServletContext().getRequestDispatcher("home.jsp");

        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
