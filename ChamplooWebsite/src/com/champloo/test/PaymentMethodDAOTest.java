package com.champloo.test;

import com.champloo.model.PaymentMethodDAO;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PaymentMethodDAOTest {
    enum Type{INSERTPMETHOD, DELETEPMETHOD, RETRIEVEBYCARDNUMBER, RETRIEVEBYUSERID};
    private Type type;
    private Boolean expectedResult;
    private PaymentMethodDAO paymentMethodDAO;
    private Object paramForDAO;

    @Before
    public void initalize() {
        paymentMethodDAO = new PaymentMethodDAO();
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object[][] {

        });
    }

    @org.junit.Test
    public void insertPMethod() {
        Assume.assumeTrue(type.equals(Type.INSERTPMETHOD));
    }

    @org.junit.Test
    public void deletePMethod() {
        Assume.assumeTrue(type.equals(Type.DELETEPMETHOD));
    }

    @org.junit.Test
    public void retrieveByCardNumber() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYCARDNUMBER));
    }

    @org.junit.Test
    public void retrieveByUserID() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYUSERID));
    }
}
