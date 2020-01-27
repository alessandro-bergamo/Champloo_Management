package com.champloo.test;

import com.champloo.model.CartDAO;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CartDAOTest {
    enum Type{INSERTPRODUCT, MODIFYQUANTITY, RETRIEVENUMBEROFPRODUCTS, RETRIEVEPRODUCTS, RETRIEVETOTAL, DELETEPRODUCT, CLEARCART, CREATECART, RETRIEVECART, RETRIEVECARTITEM};
    private Type type;
    private Boolean expectedResult;
    private CartDAO cartDAO;
    private Object paramForDAO;

    @Before
    public void initialize() {
        cartDAO = new CartDAO();
    }

    public CartDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
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
    public void insertProduct() {
        Assume.assumeTrue(type.equals(Type.INSERTPRODUCT));
    }

    @org.junit.Test
    public void modifyQuantity() {
        Assume.assumeTrue(type.equals(Type.MODIFYQUANTITY));
    }

    @org.junit.Test
    public void retrieveNumberOfProducts() {
        Assume.assumeTrue(type.equals(Type.RETRIEVENUMBEROFPRODUCTS));
    }

    @org.junit.Test
    public void retrieveProducts() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEPRODUCTS));
    }

    @org.junit.Test
    public void retrieveTotal() {
        Assume.assumeTrue(type.equals(Type.RETRIEVETOTAL));
    }

    @org.junit.Test
    public void deleteProduct() {
        Assume.assumeTrue(type.equals(Type.DELETEPRODUCT));
    }

    @org.junit.Test
    public void clearCart() {
        Assume.assumeTrue(type.equals(Type.CLEARCART));
    }

    @org.junit.Test
    public void retrieveCart() {
        Assume.assumeTrue(type.equals(Type.RETRIEVECART));
    }

    @org.junit.Test
    public void retrieveCartItem() {
        Assume.assumeTrue(type.equals(Type.RETRIEVECARTITEM));
    }

    @org.junit.Test
    public void createCart() {
        Assume.assumeTrue(type.equals(Type.CREATECART));
    }
}
