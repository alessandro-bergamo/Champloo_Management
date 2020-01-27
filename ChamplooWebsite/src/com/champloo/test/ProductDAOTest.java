package com.champloo.test;

import com.champloo.model.ProductDAO;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

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

        });
    }

    @org.junit.Test
    public void addProduct() {
        Assume.assumeTrue(type.equals(Type.ADDPRODUCT));
    }

    @org.junit.Test
    public void retrieveProductWithDetails() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEPRODUCTWITHDETAILS));
    }

    @org.junit.Test
    public void retrieveById() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYID));
    }

    @org.junit.Test
    public void retrieveByModel() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYMODEL));
    }

    @org.junit.Test
    public void retrieveByCategory() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYCATEGORY));
    }

    @org.junit.Test
    public void retrieveByBrand() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYBRAND));
    }

    @org.junit.Test
    public void retrieveByColor() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYCOLOR));
    }

    @org.junit.Test
    public void retrieveBySize() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYSIZE));
    }

    @org.junit.Test
    public void retrieveByStatus() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYSTATUS));
    }

    @org.junit.Test
    public void retrieveAll() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEALL));
    }

    @org.junit.Test
    public void createWindow() {
        Assume.assumeTrue(type.equals(Type.CREATEWINDOW));
    }

    @org.junit.Test
    public void deleteProduct() {
        Assume.assumeTrue(type.equals(Type.DELETEPRODUCT));
    }

    @org.junit.Test
    public void updateProduct() {
        Assume.assumeTrue(type.equals(Type.UPDATEPRODUCT));
    }
}
