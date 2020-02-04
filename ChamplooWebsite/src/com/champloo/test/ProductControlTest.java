package com.champloo.test;

import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.control.ProductControl;
import com.champloo.control.UserControl;
import com.champloo.storage.ConnectionPool;
import javafx.util.Pair;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@RunWith(Parameterized.class)
public class ProductControlTest {
    private static MockHttpServletRequest request;
    private static MockHttpServletResponse response;
    private HttpSession session;
    private static ProductControl servlet;
    private Boolean expectedResult;
    private Object paramForServlet;
    private Type type;
    static ConnectionPool connectionPool;
    static Connection connection;
    enum Type{ADDPRODUCT, CREATEWINDOW, SHOWPRODUCT, RETRIEVEBYMODEL, RETRIEVEBYCATEGORY, RETRIEVEBYBRAND, RETRIEVEBYCOLOR, RETRIEVEBYSIZE, RETRIEVEBYSTATUS, RETRIEVEBYFEEDBACKS, RETRIEVEBYAVERAGE, RETRIEVENEWPRODUCTS, RETRIEVEALL, DELETEPRODUCT, MODIFYPRODUCT};

    @BeforeClass
    public static void initialize() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new ProductControl();

        try {
            String alterSQLProduct = "ALTER TABLE products AUTO_INCREMENT = 1";
            connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/testing_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
            connection = connectionPool.getConnection();
            String alterSQLProductDetails = "ALTER TABLE product_details AUTO_INCREMENT = 1";
            Statement alterProduct = connection.createStatement();
            Statement alterProductDetails = connection.createStatement();
            alterProduct.execute(alterSQLProduct);
            alterProductDetails.execute(alterSQLProductDetails);

            PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Pure Tshirt','Fila','Tshirt manica corta','tshirt','20','1','0','0','0','molto bella');");
            PreparedStatement insertProduct2 = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Logo Hoodie','Domsday','Felpa con cappuccio','felpa','79','1','0','0','0','molto bella');");
            PreparedStatement insertProduct3 = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Sacramento Shirt','Dickies','Camicia manica lunga','camicia','35','1','20','5','5','molto bella');");
            PreparedStatement insertProduct4 = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Polo golf','RalphLauren','Polo manica corta','polo','35','3','20','5','5','molto bella');");
            PreparedStatement insertProduct5 = connection.prepareStatement("INSERT INTO products(name_product, brand_product, model_product, type_product, price_product, status_product, total_rating, average_rating, number_feedback_users, description_product) VALUES ('Logo Banda Pants','Australian','Pantalone tuta','Pantalone','75','4','20','5','5','molto bella');");

            PreparedStatement insertProductDetail = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('1','blu','m','0','1','path/path')");
            PreparedStatement insertProductDetail2 = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('2','rossa','l','0','1','path/path')");
            PreparedStatement insertProductDetail3 = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('3','nera','xl','0','1','path/path')");
            PreparedStatement insertProductDetail4 = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('4','bianca','xl','0','1','path/path')");
            PreparedStatement insertProductDetail5 = connection.prepareStatement("INSERT INTO product_details(Product, color, size, discount_percent, qnt_stock, img_path_folder) VALUES ('5','viola','s','0','1','path/path')");

            insertProduct.executeUpdate();
            insertProduct2.executeUpdate();
            insertProduct3.executeUpdate();
            insertProduct4.executeUpdate();
            insertProduct5.executeUpdate();
            insertProductDetail.executeUpdate();
            insertProductDetail2.executeUpdate();
            insertProductDetail3.executeUpdate();
            insertProductDetail4.executeUpdate();
            insertProductDetail5.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @AfterClass
    public static void tearDown() {
        try {
            connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/testing_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM products");
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate("DELETE FROM product_details");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }

    }

    public ProductControlTest(Type type, Object paramForServlet, Boolean expectedResult) {
        this.expectedResult = expectedResult;
        this.paramForServlet = paramForServlet;
        this.type = type;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object[][] {
                {Type.ADDPRODUCT, new Object[]{"Jogger Pants", "Iuter", "Pantalone tuta", "Pantalone", 79.0f, 1, "Molto bello", "blue", "M", 0, 1, "path/folder"}, true},
                {Type.CREATEWINDOW, null, false},
                {Type.SHOWPRODUCT, new Object[]{1, "blu"}, false},
               // {Type.RETRIEVEBYMODEL, "Pantalone tuta", false},
                {Type.RETRIEVEBYMODEL, "leggins", true},
                //{Type.RETRIEVEBYCATEGORY, "Pantalone tuta", false},
                {Type.RETRIEVEBYCATEGORY, "leggins", true},
                //{Type.RETRIEVEBYBRAND, "Fila", false},
                {Type.RETRIEVEBYBRAND, "Gucci", true},
               // {Type.RETRIEVEBYCOLOR, "rossa", false},
                {Type.RETRIEVEBYCOLOR, "cobalto", true},
                {Type.RETRIEVEBYSIZE, "XXXL", true},
                //{Type.RETRIEVEBYSTATUS, "1", false},
                {Type.RETRIEVEBYSTATUS, "6", true},
                {Type.RETRIEVEBYFEEDBACKS, null, false},
               // {Type.RETRIEVEBYAVERAGE, null, false}
                {Type.RETRIEVENEWPRODUCTS,null, true},
                {Type.RETRIEVEALL, null, true},
                {Type.DELETEPRODUCT, new Integer(1), true},
                {Type.DELETEPRODUCT, new Integer(12), false},
                {Type.MODIFYPRODUCT, new Object[]{1, 1, "Pure ", "Fendi", "Tshirt manica corta", "Tshirt", 20.0f, 2, "description", "blu", "L", 0, 1, "PATH"}, true}

        });
    }

