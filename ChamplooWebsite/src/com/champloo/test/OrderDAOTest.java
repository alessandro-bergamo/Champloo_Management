package com.champloo.test;

import com.champloo.bean.OrderBean;
import com.champloo.bean.OrderItemBean;
import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.model.OrderDAO;
import com.champloo.model.UserDAO;
import com.champloo.storage.ConnectionPool;
import javafx.util.Pair;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@RunWith(Parameterized.class)
public class OrderDAOTest {
    enum Type{CREATEORDER, MODIFYORDER, CANCELORDER, RETRIEVEBYID, RETRIEVEBYUSERID, RETRIEVEBYDATE, RETRIEVECANCELLEDORDERS, RETRIEVEALL, RETRIEVEBYORDER};
    private Type type;
    private Boolean expectedResult;
    private static OrderDAO orderDAO;
    private Object paramForDAO;
    static ConnectionPool connectionPool;
    static Connection connection;

    @BeforeClass
    public static void initialize() {
        orderDAO = new OrderDAO("");
        try {
            String alterSQLUsers = "ALTER TABLE registred_users AUTO_INCREMENT = 1";
            connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/testing_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
            connection = connectionPool.getConnection();
            Statement alterUsers = connection.createStatement();
            String alterSQLOrder = "ALTER TABLE orders AUTO_INCREMENT = 1";
            String alterSQLOrderItem = "ALTER TABLE order_item AUTO_INCREMENT = 1";
            String alterSQLProducts = "ALTER TABLE products AUTO_INCREMENT = 1";
            String alterSQLProductDetails = "ALTER TABLE product_details AUTO_INCREMENT = 1";
            Statement alterOrder = connection.createStatement();
            Statement alterOrderItem = connection.createStatement();
            Statement alterProducts = connection.createStatement();
            Statement alterProductDetails = connection.createStatement();
            alterUsers.execute(alterSQLUsers);
            alterOrder.execute(alterSQLOrder);
            alterOrderItem.execute(alterSQLOrderItem);
            alterProducts.execute(alterSQLProducts);
            alterProductDetails.execute(alterSQLProductDetails);
            PreparedStatement insertUser = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('David','Capuano','david98','davidecap00@hotmail.it','capucapu','2020-02-06','1');");
            PreparedStatement insertUser2 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Alex','Esposito','alexBab','alex98@hotmail.it','freepsw','2020-02-06','1');");
            PreparedStatement insertUser3 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Maria','Rossi','mRed','mrossi@hotmail.it','rossi46','2020-02-06','1');");
            PreparedStatement insertUser4 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Rosario','Crescenzo','rosCz','roscres@outlook.it','cres00','2020-02-06','1');");
            PreparedStatement insertOrder = connection.prepareStatement("INSERT INTO orders (Registred_User, shipping_address, payment_method, order_owner, total_price, creation_date, delivery_date, status_order) VALUES ('1', 'Via Piave Sarno', 'Carta', 'Antonio Mancuso', '90', '2020-02-06', '2020-02-16', '1');");
            PreparedStatement insertOrder2 = connection.prepareStatement("INSERT INTO orders (Registred_User, shipping_address, payment_method, order_owner, total_price, creation_date, delivery_date, status_order) VALUES ('2', 'Via Roma Napoli', 'Carta', 'David Capuano', '80', '2020-02-06', '2020-02-16', '1');");
            PreparedStatement insertOrder3 = connection.prepareStatement("INSERT INTO orders (Registred_User, shipping_address, payment_method, order_owner, total_price, creation_date, delivery_date, status_order) VALUES ('3', 'Via Manzoni Milano', 'Contrassegno', 'Luca Giugliano', '20', '2020-02-06', '2020-02-16', '1');");
            PreparedStatement insertOrder4 = connection.prepareStatement("INSERT INTO orders (Registred_User, shipping_address, payment_method, order_owner, total_price, creation_date, delivery_date, status_order) VALUES ('4', 'Via Le Noci', 'Contrassegno', 'Maria Crescenzo', '30', '2020-02-06', '2020-02-16', '5');");
            PreparedStatement insertOrderItem = connection.prepareStatement("INSERT INTO order_item (Order_number, Product_details, payed_price, qnt_in_order) VALUES ('1', '1', '90', '1');");
            PreparedStatement insertOrderItem2 = connection.prepareStatement("INSERT INTO order_item (Order_number, Product_details, payed_price, qnt_in_order) VALUES ('2', '2', '80', '1');");
            PreparedStatement insertOrderItem3 = connection.prepareStatement("INSERT INTO order_item (Order_number, Product_details, payed_price, qnt_in_order) VALUES ('3', '3', '20', '1');");
            PreparedStatement insertOrderItem4 = connection.prepareStatement("INSERT INTO order_item (Order_number, Product_details, payed_price, qnt_in_order) VALUES ('4', '4', '30', '1');");
            PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Pant Big Iop','HUF','Jeans largo','Jeans','90','1','0','0','0','bello');");
            PreparedStatement insertProduct2 = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Felpa cappuccio','Carhartt','Felpa hoodie','Felpa','80','1','0','0','0','bello');");
            PreparedStatement insertProduct3 = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Pure Tshirt','Fila','tshirt manica corta','tshirt','20','1','0','0','0','bello');");
            PreparedStatement insertProduct4 = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Logo hoodie','Doomsday','Felpa hoodie','Felpa','30','1','0','0','0','bello');");
            PreparedStatement insertProductDetails = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('1','blu', 'm','0','1','path/folder');");
            PreparedStatement insertProductDetails2 = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('2','rossa', 'l','0','1','path/folder');");
            PreparedStatement insertProductDetails3 = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('3','viola', 's','0','1','path/folder');");
            PreparedStatement insertProductDetails4 = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('4','celeste', 'xl','0','1','path/folder');");

            insertUser.executeUpdate();
            insertUser2.executeUpdate();
            insertUser3.executeUpdate();
            insertUser4.executeUpdate();
            insertOrder.executeUpdate();
            insertOrder2.executeUpdate();
            insertOrder3.executeUpdate();
            insertOrder4.executeUpdate();
            insertOrderItem.executeUpdate();
            insertOrderItem2.executeUpdate();
            insertOrderItem3.executeUpdate();
            insertOrderItem4.executeUpdate();
            insertProduct.executeUpdate();
            insertProduct2.executeUpdate();
            insertProduct3.executeUpdate();
            insertProduct4.executeUpdate();
            insertProductDetails.executeUpdate();
            insertProductDetails2.executeUpdate();
            insertProductDetails3.executeUpdate();
            insertProductDetails4.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }

    }

