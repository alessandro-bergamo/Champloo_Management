package com.champloo.test;

import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.model.ProductDAO;
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
public class ProductDAOTest {
    enum Type{ADDPRODUCT, RETRIEVEPRODUCTWITHDETAILS, RETRIEVEBYID, RETRIEVEBYMODEL, RETRIEVEBYCATEGORY, RETRIEVEBYBRAND, RETRIEVEBYFEEDBACKS, RETRIEVEBYAVERAGE, RETRIEVEBYCOLOR, RETRIEVEBYSIZE, RETRIEVEBYSTATUS, RETRIEVEALL, CREATEWINDOW, RETRIEVENEWPRODUCTS, UPDATERATING, DELETEPRODUCT, UPDATEPRODUCT};
    private Type type;
    private Boolean expectedResult;
    private static ProductDAO productDAO;
    private Object paramForDAO;
    static ConnectionPool connectionPool;
    static Connection connection;

    @BeforeClass
    public static void initialize() {
        productDAO = new ProductDAO("");
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

    public ProductDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
        this.type = type;
        this.paramForDAO = paramForDAO;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object [][] {
                {Type.ADDPRODUCT, new Object[]{new ProductBean(1, 5, 10, 5.0f, 12.4f, "Acrobat", "NorthFace", "Puffer", "Giubbino", "Molto bello"), new ProductDetailsBean(6, 50, 5, "Blu", "XL", "ciao/ciao")}, true},
              //  {Type.ADDPRODUCT, new Object[]{new ProductBean(1, 5, 10, 5.0f, 12.4f, "Acrobat", "NorthFace", "Puffer", "Giubbino", "Molto bello"), new ProductDetailsBean(6, 50, 15, "Rosa", "XXL", "ciao/ciao")}, false},
                {Type.RETRIEVEPRODUCTWITHDETAILS, new Object[]{1, 1}, true},
                {Type.RETRIEVEPRODUCTWITHDETAILS, new Object[]{100, 100}, false},
                {Type.RETRIEVEBYID, new Integer(1), true},
                {Type.RETRIEVEBYID, new Integer(100), false},
                {Type.RETRIEVEBYMODEL, "Felpa con cappuccio", true},
                {Type.RETRIEVEBYMODEL, "Puffer", false},
                {Type.RETRIEVEBYCATEGORY, "tshirt", false},
                {Type.RETRIEVEBYCATEGORY, "leggins", true},
                {Type.RETRIEVEBYBRAND, "Fila", true},
                {Type.RETRIEVEBYBRAND, "Levis", false},
                {Type.RETRIEVEBYFEEDBACKS, null, false},
                {Type.RETRIEVEBYAVERAGE, null, false},
                {Type.RETRIEVEBYCOLOR, "nera", true},
                {Type.RETRIEVEBYCOLOR, "arancione", false},
                {Type.RETRIEVEBYSIZE, "xl", true},
                {Type.RETRIEVEBYSIZE, "xxxl", false},
                {Type.RETRIEVEBYSTATUS, "1", true},
                {Type.RETRIEVEBYSTATUS, "5", false},
                {Type.RETRIEVEALL, null, true},
                {Type.CREATEWINDOW, null, true},
                {Type.RETRIEVENEWPRODUCTS, null, false},
                {Type.UPDATERATING, new Object[]{new Integer(1), new Integer(5)}, true},
                {Type.UPDATERATING, new Object[]{new Integer(10), new Integer(4)}, false},
                {Type.DELETEPRODUCT, new Integer(2), true},
                {Type.DELETEPRODUCT, new Integer(100), false},
                {Type.UPDATEPRODUCT, new Object[]{new ProductBean(1, 1, 5, 10, 5.0f, 12.4f, "Reader", "PullAndBear", "Puffer", "Giubbino", "Molto bello"), new ProductDetailsBean(18,11, 30, 50, "Viola", "XL", "ciao/ciao")}, true}
        });
    }

    @org.junit.Test
    public void addProduct() {
        Assume.assumeTrue(type.equals(Type.ADDPRODUCT));
        Object[] objects = (Object[])paramForDAO;
        ProductBean productBean = (ProductBean)objects[0];
        ProductDetailsBean productDetailsBean = (ProductDetailsBean)objects[1];

        try {
            Assert.assertEquals(expectedResult, productDAO.addProduct(productBean, productDetailsBean));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveProductWithDetails() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEPRODUCTWITHDETAILS));
        Object[] objects = (Object[])paramForDAO;
        int id_product = (int)objects[0];
        int id_product_details = (int)objects[1];

        try {
            Pair<ProductBean, ProductDetailsBean> productBeanProductDetailsBeanPair = productDAO.retrieveProductWithDetails(id_product, id_product_details);
            ProductBean productBean = productBeanProductDetailsBeanPair.getKey();
            ProductDetailsBean productDetailsBean = productBeanProductDetailsBeanPair.getValue();
            boolean notEmpty = (!productBean.isEmpty() && !productDetailsBean.isEmpty());
            Assert.assertEquals(expectedResult, notEmpty);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveById() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYID));
        try {
            Integer integer = (Integer)paramForDAO;
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveById(integer.intValue());
            Iterator iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            ProductBean productBean = (ProductBean)entry.getKey();
            ArrayList<ProductDetailsBean> productDetailsBeanArrayList = (ArrayList)entry.getValue();
            boolean resultIsNotEmpty = (!productBean.isEmpty() && productDetailsBeanArrayList.size() > 0);
            Assert.assertEquals(expectedResult, resultIsNotEmpty);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveByModel() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYMODEL));
        try {
            String model = (String)paramForDAO;
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveByModel(model);
            Iterator iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            ProductBean productBean = (ProductBean)entry.getKey();
            ArrayList<ProductDetailsBean> productDetailsBeanArrayList = (ArrayList)entry.getValue();
            boolean resultIsNotEmpty = (!productBean.isEmpty() && productDetailsBeanArrayList.size() > 0);
            Assert.assertEquals(expectedResult, resultIsNotEmpty);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveByCategory() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYCATEGORY));
        boolean result = false;
        try {
            String category = (String)paramForDAO;
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveByCategory(category);
            Iterator iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = null;
            ProductBean productBean = null;
            ArrayList<ProductDetailsBean> productDetailsBeanArrayList = null;
            if(iterator.hasNext()) {
                entry = (HashMap.Entry) iterator.next();
                productBean = (ProductBean)entry.getKey();
                productDetailsBeanArrayList = (ArrayList)entry.getValue();
                result = productBean.isEmpty();
                result = productDetailsBeanArrayList.size() == 0;
            } else
                result = true;

        }catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);

    }

    @org.junit.Test
    public void retrieveByBrand() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYBRAND));
        try {
            String brand = (String)paramForDAO;
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveByBrand(brand);
            Iterator iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            ProductBean productBean = (ProductBean)entry.getKey();
            ArrayList<ProductDetailsBean> productDetailsBeanArrayList = (ArrayList)entry.getValue();
            boolean resultIsNotEmpty = (!productBean.isEmpty() && productDetailsBeanArrayList.size() > 0);
            Assert.assertEquals(expectedResult, resultIsNotEmpty);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveByFeedbacks() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYFEEDBACKS));
        boolean result = false;
        try {
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> productBeanArrayListHashMap = productDAO.retrieveByFeedbacks();
            result = productBeanArrayListHashMap.isEmpty();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void retrieveByAverage() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYAVERAGE));
        boolean result = false;
        try {
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> productBeanArrayListHashMap = productDAO.retrieveByAverage();
            result = productBeanArrayListHashMap.isEmpty();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void retrieveByColor() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYCOLOR));
        try {
            String color = (String)paramForDAO;
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveByColor(color);
            Iterator iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            ProductBean productBean = (ProductBean)entry.getKey();
            ArrayList<ProductDetailsBean> productDetailsBeanArrayList = (ArrayList)entry.getValue();
            boolean resultIsNotEmpty = (!productBean.isEmpty() && productDetailsBeanArrayList.size() > 0);
            Assert.assertEquals(expectedResult, resultIsNotEmpty);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveBySize() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYSIZE));
        try {
            String size = (String)paramForDAO;
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveBySize(size);
            Iterator iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            ProductBean productBean = (ProductBean)entry.getKey();
            ArrayList<ProductDetailsBean> productDetailsBeanArrayList = (ArrayList)entry.getValue();
            boolean resultIsNotEmpty = (!productBean.isEmpty() && productDetailsBeanArrayList.size() > 0);
            Assert.assertEquals(expectedResult, resultIsNotEmpty);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveByStatus() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYSTATUS));
        try {
            String status = (String) paramForDAO;
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveByStatus(status);
            Iterator iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            ProductBean productBean = (ProductBean)entry.getKey();
            ArrayList<ProductDetailsBean> productDetailsBeanArrayList = (ArrayList)entry.getValue();
            boolean resultIsNotEmpty = (!productBean.isEmpty() && productDetailsBeanArrayList.size() > 0);
            Assert.assertEquals(expectedResult, resultIsNotEmpty);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveAll() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEALL));
        try {
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveAll();
            Iterator iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            ProductBean productBean = (ProductBean)entry.getKey();
            ArrayList<ProductDetailsBean> productDetailsBeanArrayList = (ArrayList)entry.getValue();
            boolean resultIsNotEmpty = (!productBean.isEmpty() && productDetailsBeanArrayList.size() > 0);
            Assert.assertEquals(expectedResult, resultIsNotEmpty);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void createWindow() {
        Assume.assumeTrue(type.equals(Type.CREATEWINDOW));
        try {
            ArrayList<Pair<ProductBean, ProductDetailsBean>> arrayList= productDAO.createWindow();
            Assert.assertEquals(expectedResult, arrayList.size() > 0);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveNewProducts() {
        Assume.assumeTrue(type.equals(Type.RETRIEVENEWPRODUCTS));
        boolean result = false;
        try {
            HashMap<ProductBean, ArrayList<ProductDetailsBean>>  productBeanArrayListHashMap= productDAO.retrieveNewProducts();
            result = productBeanArrayListHashMap.isEmpty();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void updateRating() {
        Assume.assumeTrue(type.equals(Type.UPDATERATING));
        boolean result = false;
        try {
            Object[] objects = (Object[])paramForDAO;
            Integer id_product = (Integer)objects[0];
            Integer rating_score = (Integer)objects[1];
            result = productDAO.updateRating(id_product, rating_score);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void deleteProduct() {
        Assume.assumeTrue(type.equals(Type.DELETEPRODUCT));
        try {
            Integer integer = (Integer)paramForDAO;
            Assert.assertEquals(expectedResult, productDAO.deleteProduct(integer.intValue()));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void updateProduct() {
        Assume.assumeTrue(type.equals(Type.UPDATEPRODUCT));
        try {
            Object[] objects = (Object[])paramForDAO;
            ProductBean productBean = (ProductBean)objects[0];
            ProductDetailsBean productDetailsBean = (ProductDetailsBean)objects[1];
            Assert.assertEquals(expectedResult, productDAO.updateProduct(productBean, productDetailsBean));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
