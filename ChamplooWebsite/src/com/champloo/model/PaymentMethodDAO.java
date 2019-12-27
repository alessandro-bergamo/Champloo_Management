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

        query="INSERT INTO payment_methods(Registred_User, card_number, card_bank, card_cvc, expiry_date, registration_method_date, card_owner) VALUES (?,?,?,?,?,?,?)";

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
     * @param id_method
     * @return method_deleted
     */
    public boolean deletePMethod(int id_method) throws SQLException
    {
        int method_deleted = 0;

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query="DELETE FROM payement_methods WHERE card_number = '"+id_method+"'";

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
    public PaymentMethodBean retrieveByCardNumber(String card_number) throws SQLException
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

            PMethod.setId_method(results.getInt(1));
            PMethod.setUsername(results.getString(2));
            PMethod.setCard_number(results.getString(3));
            PMethod.setCard_bank(results.getString(4));
            PMethod.setCard_cvc(results.getInt(5));
            PMethod.setExpiry_date(results.getDate(6).toLocalDate());
            PMethod.setRegistration_method_date(results.getDate(7).toLocalDate());
            PMethod.setCard_owner(results.getString(8));
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
     * @param user_id
     * @return payment_methods
     */
    public HashSet<PaymentMethodBean> retrieveByUserID(int user_id) throws SQLException
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

            preparedStatement.setInt(1, user_id);

            results=preparedStatement.executeQuery();

            while(results.next())
            {
                PaymentMethodBean PMethod = new PaymentMethodBean();

                PMethod.setId_method(results.getInt(1));
                PMethod.setUsername(results.getString(2));
                PMethod.setCard_number(results.getString(3));
                PMethod.setCard_bank(results.getString(4));
                PMethod.setCard_cvc(results.getInt(5));
                PMethod.setExpiry_date(results.getDate(6).toLocalDate());
                PMethod.setRegistration_method_date(results.getDate(7).toLocalDate());
                PMethod.setCard_owner(results.getString(8));

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
