package com.champloo.model;

import com.champloo.bean.PaymentMethodBean;
import com.champloo.storage.ConnectionPool;

import java.sql.*;
import java.util.HashSet;


public class PaymentMethodDAO implements PaymentMethodModel
{

    /**
     * Insert a new Payment Method
     * @param newPMethod
     * @return method_added
     */
    public boolean insertPMethod(PaymentMethodBean newPMethod) throws SQLException
    {
        int method_added = 0;

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "SELECT * FROM payment_methods WHERE card_number = '"+newPMethod.getCard_number()+"'";

        statement = connection.createStatement();
        results = statement.executeQuery(query);

        if(results.first())
            return false;

        query="INSERT INTO payment_methods(Registred_User, card_number, card_bank, card_cvc, expiry_date, registration_method_date, card_owener) VALUES (?,?,?,?,?,?,?)";

        try {
            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, newPMethod.getCard_number());
            preparedStatement.setString(3, newPMethod.getCard_bank());
            preparedStatement.setInt(4, newPMethod.getCard_cvc());
            preparedStatement.setDate(5, java.sql.Date.valueOf(newPMethod.getExpiry_date()));
            preparedStatement.setDate(6, java.sql.Date.valueOf(newPMethod.getRegistration_method_date()));
            preparedStatement.setString(7, newPMethod.getCard_owner());

            method_added = preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return (method_added != 0);	//qualunqe valore diverso da 0 indica il successo dell'operazione
    }


    /**
     * Deletes a Payment Method
     * @param PMethod
     * @return method_deleted
     */
    public boolean deletePMethod(PaymentMethodBean PMethod) throws SQLException
    {
        int method_deleted = 0;

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query="DELETE FROM payement_methods WHERE card_number = '"+PMethod.getCard_number()+"'";

        try {
            statement = connection.createStatement();
            method_deleted = statement.executeUpdate(query);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return (method_deleted != 0);
    }


    /**
     * Retrieve a Payment Method by his Card Number
     * @param card_number
     * @return payment_method
     */
    public PaymentMethodBean retrieveByID(String card_number) throws SQLException
    {

        PaymentMethodBean PMethod = new PaymentMethodBean();

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query="SELECT * FROM payment_methods WHERE card_number = ?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, card_number);

            results = preparedStatement.executeQuery();

            PMethod.setUsername(results.getString(1));
            PMethod.setCard_number(results.getString(2));
            PMethod.setCard_bank(results.getString(3));
            PMethod.setCard_cvc(results.getInt(4));
            PMethod.setExpiry_date(results.getDate(5).toLocalDate());
            PMethod.setRegistration_method_date(results.getDate(6).toLocalDate());
            PMethod.setCard_owner(results.getString(7));
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return PMethod;
    }


    /**
     * Retrieve all the Payment Methods of an user
     * @param username
     * @return payment_methods
     */
    public HashSet<PaymentMethodBean> retrieveByUsername(String username) throws SQLException
    {
        HashSet<PaymentMethodBean> payment_methods = new HashSet<>();

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query="SELECT * FROM payement_methods WHERE card_owner = ?";

        try {

            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setString(1, username);

            results=preparedStatement.executeQuery();

            while(results.next())
            {
                PaymentMethodBean PMethod = new PaymentMethodBean();

                PMethod.setUsername(results.getString(1));
                PMethod.setCard_number(results.getString(2));
                PMethod.setCard_bank(results.getString(3));
                PMethod.setCard_cvc(results.getInt(4));
                PMethod.setExpiry_date(results.getDate(5).toLocalDate());
                PMethod.setRegistration_method_date(results.getDate(6).toLocalDate());
                PMethod.setCard_owner(results.getString(7));

                payment_methods.add(PMethod);
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return payment_methods;
    }



    private ConnectionPool connectionPool = new ConnectionPool();
    private Connection connection;
    String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results;

}
