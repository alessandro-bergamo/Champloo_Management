package com.champloo.test;

import com.champloo.control.CartControl;
import com.champloo.control.UserControl;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CartControlTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HttpSession session;
    private CartControl servlet;
    private Boolean expectedResult;
    private Object paramForServlet;
    private Type type;
    enum Type{LOGIN, INSERTPRODUCT, MODIFYQUANTITY, RETRIEVENUMBEROFPRODUCTS, RETRIEVEPRODUCTS, RETRIEVETOTAL, DELETEPRODUCT, CLEARCART, SUBMITCHECKOUT, CREATEORDER};

    @Before
    public void initialize() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new CartControl();
    }

    public CartControlTest(Type type, Object paramForServlet, Boolean expectedResult) {
        this.expectedResult = expectedResult;
        this.paramForServlet = paramForServlet;
        this.type = type;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object[][] {

        });
    }

    @org.junit.Test
    public void login() {
        Assume.assumeTrue(type.equals(Type.LOGIN));
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
    public void submitCheckout() {
        Assume.assumeTrue(type.equals(Type.SUBMITCHECKOUT));
    }

    @org.junit.Test
    public void createOrder() {
        Assume.assumeTrue(type.equals(Type.CREATEORDER));
    }
}