    @org.junit.Test
    public void addProduct() {
        Assume.assumeTrue(type.equals(Type.ADDPRODUCT));
        Object[] objects = (Object[])paramForServlet;
        String name_product = (String)objects[0];
        String brand_product = (String)objects[1];
        String model_product = (String)objects[2];
        String type_product = (String)objects[3];
        float price = (float)objects[4];
        int status = (int)objects[5];
        String description_product = (String)objects[6];
        String color = (String)objects[7];
        String size = (String)objects[8];
        int discount_percent = (int)objects[9];
        int qnt_stock = (int)objects[10];
        String img_path_folder = (String)objects[11];

        request.addParameter("operation", "addProduct");
        request.addParameter("name_product", name_product);
        request.addParameter("brand_product", brand_product);
        request.addParameter("model_product", model_product);
        request.addParameter("type_product", type_product);
        request.addParameter("price_product", ""+price);
        request.addParameter("status_product", ""+status);
        request.addParameter("description_product", description_product);
        request.addParameter("color_product", color);
        request.addParameter("size_product", size);
        request.addParameter("discount_percent_product", ""+discount_percent);
        request.addParameter("qnt_stock_product", ""+ qnt_stock);
        request.addParameter("img_path_folder_product", img_path_folder);
        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean result = (Boolean)request.getAttribute("accreditate");
        Assert.assertEquals(expectedResult, result);

    }

    @org.junit.Test
    public void createWindow() {
        Assume.assumeTrue(type.equals(Type.CREATEWINDOW));
        request.addParameter("operation", "createWindow");
        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        ArrayList<Pair<ProductBean, ProductDetailsBean>> window = (ArrayList<Pair<ProductBean, ProductDetailsBean>>)session.getAttribute("window");
        Assert.assertEquals(expectedResult, window.isEmpty());
    }

