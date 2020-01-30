package com.champloo.test;

import com.champloo.bean.ProductBean;
import com.champloo.bean.ProductDetailsBean;
import com.champloo.model.ProductDAO;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class ProductDAOTest {
    enum Type{ADDPRODUCT, RETRIEVEPRODUCTWITHDETAILS, RETRIEVEBYID, RETRIEVEBYMODEL, RETRIEVEBYCATEGORY, RETRIEVEBYBRAND, RETRIEVEBYCOLOR, RETRIEVEBYSIZE, RETRIEVEBYSTATUS, RETRIEVEALL, CREATEWINDOW, DELETEPRODUCT, UPDATEPRODUCT};
    private Type type;
    private Boolean expectedResult;
    private ProductDAO productDAO;
    private Object paramForDAO;

    @Before
    public void initialize() {
        productDAO = new ProductDAO();
    }

    public ProductDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
        this.type = type;
        this.paramForDAO = paramForDAO;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object [][] {
                {Type.ADDPRODUCT, new Object[]{new ProductBean(1, 5, 10, 5.0f, 12.4f, "Acrobat", "NorthFace", "Puffer", "Giubbino", "Molto bello"), new ProductDetailsBean(1, 50, 5, "Blu", "XL", "ciao/ciao")}, true},
                {Type.ADDPRODUCT, new Object[]{new ProductBean(1, 5, 10, 5.0f, 12.4f, "Acrobat", "NorthFace", "Puffer", "Giubbino", "Molto bello"), new ProductDetailsBean(1, 50, 15, "Rosa", "XXL", "ciao/ciao")}, true},
                {Type.RETRIEVEPRODUCTWITHDETAILS, new Object[]{11, 18}, true},
                {Type.RETRIEVEPRODUCTWITHDETAILS, new Object[]{100, 100}, false},
                {Type.RETRIEVEBYID, new Integer(11), true},
                {Type.RETRIEVEBYID, new Integer(100), false},
                {Type.RETRIEVEBYMODEL, "Puffer", true},
                {Type.RETRIEVEBYMODEL, "nonso", false},
                {Type.RETRIEVEBYCATEGORY, "giubbino", true},
                {Type.RETRIEVEBYCATEGORY, "boh", false},
                {Type.RETRIEVEBYBRAND, "NorthFace", true},
                {Type.RETRIEVEBYBRAND, "Nobrand", false},
                {Type.RETRIEVEBYCOLOR, "nero", true},
                {Type.RETRIEVEBYCOLOR, "colore", false},
                {Type.RETRIEVEBYSIZE, "XL", true},
                {Type.RETRIEVEBYSIZE, "SIZE", false},
                {Type.RETRIEVEBYSTATUS, "1", true},
                {Type.RETRIEVEBYSTATUS, "100", false},
                {Type.RETRIEVEALL, null, true},
                {Type.CREATEWINDOW, null, true},
                {Type.DELETEPRODUCT, new Integer(5), true},
                {Type.DELETEPRODUCT, new Integer(100), false},
                {Type.UPDATEPRODUCT, new Object[]{new ProductBean(11, 1, 5, 10, 5.0f, 12.4f, "Reader", "PullAndBear", "Puffer", "Giubbino", "Molto bello"), new ProductDetailsBean(18,11, 30, 50, "Viola", "XL", "ciao/ciao")}, true}

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
        try {
            String category = (String)paramForDAO;
            HashMap<ProductBean, ArrayList<ProductDetailsBean>> hashMap = productDAO.retrieveByCategory(category);
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
