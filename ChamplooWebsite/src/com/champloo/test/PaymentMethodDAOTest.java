package com.champloo.test;

import com.champloo.bean.PaymentMethodBean;
import com.champloo.model.PaymentMethodDAO;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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

    public PaymentMethodDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
        this.type = type;
        this.expectedResult = expectedResult;
        this.paramForDAO = paramForDAO;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object[][] {
                {Type.INSERTPMETHOD, new PaymentMethodBean(402, "40824280482", "Visa", "Antonio Mancuso", "Tony", LocalDate.now(), LocalDate.now()), true},
                {Type.INSERTPMETHOD, new PaymentMethodBean(402, "40824280482", "Visa", "Antonio Mancuso", "Tony", LocalDate.now(), LocalDate.now()), false},
                {Type.DELETEPMETHOD, new Integer(2), true},
                {Type.DELETEPMETHOD, new Integer(3), false},
                {Type.RETRIEVEBYCARDNUMBER, "40824280482", false},
                {Type.RETRIEVEBYCARDNUMBER, "111112", true},
                {Type.RETRIEVEBYUSERID, new Integer(3), false},
                {Type.RETRIEVEBYUSERID, new Integer(10), true}
        });
    }

    @org.junit.Test
    public void insertPMethod() {
        Assume.assumeTrue(type.equals(Type.INSERTPMETHOD));
        try {
            Assert.assertEquals(expectedResult, paymentMethodDAO.insertPMethod((PaymentMethodBean)paramForDAO));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void deletePMethod() {
        Assume.assumeTrue(type.equals(Type.DELETEPMETHOD));
        try {
            Integer integer = (Integer)paramForDAO;
            Assert.assertEquals(expectedResult, paymentMethodDAO.deletePMethod(integer.intValue()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveByCardNumber() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYCARDNUMBER));
        try {
            Assert.assertEquals(expectedResult, paymentMethodDAO.retrieveByCardNumber((String)paramForDAO).isEmpty());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveByUserID() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYUSERID));
        try {
            Integer integer = (Integer)paramForDAO;
            ArrayList<PaymentMethodBean> paymentMethodBeanHashSet = paymentMethodDAO.retrieveByUserID(integer.intValue());
            Assert.assertEquals(expectedResult, paymentMethodBeanHashSet.isEmpty());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