    @org.junit.Test
    public void showProduct() {
        Assume.assumeTrue(type.equals(Type.SHOWPRODUCT));
        Object[] objects = (Object[])paramForServlet;
        int id_product = (int)objects[0];
        String color = (String)objects[1];

        request.addParameter("operation", "showProduct");
        request.addParameter("id_product", id_product +"");
        request.addParameter("color", color);

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        HashMap<ProductBean, ArrayList<ProductDetailsBean>> product = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)session.getAttribute("product");
        ArrayList<ProductDetailsBean> selectedProductsByColor = (ArrayList<ProductDetailsBean>)session.getAttribute("selectedProductsByColor");
        Assert.assertEquals(expectedResult, product.isEmpty() && selectedProductsByColor.isEmpty());
    }

    @org.junit.Test
    public void retrieveByModel() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYMODEL));
        String model_product = (String)paramForServlet;
        request.addParameter("operation", "retrieveByModel");
        request.addParameter("model_product", model_product);

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getAttribute("productsByModel");
        Assert.assertEquals(expectedResult, products.isEmpty());

    }

    @org.junit.Test
    public void retrieveByCategory() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYCATEGORY));
        request.addParameter("operation", "retrieveByCategory");
        String category = (String)paramForServlet;
        request.addParameter("category", category);

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getSession().getAttribute("productsByCategory");
        Assert.assertEquals(expectedResult, products.isEmpty());
    }

    @org.junit.Test
   public void retrieveByBrand() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYBRAND));
        request.addParameter("operation", "retrieveByBrand");
        String brand = (String)paramForServlet;
        request.addParameter("brand_product");

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getAttribute("productsByBrand");
        Assert.assertEquals(expectedResult, products.isEmpty());
    }

    @org.junit.Test
    public void retrieveByColor() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYCOLOR));
        request.addParameter("operation", "retrieveByColor");
        String color = (String)paramForServlet;
        request.addParameter("color_product", color);

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getAttribute("productsByColor");
        Assert.assertEquals(expectedResult, products.isEmpty());
    }

    @org.junit.Test
    public void retrieveBySize() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYSIZE));

        request.addParameter("operation", "retrieveBySize");
        String size = (String)paramForServlet;
        request.addParameter("size_product", size);

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getAttribute("productsBySize");
        Assert.assertEquals(expectedResult, products.isEmpty());
    }

    @org.junit.Test
    public void retrieveByStatus() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYSTATUS));
        request.addParameter("operation", "retrieveByStatus");
        String status = (String)paramForServlet;
        request.addParameter("status_product", status);

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getAttribute("productsByStatus");
        if(products == null) {
            Assert.assertEquals(expectedResult, true);
        } else
            Assert.assertEquals(expectedResult, false);
    }

    @org.junit.Test
    public void retrieveByFeedbacks() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYFEEDBACKS));
        request.addParameter("operation", "retrieveByFeedbacks");

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getSession().getAttribute("productsByCategory");
        Assert.assertEquals(expectedResult, products.isEmpty());
    }

    @org.junit.Test
    public void retrieveByAverage() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYAVERAGE));
        request.addParameter("operation", "retrieveByAverage");

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getSession().getAttribute("productsByAverage");
        Assert.assertEquals(expectedResult, products.isEmpty());
    }

    @org.junit.Test
    public void retrieveNewProducts() {
        Assume.assumeTrue(type.equals(Type.RETRIEVENEWPRODUCTS));
        request.addParameter("operation", "retrieveNewProducts");

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getSession().getAttribute("productsByCategory");
        Assert.assertEquals(expectedResult, products.isEmpty());
    }

    @org.junit.Test
    public void retrieveAll() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEALL));
        request.addParameter("operation", "retrieveAll");

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<ProductBean, ArrayList<ProductDetailsBean>> products = (HashMap<ProductBean, ArrayList<ProductDetailsBean>>)request.getSession().getAttribute("productsByCategory");
        Assert.assertEquals(expectedResult, products.isEmpty());
    }

    @org.junit.Test
    public void deleteProduct() {
        Assume.assumeTrue(type.equals(Type.DELETEPRODUCT));
        request.addParameter("operation", "deleteProduct");
        Integer integer = (Integer)paramForServlet;
        request.addParameter("id_product", ""+ integer);
        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean result = (boolean)request.getAttribute("accreditate");

    }

    @org.junit.Test
    public void updateProduct() {
        Assume.assumeTrue(type.equals(Type.MODIFYPRODUCT));
        Object[] objects = (Object[])paramForServlet;
        int id_product = (int)objects[0];
        int id_product_details = (int)objects[1];
        String name_product = (String)objects[2];
        String brand_product = (String)objects[3];
        String model_product = (String)objects[4];
        String type_product = (String)objects[5];
        float price = (float)objects[6];
        int status = (int)objects[7];
        String description_product = (String)objects[8];
        String color = (String)objects[9];
        String size = (String)objects[10];
        int discount_percent = (int)objects[11];
        int qnt_stock = (int)objects[12];
        String img_path_folder = (String)objects[13];

        request.addParameter("operation", "modifyProduct");
        request.addParameter("id_product", ""+id_product);
        request.addParameter("id_product_details", ""+id_product_details);
        request.addParameter("name_product", name_product);
        request.addParameter("brand_product", brand_product);
        request.addParameter("model_product", model_product);
        request.addParameter("type_product", type_product);
        request.addParameter("price_product", price + "");
        request.addParameter("status_product", status + "");
        request.addParameter("description_product", description_product);
        request.addParameter("color_product", color);
        request.addParameter("size_product", size);
        request.addParameter("discount_percent_product", discount_percent + "");
        request.addParameter("qnt_stock_product", qnt_stock + "");
        request.addParameter("img_path_folder_product", img_path_folder);

        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean result = (boolean)request.getAttribute("accreditate");
        Assert.assertEquals(expectedResult, result);
    }


}
