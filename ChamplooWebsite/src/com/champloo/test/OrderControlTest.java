package com.champloo.test;

import com.champloo.control.OrderControl;
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
public class OrderControlTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HttpSession session;
    private OrderControl servlet;
    private Boolean expectedResult;
    private Object paramForServlet;
    private Type type;
    enum Type {CREATEORDER, CANCELORDER, MODIFYORDER, SHOWORDER, SHOWORDERSPERUSER, SHOWORDERSPERDATE, SHOWCANCELEDORDERS};

    @Before
    public void initialize() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new OrderControl();
    }

    public OrderControlTest(Type type, Object paramForServlet, Boolean expectedResult) {
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
    public void createOrder() {
        Assume.assumeTrue(type.equals(Type.CREATEORDER));
    }

    @org.junit.Test
    public void cancelOrder() {
        Assume.assumeTrue(type.equals(Type.CANCELORDER));
    }

    @org.junit.Test
    public void modifyorder() {
        Assume.assumeTrue(type.equals(Type.MODIFYORDER));
    }

    @org.junit.Test
    public void showOrder() {
        Assume.assumeTrue(type.equals(Type.SHOWORDER));
    }

    @org.junit.Test
    public void showOrdersPerUsers() {
        Assume.assumeTrue(type.equals(Type.SHOWORDERSPERUSER));
    }

    @org.junit.Test
    public void showOrdersPerUpdate() {
        Assume.assumeTrue(type.equals(Type.SHOWORDERSPERDATE));
    }

    @org.junit.Test
    public void showCanceledOrders() {
        Assume.assumeTrue(type.equals(Type.SHOWCANCELEDORDERS));
    }


}
