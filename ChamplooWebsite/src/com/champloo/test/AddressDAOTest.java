package com.champloo.test;

import com.champloo.bean.AddressBean;
import com.champloo.bean.UserBean;
import com.champloo.model.AddressDAO;
import com.champloo.model.UserDAO;
import com.champloo.storage.ConnectionPool;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.jws.soap.SOAPBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
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
public class AddressDAOTest {
    enum Type{INSERTADDRESS, DELETEADDRESS, RETRIEVEBYID, RETRIEVEBYUSERID};
    private Type type;
    private Boolean expectedResult;
    private static AddressDAO addressDAO;
    private Object paramForDAO;
    static ConnectionPool connectionPool;
    static Connection connection;


    @BeforeClass
    public static void initialize() {
        addressDAO = new AddressDAO("");
        try {
            String alterSQLUsers = "ALTER TABLE registred_users AUTO_INCREMENT = 1";
            String alterSQLAddresses = "ALTER TABLE shipping_addresses AUTO_INCREMENT = 1";
            connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/testing_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
            connection = connectionPool.getConnection();
            Statement alterUsers = connection.createStatement();
            Statement alterAddresses = connection.createStatement();
            alterUsers.execute(alterSQLUsers);
            alterAddresses.execute(alterSQLAddresses);
            PreparedStatement insertUser = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('David','Capuano','david98','davidecap00@hotmail.it','capucapu','2020-02-06','1');");
            PreparedStatement insertUser2 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Alex','Esposito','alexBab','alex98@hotmail.it','freepsw','2020-02-06','1');");
            PreparedStatement insertUser3 = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values('Maria','Rossi','mRed','mrossi@hotmail.it','rossi46','2020-02-06','1');");
            PreparedStatement insertQuery = connection.prepareStatement("insert into shipping_addresses(Registred_User, address, city, province, civic_number, CAP) values('1','Via Piave','Roma','RM','15','00120');");
            PreparedStatement insertQuery2 = connection.prepareStatement("insert into shipping_addresses(Registred_User, address, city, province, civic_number, CAP) values('2','Via Roma','Napoli','NA','25','80121');");
            PreparedStatement insertQuery3 = connection.prepareStatement("insert into shipping_addresses(Registred_User, address, city, province, civic_number, CAP) values('3','Via Manzoni','Milano','MI','12','20130');");

            insertUser.executeUpdate();
            insertUser2.executeUpdate();
            insertUser3.executeUpdate();
            insertQuery.executeUpdate();
            insertQuery2.executeUpdate();
            insertQuery3.executeUpdate();

        }catch(SQLException e) {
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
            statement.executeUpdate("DELETE FROM shipping_addresses");
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate("DELETE FROM registred_users");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }
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
                {Type.DELETEADDRESS, new Integer(1), true},
                {Type.DELETEADDRESS, new Integer(5), false},
                {Type.RETRIEVEBYID, new Integer(2), true},
                {Type.RETRIEVEBYID, new Integer(10), false},
                {Type.RETRIEVEBYUSERID, new Integer(3), false},
                {Type.RETRIEVEBYUSERID, new Integer(8), true}
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
