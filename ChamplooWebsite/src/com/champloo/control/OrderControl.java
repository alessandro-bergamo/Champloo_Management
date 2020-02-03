package com.champloo.control;

import com.champloo.bean.*;
import com.champloo.model.CartDAO;
import com.champloo.model.OrderDAO;
import javafx.util.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;


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
        System.out.println("OPERATION: "+operation);
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(true);

        if(operation != null)
        {
            if(operation.equals("createOrder"))
            {
                UserBean user = (UserBean) session.getAttribute("utenteLoggato");
                HashMap<Pair<ProductBean, ProductDetailsBean>, Integer> products_in_order= (HashMap<Pair<ProductBean, ProductDetailsBean>, Integer>) session.getAttribute("productsInCart");

                int id_user = user.getID();
                String order_owner = user.getFirstName()+" "+user.getSurname();

                String address = request.getParameter("address");
                String payment_method = request.getParameter("payment_method");
                Float order_price = (Float) session.getAttribute("total_price_order");

                //SYSTEM DATE ON ORDER CREATION
                Date creation_date = new Date(System.currentTimeMillis());

                //DELIVERY DATE OF THE ORDER
                Date delivery_date = new Date(System.currentTimeMillis() + 4 * 24 * 60 * 60 * 1000);

                OrderBean newOrder = new OrderBean();

                newOrder.setAddress(address);
                newOrder.setPayment_method(payment_method);
                newOrder.setRegistred_User(id_user);
                newOrder.setOrder_owner(order_owner);
                newOrder.setTotal_price(order_price);
                newOrder.setCreation_date(creation_date);
                newOrder.setDelivery_date(delivery_date);
                newOrder.setStatus_order(0);

                try
                {
                    model_order.createOrder(newOrder, products_in_order);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                session.removeAttribute("orders");

                LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();

                try {
                    orders = model_order.retrieveByUserID(user.getID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                session.setAttribute("orders", orders);

                dispatcher = request.getRequestDispatcher("Cart");
                dispatcher.forward(request, response);
            }
            else if(operation.equals("cancelOrder"))
            {
                int id_order = Integer.parseInt(request.getParameter("order_id"));
                Date cancel_date = new Date(System.currentTimeMillis());

                try {
                    model_order.modifyOrder(id_order, 5, cancel_date);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();
                try {
                    int order_id = Integer.parseInt(request.getParameter("order_id"));
                    orders = model_order.retrieveByOrder(order_id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("order", orders);
            } else if(operation.equals("modifyOrder"))
            {
                UserBean user = (UserBean) session.getAttribute("utenteLoggato");

                int id_order = Integer.parseInt(request.getParameter("order_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                Date delivery_date = Date.valueOf(request.getParameter("delivery_date"));

                System.out.println("ID: "+id_order+" STATUS: "+status+" DATA: "+delivery_date);

                try {
                    model_order.modifyOrder(id_order, status, delivery_date);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                session.removeAttribute("order");

                LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();
                try {
                    int order_id = Integer.parseInt(request.getParameter("order_id"));
                    orders = model_order.retrieveByOrder(order_id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("order", orders);
            } else if(operation.equals("showOrder"))
            {
                LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();
                try {
                    int order_id = Integer.parseInt(request.getParameter("order_id"));
                    orders = model_order.retrieveByOrder(order_id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("order", orders);
            } else if(operation.equals("showOrdersPerUser"))
            {
                LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orders = new LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>>();

                try {
                    UserBean user = (UserBean) session.getAttribute("utenteLoggato");
                    orders = model_order.retrieveByUserID(user.getID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                session.setAttribute("orders", orders);
            } else if(operation.equals("showOrdersPerDate"))
            {
                request.removeAttribute("orders");

                HashSet<OrderBean> orders = new HashSet<OrderBean>();

                Date start_date = Date.valueOf(request.getParameter("start_date"));
                Date end_date = Date.valueOf(request.getParameter("end_date"));

                try {
                    orders = model_order.retrieveByDate(start_date, end_date);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                request.setAttribute("orders", orders);
            } else if(operation.equals("ordersManager"))
            {
                request.removeAttribute("orders");

                HashMap<OrderBean, ArrayList<OrderItemBean>> orders = new HashMap<OrderBean, ArrayList<OrderItemBean>>();

                try {
                    orders = model_order.retrieveAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                session.setAttribute("orders", orders);
            } else if(operation.equals("showCanceledOrders"))
            {
                request.removeAttribute("orders");

                HashSet<OrderBean> orders = new HashSet<OrderBean>();

                try {
                    orders = model_order.retrieveCancelledOrders(3);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                request.setAttribute("orders", orders);
            }
        }

        if(operation.equals("ordersManager"))
            response.sendRedirect("area_admin.jsp");
        else if(operation.equals("showOrdersPerUser"))
            response.sendRedirect("user_area_orders.jsp");
        else if(operation.equals("showOrder"))
            response.sendRedirect("order.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    private static final long serialVersionUID = 1L;
    private static OrderDAO model_order = new OrderDAO();

}