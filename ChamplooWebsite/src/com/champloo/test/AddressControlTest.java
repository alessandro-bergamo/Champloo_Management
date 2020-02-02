package com.champloo.test;

import com.champloo.control.AddressControl;
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
public class AddressControlTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HttpSession session;
    private AddressControl servlet;
    private Boolean expectedResult;
    private Object paramForServlet;
    private Type type;
    enum Type {INSERT, DELETE, LOGIN, SUBMITCHECKOUT, UPDATEUSER};

    @Before
    public void initialize() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new AddressControl();
    }

    public AddressControlTest(Type type, Object paramForServlet, Boolean expectedResult) {
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
    public void insert() {
        Assume.assumeTrue(type.equals(Type.INSERT));
    }

    @org.junit.Test
    public void delete() {
        Assume.assumeTrue(type.equals(Type.DELETE));
    }

    @org.junit.Test
    public void login() {
        Assume.assumeTrue(type.equals(Type.LOGIN));
    }

    @org.junit.Test
    public void submitCheckout() {
        Assume.assumeTrue(type.equals(Type.SUBMITCHECKOUT));
    }

    @org.junit.Test
    public void updateUser() {
        Assume.assumeTrue(type.equals(Type.UPDATEUSER));
    }
}
