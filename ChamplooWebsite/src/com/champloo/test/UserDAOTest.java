package com.champloo.test;

import com.champloo.bean.UserBean;
import com.champloo.model.UserDAO;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@RunWith(Parameterized.class)
public class UserDAOTest {

    enum Type {REGISTERUSER, GETUSERBYEMAIL, GETUSERBYUSERNAME, GETALLUSERS, UPDATEUSER, DELETEUSER, BLOCKUSER, LOGIN, CHANGEPASSWORD};
    private Type type;
    private Boolean expectedResult;
    private UserDAO userDAO;
    private UserBean userBean;

    @Before
    public void initialize() {
        userDAO = new UserDAO();
    }

    public UserDAOTest(Type type, UserBean userBean, Boolean expectedResult) {
        this.type = type;
       this.userBean = userBean;
       this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object [] [] {
                {Type.REGISTERUSER, new UserBean("David", "Capuano", "daviddavid", "davidecap00@hotmail.it", "capucapu", new Date(), 1), false},
                {Type.REGISTERUSER, new UserBean("Antonio", "Mancuso", "yeezus", "gokuantony@live.it", "yeezy", new Date(), 1), true}
        });
    }

    @org.junit.Test
    public void registerUser() {
        Assume.assumeTrue((type.equals(Type.REGISTERUSER)));
        System.out.println("Parameterized Number is : " + userBean.getUsername());
        assertEquals(expectedResult, userDAO.registerUser(userBean));

    }

    @org.junit.jupiter.api.Test
    public void getUserByEmail() {
        Assume.assumeTrue((type.equals(Type.GETUSERBYEMAIL)));
    }

    @org.junit.jupiter.api.Test
    public void getUserByUsername() {
        Assume.assumeTrue((type.equals(Type.GETUSERBYUSERNAME)));
    }

    @org.junit.jupiter.api.Test
    public void getAllUsers() {
        Assume.assumeTrue((type.equals(Type.GETALLUSERS)));
    }

    @org.junit.jupiter.api.Test
    public void updateUser() {
        Assume.assumeTrue((type.equals(Type.UPDATEUSER)));
    }

    @org.junit.jupiter.api.Test
    public void deleteUser() {
        Assume.assumeTrue((type.equals(Type.DELETEUSER)));
    }

    @org.junit.jupiter.api.Test
    public void blockUser() {
        Assume.assumeTrue((type.equals(Type.BLOCKUSER)));
    }

    @org.junit.jupiter.api.Test
    public void login() {
        Assume.assumeTrue((type.equals(Type.LOGIN)));
    }

    @org.junit.jupiter.api.Test
    public void changePassword() {
        Assume.assumeTrue((type.equals(Type.CHANGEPASSWORD)));
    }
}