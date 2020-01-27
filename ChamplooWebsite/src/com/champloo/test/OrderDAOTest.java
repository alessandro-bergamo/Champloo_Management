package com.champloo.test;

import com.champloo.model.OrderDAO;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderDAOTest {
    enum Type{CREATEORDER, MODIFYORDER, CANCELORDER, RETRIEVEBYID, RETRIEVEBYUSERID, RETRIEVEBYDATE, RETRIEVECANCELLEDORDERS, RETRIEVEALL, RETRIEVEBYORDERD};
    private Type type;
    private Boolean expectedResult;
    private OrderDAO orderDAO;
    private Object paramForDAO;

    @Before
    public void initialize() {
        orderDAO = new OrderDAO();
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object [][] {

        });
    }

    @org.junit.Test
    public void createOrder() {
        Assume.assumeTrue(type.equals(Type.CREATEORDER));
    }

    @org.junit.Test
    public void modifyOrder() {
        Assume.assumeTrue(type.equals(Type.MODIFYORDER));
    }

    @org.junit.Test
    public void cancelOrder() {
        Assume.assumeTrue(type.equals(Type.CANCELORDER));
    }

    @org.junit.Test
    public void retrieveByID() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYID));
    }

    @org.junit.Test
    public void retrieveByUserID() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYUSERID));
    }

    @org.junit.Test
    public void retrieveByDate() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYDATE));
    }

    @org.junit.Test
    public void retrieveCancelledOrders() {
        Assume.assumeTrue(type.equals(Type.RETRIEVECANCELLEDORDERS));
    }

    @org.junit.Test
    public void retrieveAll() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEALL));
    }

    @org.junit.Test
    public void retrieveByOrder() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYORDERD));
    }
}
