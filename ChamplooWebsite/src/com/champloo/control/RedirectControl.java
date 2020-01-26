package com.champloo.control;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Redirect")
public class RedirectControl extends HttpServlet
{

    public RedirectControl(){ super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String redirectURL = (String) request.getSession().getAttribute("redirectURL");

        System.out.println(redirectURL);

        response.sendRedirect(redirectURL);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}



