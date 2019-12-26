package com.champloo.control;

import com.champloo.model.AddressDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Address")
public class AddressControl extends HttpServlet
{

    public AddressControl() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        RequestDispatcher dispatcher;
        if(action.equals(""))
            dispatcher = getServletContext().getRequestDispatcher("home.jsp");
        else
            dispatcher = getServletContext().getRequestDispatcher("home.jsp");

        dispatcher.forward(request, response);
    }


    protected void goPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }



    private static AddressDAO model_address = new AddressDAO();
    private static final long serialVersionUID = 1L;

}