    public OrderDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
        this.type = type;
        this.paramForDAO = paramForDAO;
        this.expectedResult = expectedResult;
    }

    @AfterClass
    public static void tearDown() {
        try {
            connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/testing_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM registred_users");
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate("DELETE FROM orders");
            Statement statement3 = connection.createStatement();
            statement3.executeUpdate("DELETE FROM order_item");
            Statement statement4 = connection.createStatement();
            statement4.executeUpdate("DELETE FROM products");
            Statement statement5 = connection.createStatement();
            statement5.executeUpdate("DELETE FROM product_details");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object [][] {
                {Type.CREATEORDER, new Object[]{new OrderBean(1, 1, 90.0f, UserDAO.convert(new Date()), UserDAO.convert(new Date()), "Contrassegno", "Via Romano Genova", "Angela Adiletta"), new ProductBean(1, 0, 0, 0.0f, 40.0f, "Octopus Hoodie", "Octopus", "Felpa hoodie", "Felpa", "Felpa molto bella"), new ProductDetailsBean(1, 0, 1, "Rosa", "M", "PATH/PATH"), new Integer(1)}, true},
                {Type.MODIFYORDER, new Object[]{new Integer(1), new Integer(5), new Date()}, true},
                {Type.CANCELORDER, new Integer(1), true},
                {Type.CANCELORDER, new Integer(20), false},
                {Type.RETRIEVEBYID, new Integer(1), false},
                {Type.RETRIEVEBYID, new Integer(30), true},
                {Type.RETRIEVEBYUSERID, new Integer(1), false},
                {Type.RETRIEVEBYUSERID, new Integer(22), true},
                {Type.RETRIEVEBYDATE, null, false},
                {Type.RETRIEVECANCELLEDORDERS, new Integer(5), false},
                {Type.RETRIEVECANCELLEDORDERS, new Integer(10), true},
                {Type.RETRIEVEALL, null, false},
                {Type.RETRIEVEBYORDER, new Integer(1), false},
                {Type.RETRIEVEBYORDER, new Integer(15), true}
        });
    }

    @org.junit.Test
    public void createOrder() {
        boolean result = false;
        Assume.assumeTrue(type.equals(Type.CREATEORDER));
        HashMap<Pair<ProductBean, ProductDetailsBean>, Integer> j = new HashMap<>();
        Object[] objects = (Object[])paramForDAO;
        ProductBean productBean = (ProductBean)objects[1];
        ProductDetailsBean productDetailsBean = (ProductDetailsBean)objects[2];
        Integer integer = (Integer)objects[3];
        OrderBean orderBean = (OrderBean)objects[0];
        Pair<ProductBean, ProductDetailsBean> productBeanProductDetailsBeanPair = new Pair<>(productBean, productDetailsBean);
        j.put(productBeanProductDetailsBeanPair, integer);
        try {
            result = orderDAO.createOrder(orderBean, j);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void modifyOrder() {
        Assume.assumeTrue(type.equals(Type.MODIFYORDER));
        Object[] objects = (Object[])paramForDAO;
        Integer order_id = (Integer)objects[0];
        Integer status = (Integer)objects[1];
        Date date = (Date)objects[2];
        boolean result = false;
        try {
            result = orderDAO.modifyOrder(order_id, status, UserDAO.convert(date));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void cancelOrder() {
        Assume.assumeTrue(type.equals(Type.CANCELORDER));
        Integer order_id = (Integer)paramForDAO;
        boolean result = false;
        try {
            result = orderDAO.cancelOrder(order_id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void retrieveByID() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYID));
        Integer order_id = (Integer)paramForDAO;
        boolean result = false;
        try {
            OrderBean orderBean = orderDAO.retrieveByID(order_id);
            result = orderBean.isEmpty();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void retrieveByUserID() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYUSERID));
        Integer user_id = (Integer)paramForDAO;
        boolean result = false;
        try {
            LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> retrieveByUserID = orderDAO.retrieveByUserID(user_id);
            result = retrieveByUserID.isEmpty();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);

    }

    @org.junit.Test
    public void retrieveByDate() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYDATE));
        Object object = (Object)paramForDAO;
        boolean result = false;
        try {
            HashSet<OrderBean> orderBeans = orderDAO.retrieveByDate((java.sql.Date)object, (java.sql.Date)object);
            result = orderBeans.isEmpty();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void retrieveCancelledOrders() {
        Assume.assumeTrue(type.equals(Type.RETRIEVECANCELLEDORDERS));
        Integer integer = (Integer)paramForDAO;
        boolean result = false;
        try {
            HashSet<OrderBean> orderBeans = orderDAO.retrieveCancelledOrders(integer);
            result = orderBeans.isEmpty();
        }catch (SQLException e) {

        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void retrieveAll() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEALL));
        boolean result = false;
        try {
            HashMap<OrderBean, ArrayList<OrderItemBean>> orderBeanArrayListHashMap = orderDAO.retrieveAll();
            result = orderBeanArrayListHashMap.isEmpty();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void retrieveByOrder() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYORDER));
        boolean result = false;
        Integer order_id = (Integer)paramForDAO;
        try {
            LinkedHashMap<OrderBean, ArrayList<Pair<OrderItemBean, Pair<ProductBean, ProductDetailsBean>>>> orderBeanArrayListLinkedHashMap = orderDAO.retrieveByOrder(order_id);
            result = orderBeanArrayListLinkedHashMap.isEmpty();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }
}
