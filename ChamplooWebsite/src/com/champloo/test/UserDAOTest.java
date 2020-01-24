package com.champloo.test;

import com.champloo.bean.UserBean;
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
public class UserDAOTest {

    enum Type {REGISTERUSER, GETUSERBYEMAIL, GETUSERBYUSERNAME, GETALLUSERS, UPDATEUSER, DELETEUSER, BLOCKUSER, LOGIN, CHANGEPASSWORD};
    private Type type;
    private Boolean expectedResult;
    private UserDAO userDAO;
    private Object paramForDAO;

    @Before
    public void initialize() {
        userDAO = new UserDAO();
    }

    public UserDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
        this.type = type;
       this.paramForDAO = paramForDAO;
       this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object [] [] {
                {Type.REGISTERUSER, new UserBean("David", "Capuano", "daviddavid", "davidecap00@hotmail.it", "capucapu", new Date(), 1), false},
                {Type.REGISTERUSER, new UserBean("Antonio", "Mancuso", "yeezus", "gokuantony@live.it", "yeezy", new Date(), 1), true},
                {Type.GETUSERBYEMAIL, "gokuantony@live.it", false},
                {Type.GETUSERBYEMAIL, "wasdxd@hotmail.it", true},
                {Type.GETUSERBYUSERNAME, "alexalex", false},
                {Type.GETUSERBYUSERNAME, "okokok", true},
                {Type.GETALLUSERS, null, false},
                {Type.UPDATEUSER, new UserBean("Davide", "Cap", "daviddavid", "davidecap00@hotmail.it", "capucapu", new Date(), 1), true},
                {Type.UPDATEUSER, new UserBean("Tony", "Mank", "yee", "gokuan@live.it", "yeezy", new Date(), 1), false},
                {Type.DELETEUSER, new UserBean("Davide", "Cap", "daviddavid", "davidecap00@hotmail.it", "capucapu", new Date(), 1), true},
                {Type.DELETEUSER, new UserBean("ok", "ok", "ok", "ok", "ok", new Date(), 1), false},
                {Type.BLOCKUSER, "alexalex", true},
                {Type.BLOCKUSER, "ciaociao", false},
                {Type.LOGIN, new String[]{"gokuantony@live.it", "yeezy"}, true},
                {Type.LOGIN, new String[]{"fewiof@hotmail.it", "paswfe"}, false},
                {Type.CHANGEPASSWORD, new Object[]{new UserBean("Salvatore", "Villano", "tabitabi", "tatoresammino@gmail.com", "villivilli", new Date(), 1), "provaaa"}, true},
                {Type.CHANGEPASSWORD, new Object[]{new UserBean("Luca", "Giugliano", "giougly", "lucag@gmail.com", "welcome", new Date(), 1), "provaaa"}, false}
        });
    }

   @org.junit.Test
    public void registerUser() {
        Assume.assumeTrue((type.equals(Type.REGISTERUSER)));
        assertEquals(expectedResult, userDAO.registerUser((UserBean)paramForDAO));

    }

    @org.junit.Test
    public void getUserByEmail() {
        Assume.assumeTrue((type.equals(Type.GETUSERBYEMAIL)));
        UserBean userBean = userDAO.getUserByEmail((String)paramForDAO);
        assertEquals(expectedResult, userBean.isEmpty());
    }

   @org.junit.Test
    public void getUserByUsername() {
        Assume.assumeTrue((type.equals(Type.GETUSERBYUSERNAME)));
        UserBean userBean = userDAO.getUserByUsername((String)paramForDAO);
        assertEquals(expectedResult, userBean.isEmpty());
    }

    @org.junit.Test
    public void getAllUsers() {
        Assume.assumeTrue((type.equals(Type.GETALLUSERS)));
        ArrayList<UserBean> users = userDAO.getAllUsers();
        assertEquals(expectedResult, users.isEmpty());
    }

    @org.junit.Test
    public void updateUser() {
        Assume.assumeTrue((type.equals(Type.UPDATEUSER)));
        assertEquals(expectedResult, userDAO.updateUser((UserBean)paramForDAO));
    }

    @org.junit.Test
    public void deleteUser() {
        Assume.assumeTrue((type.equals(Type.DELETEUSER)));
        assertEquals(expectedResult, userDAO.deleteUser((UserBean)paramForDAO));
    }

    @org.junit.Test
    public void blockUser() {
        Assume.assumeTrue((type.equals(Type.BLOCKUSER)));
        assertEquals(expectedResult, userDAO.blockUser((String)paramForDAO));
    }

    @org.junit.Test
    public void login() {
        Assume.assumeTrue((type.equals(Type.LOGIN)));
        String[] values = ((String[])paramForDAO);
        assertEquals(expectedResult, userDAO.login(values[0], values[1]));
    }

    @org.junit.Test
    public void changePassword() {
        Assume.assumeTrue((type.equals(Type.CHANGEPASSWORD)));
        UserBean userBean = (UserBean)((Object[])paramForDAO)[0];
        String password = (String) ((Object[])paramForDAO)[1];
        assertEquals(expectedResult, userDAO.changePassword(userBean, password));
    }
}