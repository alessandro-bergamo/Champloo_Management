package com.champloo.test;

import com.champloo.bean.PaymentMethodBean;
import com.champloo.model.PaymentMethodDAO;
import com.champloo.storage.ConnectionPool;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    private static PaymentMethodDAO paymentMethodDAO;
    private Object paramForDAO;
    static ConnectionPool connectionPool;
    static Connection connection;

    @BeforeClass
    public static void initalize() {
        paymentMethodDAO = new PaymentMethodDAO("");
        try{
            connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/testing_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
            connection = connectionPool.getConnection();
            String alterSQLUsers = "ALTER TABLE registred_users AUTO_INCREMENT = 1";
            String alterSQLPayment = "ALTER TABLE payment_methods AUTO_INCREMENT = 1";
            Statement alterUsers = connection.createStatement();
            Statement alterPayment = connection.createStatement();
            alterUsers.execute(alterSQLUsers);
            alterPayment.execute(alterSQLPayment);
            PreparedStatement insertUser = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('David','Capuano','david98','davidecap00@hotmail.it','capucapu','2020-02-06','1');");
            PreparedStatement insertUser2 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Alex','Esposito','alexBab','alex98@hotmail.it','freepsw','2020-02-06','1');");
            PreparedStatement insertUser3 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Maria','Rossi','mRed','mrossi@hotmail.it','rossi46','2020-02-06','1');");
            PreparedStatement insertQuery = connection.prepareStatement("INSERT INTO payment_methods(Registred_User, card_number, card_bank, card_cvc, expiry_date, registration_method_date, card_owner) VALUES ('1','2221001234123456','Mastercard','481','2023-05-06','2020-02-06','Antonio Mancuso');");
            PreparedStatement insertQuery2 = connection.prepareStatement("INSERT INTO payment_methods(Registred_User, card_number, card_bank, card_cvc, expiry_date, registration_method_date, card_owner) VALUES ('2','4000123456789010','Visa','472','2023-01-06','2020-02-06','David Capuano');");
            PreparedStatement insertQuery3 = connection.prepareStatement("INSERT INTO payment_methods(Registred_User, card_number, card_bank, card_cvc, expiry_date, registration_method_date, card_owner) VALUES ('3','400023676788010','Visa','330','2026-06-01','2020-02-06','Luca Giugliano');");

            insertUser.executeUpdate();
            insertUser2.executeUpdate();
            insertUser3.executeUpdate();
            insertQuery.executeUpdate();
            insertQuery2.executeUpdate();
            insertQuery3.executeUpdate();
        }catch (SQLException e) {
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
            statement.executeUpdate("DELETE FROM payment_methods");
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate("DELETE FROM registred_users");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public PaymentMethodDAOTest(Type type, Object paramForDAO, Boolean expectedResult) {
        this.type = type;
        this.expectedResult = expectedResult;
        this.paramForDAO = paramForDAO;
    }

    @Parameterized.Parameters
    public static Collection objects() {
        return Arrays.asList(new Object[][] {
                {Type.INSERTPMETHOD, new PaymentMethodBean(301, "5481007026413598", "Visa", "Antonio Mancuso", 1, "2025-02-02", LocalDate.now()), true},
                {Type.INSERTPMETHOD, new PaymentMethodBean(482, "2221001234123456", "MasterCard", "Salvatore Celentano", 2, "2021-11-12", LocalDate.now()), false},
                {Type.DELETEPMETHOD, new Integer(2), true},
                {Type.DELETEPMETHOD, new Integer(8), false},
                {Type.RETRIEVEBYCARDNUMBER, "2221001234123456", false},
                {Type.RETRIEVEBYCARDNUMBER, "1111125453453543", true},
                {Type.RETRIEVEBYUSERID, new Integer(1), false},
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
