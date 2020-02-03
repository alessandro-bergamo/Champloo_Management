package com.champloo.test;

import com.champloo.bean.UserBean;
import com.champloo.control.UserControl;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import java.sql.Connection;
import java.util.*;

import static org.easymock.EasyMock.mock;


@RunWith(Parameterized.class)
public class UserControlTest {
    private Connection mConnection;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HttpSession session;
    private UserControl servlet;
    private Boolean expectedResult;
    private Object paramForServlet;
    private Type type;
    enum Type {LOGIN, FORGETPASSWORD, MODIFYPASSWORD, LOGOUT, GETUSERBYEMAIL, GETALLUSERS, GETUSERBYUSERNAME, REGISTERUSER, VALIDATEUSER, UPDATEUSER, DELETEUSER, BLOCKUSER};

    @Before
    public void initialize() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new UserControl();

    }

    public UserControlTest(Type type, Object paramForServlet, Boolean expectedResult) {
        this.expectedResult = expectedResult;
        this.paramForServlet = paramForServlet;
        this.type = type;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object[][] {
                {Type.LOGIN, new Object[]{"davidecap00@hotmail.it", "capucapu"}, true},
                {Type.LOGIN, new Object[]{"dfwefew", "wasd"}, false},
                {Type.FORGETPASSWORD, "gokuantony@live.it", true},
                {Type.FORGETPASSWORD, "fenofnew@outlook.it", false},
                {Type.MODIFYPASSWORD, "yeezus", true},
                {Type.MODIFYPASSWORD, "fenwfk", false},
                {Type.LOGOUT, null, true},
                {Type.GETUSERBYEMAIL, "gokuantony@live.it", false},
                {Type.GETUSERBYEMAIL, "fefe@hotmail.it", true},
                {Type.GETALLUSERS, null, true},
                {Type.GETUSERBYUSERNAME, "yeezus", false},
                {Type.GETUSERBYUSERNAME, "fenfe", true},
                {Type.REGISTERUSER, new Object[]{"enderson", "gokuantony@live.it", "Montana", "Castagna", "nonsochemettere"}, true},
                {Type.VALIDATEUSER, new Object[]{"palermi", "tuttookay@hotmail.it", "Montana", "Castagna", "nonsochemettere"}, true},
                {Type.VALIDATEUSER, new Object[]{"enderson", "gokuantony@live.it", "Montana", "Castagna", "nonsochemettere"}, false},
                {Type.UPDATEUSER, new Object[]{new UserBean("", "", "yeezus", "", "", new Date(), 1), "nuovoFirst", "nuovoLast"}, true},
                {Type.UPDATEUSER, new Object[]{new UserBean("", "", "WEFEWF", "", "", new Date(), 1), "nuovoFirst2", "nuovoLast2"}, false},
                {Type.DELETEUSER, new UserBean("", "", "yeezus", "", "", new Date(), 1), true},
                {Type.DELETEUSER, new UserBean("", "", "fwefwe", "", "", new Date(), 1), false},
                {Type.BLOCKUSER, "alexalex", true},
                {Type.BLOCKUSER, "mariooo", false}
        });
    }

    @org.junit.Test
    public void login() {
        Assume.assumeTrue(type.equals(Type.LOGIN));
        request.addParameter("operation", "login");
        Object[] objects = (Object[])paramForServlet;
        String user_email = (String)objects[0];
        String user_password = (String)objects[1];
        request.addParameter("email", user_email);
        request.addParameter("password", user_password);
        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean result = (Boolean)request.getAttribute("login");
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void forgetPassword() {
        Assume.assumeTrue(type.equals(Type.FORGETPASSWORD));
        request.addParameter("operation", "forgetPassword");
        String email = (String)paramForServlet;
        request.addParameter("email", email);
        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean result = (Boolean)request.getAttribute("accreditate");
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void modifyPassword() {
        Assume.assumeTrue(type.equals(Type.MODIFYPASSWORD));
        request.addParameter("operation", "modifyPassword");
        String username = (String)paramForServlet;
        request.addParameter("username", username);
        request.addParameter("new_psw", "ciaociaociao");
        try {
            servlet.doPost(request, response);
        }catch(ServletException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

        boolean result = (Boolean)request.getAttribute("result");
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void logout() {
        Assume.assumeTrue(type.equals(Type.LOGOUT));
        request.addParameter("operation", "logout");
        try {
            servlet.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean result = (Boolean)request.getAttribute("result");
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void getUserByEmail() {
        Assume.assumeTrue(type.equals(Type.GETUSERBYEMAIL));
        request.addParameter("operation", "getUserByEmail");
        String email = (String)paramForServlet;
        request.addParameter("email", email);
        try {
            servlet.doPost(request, response);
        }catch(ServletException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

        UserBean userBean = (UserBean)request.getAttribute("userByEmail");
        boolean result = userBean.isEmpty();
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void getAllUsers() {
        Assume.assumeTrue(type.equals(Type.GETALLUSERS));
        request.addParameter("operation", "getAllUsers");
        try {
            servlet.doPost(request, response);
        }catch(ServletException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

        ArrayList<UserBean> arrayList = (ArrayList<UserBean>)request.getAttribute("allUsers");
        boolean result = arrayList.size() > 0;
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void getUserByUsername() {
        Assume.assumeTrue(type.equals(Type.GETUSERBYUSERNAME));
        String username = (String)paramForServlet;
        request.addParameter("operation", "getUserByUsername");
        request.addParameter("username", username);

        try {
            servlet.doPost(request, response);
        }catch(ServletException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

        UserBean userBean = (UserBean)request.getAttribute("userByUsername");
        boolean result = userBean.isEmpty();
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void registerUser() {
        Assume.assumeTrue(type.equals(Type.REGISTERUSER));
        try {
            Object[] strings = (Object[])paramForServlet;
            String username = (String)strings[0];
            String email = (String)strings[1];
            String firstName = (String)strings[2];
            String lastName = (String)strings[3];
            String password = (String)strings[4];

            request.addParameter("operation", "registerUser");
            request.addParameter("username", username);
            request.addParameter("email", email);
            request.addParameter("firstName", firstName);
            request.addParameter("lastname", lastName);
            request.addParameter("password", password);

            try {
                servlet.doPost(request, response);
            }catch(ServletException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }

            boolean result = (boolean)request.getAttribute("accreditate");
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {

        }
    }

    @org.junit.Test
    public void validateUser() {
        Assume.assumeTrue(type.equals(Type.VALIDATEUSER));
        Object[] strings = (Object[])paramForServlet;
        String username = (String)strings[0];
        String email = (String)strings[1];
        String firstName = (String)strings[2];
        String lastName = (String)strings[3];
        String password = (String)strings[4];

        request.addParameter("operation", "validateUser");
        request.addParameter("username", username);
        request.addParameter("email", email);
        request.addParameter("firstname", firstName);
        request.addParameter("lastname", lastName);
        request.addParameter("password", password);

        try {
            servlet.doPost(request, response);
        }catch(ServletException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

        boolean result = (boolean)request.getAttribute("accreditate");
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void updateUser() {
        Assume.assumeTrue(type.equals(Type.UPDATEUSER));
        Object[]objects = (Object[])paramForServlet;
        UserBean userBean = (UserBean)objects[0];
        String firstName = (String)objects[1];
        String lastName = (String)objects[2];

        request.setSession(new HttpSession() {
            @Override
            public long getCreationTime() {
                return 0;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public long getLastAccessedTime() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int i) {

            }

            @Override
            public int getMaxInactiveInterval() {
                return 0;
            }

            @Override
            public HttpSessionContext getSessionContext() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return userBean;
            }

            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String[] getValueNames() {
                return new String[0];
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public void removeValue(String s) {

            }

            @Override
            public void invalidate() {

            }

            @Override
            public boolean isNew() {
                return false;
            }
        });

        request.addParameter("operation", "updateUser");
        request.addParameter("firstname", firstName);
        request.addParameter("lastname", lastName);

        try {
            servlet.doPost(request, response);
        }catch(ServletException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

        boolean result = (boolean)request.getAttribute("accreditate");
        Assert.assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void deleteUser() {
        Assume.assumeTrue(type.equals(Type.DELETEUSER));
        UserBean userBean = (UserBean)paramForServlet;
        request.setSession(new HttpSession() {
            @Override
            public long getCreationTime() {
                return 0;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public long getLastAccessedTime() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int i) {

            }

            @Override
            public int getMaxInactiveInterval() {
                return 0;
            }

            @Override
            public HttpSessionContext getSessionContext() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return userBean;
            }

            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String[] getValueNames() {
                return new String[0];
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public void removeValue(String s) {

            }

            @Override
            public void invalidate() {

            }

            @Override
            public boolean isNew() {
                return false;
            }
        });

        request.addParameter("operation", "deleteUser");

        try {
            servlet.doPost(request, response);
        }catch(ServletException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

        boolean result = (boolean)request.getAttribute("accreditate");
        Assert.assertEquals(expectedResult, result);

    }

    @org.junit.Test
    public void blockUser() {
        Assume.assumeTrue(type.equals(Type.BLOCKUSER));
        String username = (String)paramForServlet;
        request.addParameter("username", username);
        request.addParameter("operation", "blockUser");

        try {
            servlet.doPost(request, response);
        }catch(ServletException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

        boolean result = (boolean)request.getAttribute("accreditate");
        Assert.assertEquals(expectedResult, result);
    }

}
