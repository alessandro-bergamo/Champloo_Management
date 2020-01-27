package com.champloo.test;

import com.champloo.bean.AddressBean;
import com.champloo.bean.UserBean;
import com.champloo.model.AddressDAO;
import com.champloo.model.UserDAO;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.jws.soap.SOAPBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AddressDAOTest {
    enum Type{INSERTADDRESS, DELETEADDRESS, RETRIEVEBYID, RETRIEVEBYUSERID};
    private Type type;
    private Boolean expectedResult;
    private AddressDAO addressDAO;
    private Object paramForDAO;

    @Before
    public void initialize() {
        addressDAO = new AddressDAO();
    }

    public AddressDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
        this.type = type;
        this.paramForDAO = paramForDAO;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object [] [] {
                {Type.INSERTADDRESS, new AddressBean(25, 84087, 3, "Via Piave", "Sarno", "SA"), true},
                {Type.DELETEADDRESS, new Integer(2), true},
                {Type.DELETEADDRESS, new Integer(5), false},
                {Type.RETRIEVEBYID, new Integer(2), true},
                {Type.RETRIEVEBYID, new Integer(10), false},
                {Type.RETRIEVEBYUSERID, new Integer(3), false},
                {Type.RETRIEVEBYUSERID, new Integer(10), true}
        });
    }

    @org.junit.Test
    public void insertAddress() {
        Assume.assumeTrue(type.equals(Type.INSERTADDRESS));
        try {
            assertEquals(expectedResult, addressDAO.insertAddress((AddressBean)paramForDAO));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void deleteAddress() {
        Assume.assumeTrue(type.equals(Type.DELETEADDRESS));
        try {
            assertEquals(expectedResult, addressDAO.deleteAddress(((Integer)paramForDAO).intValue()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveByID() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYID));
        Integer integer = (Integer)paramForDAO;
        try {
            AddressBean addressBean = addressDAO.retrieveByID(integer.intValue());
            assertEquals(expectedResult, addressBean.isEmpty());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void retrieveByUserID() {
        Assume.assumeTrue(type.equals(Type.RETRIEVEBYUSERID));
        try {
            Integer integer = (Integer)paramForDAO;
            ArrayList<AddressBean> addressBeans = addressDAO.retrieveByUserID(integer.intValue());
            for(AddressBean a: addressBeans) {
                System.out.println("Nnn: " + a.toString() + "" );
            }
            assertEquals(expectedResult, addressBeans.isEmpty());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
