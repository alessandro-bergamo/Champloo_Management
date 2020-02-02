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

                dispatcher = request.getRequestDispatcher("Cart");
                dispatcher.forward(request, response);
            }
            else if(operation.equals("cancelOrder"))
            {
                int id_order = Integer.parseInt(request.getParameter("id_order"));
                try {
                    model_order.cancelOrder(model_order.retrieveByID(id_order));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if(operation.equals("modifyOrder"))
            {
                UserBean user = (UserBean) request.getSession().getAttribute("user");

                int id_user = user.getID();

                String address = request.getParameter("address");
                String payment_method = request.getParameter("payment_method");
                String order_owner = user.getFirstName()+user.getSurname();
                Float order_price = Float.parseFloat(request.getParameter("order_price"));

                //SYSTEM DATE ON ORDER CREATION
                Date creation_date = new Date(System.currentTimeMillis());

                //DELIVERY DATE OF THE ORDER
                Date delivery_date = new Date(System.currentTimeMillis() + 4 * 24 * 60 * 60 * 1000);

                OrderBean order = new OrderBean();

                order.setId_order(1);        //USELESS
                order.setAddress(address);
                order.setPayment_method(payment_method);
                order.setRegistred_User(id_user);
                order.setOrder_owner(order_owner);
                order.setTotal_price(order_price);
                order.setCreation_date(creation_date);
                order.setDelivery_date(delivery_date);
                order.setStatus_order(0);

                try {
                    model_order.modifyOrder(order);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if(operation.equals("showOrder"))
            {
                request.removeAttribute("order");

                OrderBean order = new OrderBean();
                ArrayList<OrderItemBean> items_in_order = new ArrayList<OrderItemBean>();

                int id_order = Integer.parseInt(request.getParameter("id_order"));

                try {
                    order = model_order.retrieveByID(id_order);
                    items_in_order = model_order.retrieveByOrder(id_order);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                request.setAttribute("order", order);
                request.setAttribute("items_in_order", items_in_order);
            } else if(operation.equals("showOrdersPerUser"))
            {
                request.removeAttribute("orders");

                HashMap<Integer, ArrayList> item_in_order = new HashMap<Integer, ArrayList>();

                ArrayList<OrderBean> ordersByUser = new ArrayList<OrderBean>();
                ArrayList<OrderItemBean> items_in_order = new ArrayList<OrderItemBean>();

                int Registred_User = Integer.parseInt(request.getParameter("id_user"));

                try {
                    ordersByUser = model_order.retrieveByUserID(Registred_User);
                    for(int I=0; I<ordersByUser.size(); I++)
                    {
                        items_in_order = model_order.retrieveByOrder(ordersByUser.get(I).getId_order());
                        item_in_order.put(I, items_in_order);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                request.setAttribute("orders", ordersByUser);
                request.setAttribute("item_in_orders", item_in_order);
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

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    private static final long serialVersionUID = 1L;
    private static OrderDAO model_order = new OrderDAO();

}