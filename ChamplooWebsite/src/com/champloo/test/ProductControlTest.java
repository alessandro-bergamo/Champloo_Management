package com.champloo.test;

import com.champloo.control.ProductControl;
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
public class ProductControlTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HttpSession session;
    private ProductControl servlet;
    private Boolean expectedResult;
    private Object paramForServlet;
    private Type type;
    enum Type{ADDPRODUCT, CREATEWINDOW, SHOWPRODUCT, RETRIEVEBYMODEL, RETRIEVEBYCATEGORY, RETRIEVEBYBRAND, RETRIEVEBYCOLOR, RETRIEVEBYSIZE, RETRIEVEBYSTATUS, RETRIEVEBYFEEDBACKS, RETRIEVEBYAVERAGE, RETRIEVENEWPRODUCTS, RETRIEVEALL, DELETEPRODUCT, UPDATEPRODUCT, ADDTOACTIVECART};

    @Before
    public void initialize() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new ProductControl();
    }

    public ProductControlTest(Type type, Object paramForServlet, Boolean expectedResult) {
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
    public void addProduct() {
        Assume.assumeTrue(type.equals(Type.ADDPRODUCT));
    }

    @org.junit.Test
    public void createWindow() {
        Assume.assumeTrue(type.equals(Type.CREATEWINDOW));
    }

    @org.junit.Test
    public void showProduct() {
        Assume.assumeTrue(type.equals(Type.SHOWPRODUCT));
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
    public void retrieveByFeedbacks() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYFEEDBACKS));
    }

    @org.junit.Test
    public void retrieveByAverage() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYAVERAGE));
    }

    @org.junit.Test
    public void retrieveNewProducts() {
        Assume.assumeTrue(type.equals(Type.RETRIEVENEWPRODUCTS));
    }

    @org.junit.Test
    public void retrieveAll() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEALL));
    }

    @org.junit.Test
    public void deleteProduct() {
        Assume.assumeTrue(type.equals(Type.DELETEPRODUCT));
    }

    @org.junit.Test
    public void updateProduct() {
        Assume.assumeTrue(type.equals(Type.UPDATEPRODUCT));
    }

    @org.junit.Test
    public void addToActiveCart() {
        Assume.assumeTrue(type.equals(Type.ADDTOACTIVECART));
    }


}
