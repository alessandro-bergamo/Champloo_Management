package com.champloo.control;


import com.champloo.bean.OrderBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.UserBean;
import com.champloo.model.CartDAO;
import com.champloo.model.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;




@WebServlet("/Order")
public class OrderControl extends HttpServlet
{

    public OrderControl()
    {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String operation = request.getParameter("operation");

        if(operation != null)
        {
            if(operation.equals("insertOrder"))
            {
                UserBean user = (UserBean) request.getSession().getAttribute("user");
                CartDAO cart = (CartDAO) request.getSession().getAttribute("cart");

                int id_user = user.getID();

                ArrayList<ProductBean> products = null; //AGGIUNGERE IL RETRIEVE DEI PRODOTTI IN CARRELLO

                String address = request.getParameter("address");
                String payment_method = request.getParameter("payment_method");
                String order_owner = user.getFirstName()+user.getSurname();
                Float order_price = Float.parseFloat(request.getParameter("order_price"));

                //SYSTEM DATE ON ORDER CREATION
                Date creation_date = new Date(System.currentTimeMillis());

                //DELIVERY DATE OF THE ORDER
                Date delivery_date = new Date(System.currentTimeMillis() + 4 * 24 * 60 * 60 * 1000);

                OrderBean newOrder = new OrderBean();

                newOrder.setId_order(1);        //USELESS
                newOrder.setAddress(address);
                newOrder.setPayment_method(payment_method);
                newOrder.setRegistred_User(id_user);
                newOrder.setOrder_owner(order_owner);
                newOrder.setTotal_price(order_price);
                newOrder.setCreation_date(creation_date);
                newOrder.setDelivery_date(delivery_date);
                newOrder.setStatus_order(0);

                model_order.createOrder(newOrder, products);
            }
            else if(operation.equals("cancelOrder"))
            {
                int id_order = Integer.parseInt(request.getParameter("id_order"));
                model_order.cancelOrder(model_order.retrieveByID(id_order));
            }
        }


        if(operation.equals("cancelOrder"))
            response.sendRedirect("user_area.jsp");
        else
            response.sendRedirect("pagina bianca.html");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }



    private static final long serialVersionUID = 1L;
    private static OrderDAO model_order = new OrderDAO();

}
