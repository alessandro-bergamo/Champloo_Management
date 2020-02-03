package com.champloo.test;

import com.champloo.bean.UserBean;
import com.champloo.model.UserDAO;
import com.champloo.storage.ConnectionPool;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    private static UserDAO userDAO;
    private Object paramForDAO;
    static ConnectionPool connectionPool;
    static Connection connection;

    @BeforeClass
    public static void initialize() {
        userDAO = new UserDAO("");
        try {
            connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/testing_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
            connection = connectionPool.getConnection();
            PreparedStatement insertQuery = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('David','Capuano','david98','davidecap00@hotmail.it','capucapu','2020-02-06','1');");
            PreparedStatement insertQuery2 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Alex','Esposito','alexBab','alex98@hotmail.it','freepsw','2020-02-06','1');");
            PreparedStatement insertQuery3 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Maria','Rossi','mRed','mrossi@hotmail.it','rossi46','2020-02-06','1');");
            PreparedStatement insertQuery4 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Rosario','Crescenzo','rosCz','roscres@outlook.it','cres00','2020-02-06','1');");
            PreparedStatement insertQuery5 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Tina','Esposito','tinEs98','tina@hotmail.it','espyus','2020-02-06','1');");

            insertQuery.executeUpdate();
            insertQuery2.executeUpdate();
            insertQuery3.executeUpdate();
            insertQuery4.executeUpdate();
            insertQuery5.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @AfterClass
    public static void tearDown() {
       try {
           connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/testing_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
           connection = connectionPool.getConnection();
           Statement statement = connection.createStatement();
           statement.executeUpdate("DELETE FROM registred_users");

       }catch (Exception e) {
            e.printStackTrace();
       }finally {
           connectionPool.releaseConnection(connection);
       }

    }

    public UserDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
        this.type = type;
       this.paramForDAO = paramForDAO;
       this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object [] [] {
                {Type.REGISTERUSER, new UserBean("Mario", "Rossi", "supermario", "mariorossi@hotmail.it", "red9898", new Date(), 1), true},
                {Type.REGISTERUSER, new UserBean("David", "Capuano", "david98", "davidecap00@hotmail.it", "capucapu", new Date(), 1), false},
                {Type.GETUSERBYEMAIL, "mariorossi@hotmail.it", false},
                {Type.GETUSERBYEMAIL, "lucagiugliano@outlook.it", true},
                {Type.GETUSERBYUSERNAME, "alexBab", false},
                {Type.GETUSERBYUSERNAME, "kanyeZus", true},
                {Type.GETALLUSERS, null, false},
                {Type.UPDATEUSER, new UserBean("Rosaria", "Crescenzi", "rosCz", "roscres@outlook.it", "cres00", new Date(), 1), true},
                {Type.UPDATEUSER, new UserBean("Tony", "Mank", "yee", "gokuan@live.it", "yeezy", new Date(), 1), false},
                {Type.DELETEUSER, new UserBean("Tina", "Esposito", "tinEs98", "tina@hotmail.it", "espyus", new Date(), 1), true},
                {Type.DELETEUSER, new UserBean("Nicola", "Marciano", "marcix", "nikmar@gmail.com", "nicoo98", new Date(), 1), false},
                {Type.BLOCKUSER, "alexBab", true},
                {Type.BLOCKUSER, "yeezy", false},
                {Type.LOGIN, new String[]{"roscres@outlook.it", "cres00"}, true},
                {Type.LOGIN, new String[]{"footbalpassion@hotmail.it", "calcio00"}, false},
                {Type.CHANGEPASSWORD, new Object[]{new UserBean("Maria", "Rossi", "mRed", "mrossi@hotmail.it", "rossi46", new Date(), 1), "nuovaPsw"}, true},
                {Type.CHANGEPASSWORD, new Object[]{new UserBean("Luca", "Giugliano", "giougly", "lucag@gmail.com", "welcome", new Date(), 1), "provaaa"}, false}
        });
    }

   @org.junit.Test
    public void registerUser() {
        Assume.assumeTrue((type.equals(Type.REGISTERUSER)));
        System.out.println((UserBean)paramForDAO);
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