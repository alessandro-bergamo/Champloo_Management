package com.champloo.model;

import com.champloo.bean.AddressBean;
import com.champloo.bean.PaymentMethodBean;
import com.champloo.storage.ConnectionPool;

import java.sql.*;
import java.util.HashSet;

public class AddressDAO implements AddressModel
{

    /**
     * Insert a new Shipping Address
     * @param newAddress
     * @return address_added
     */
    public boolean insertAddress(AddressBean newAddress) throws SQLException
    {
        int address_added = 0;

        query="INSERT INTO shipping_addresses(Registred_User, address, city, province, civic_number, CAP) VALUES (?,?,?,?,?,?)";

        try {
            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, newAddress.getAddress());
            preparedStatement.setString(3, newAddress.getCity());
            preparedStatement.setString(4, newAddress.getProvince());
            preparedStatement.setInt(5, newAddress.getCivic_number());
            preparedStatement.setInt(6, newAddress.getCAP());

            address_added = preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return (address_added != 0);
    }


    /**
     * Deletes a Shipping Address
     * @param id_address
     * @return address_deleted
     */
    public boolean deleteAddress(int id_address) throws SQLException
    {
        int address_deleted = 0;

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query="DELETE FROM shipping_addresses WHERE id_address = '"+id_address+"'";

        try {
            statement = connection.createStatement();
            address_deleted = statement.executeUpdate(query);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return (address_deleted != 0);
    }


    /**
     * Retrieve a Shipping Address by his ID
     * @param id_address
     * @return address
     */
    public AddressBean retrieveByID(int id_address) throws SQLException
    {
        AddressBean address = new AddressBean();

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query="SELECT * FROM shipping_addresses WHERE id_address = ?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id_address);

            results = preparedStatement.executeQuery();

            address.setId_address(results.getInt(1));
            address.setRegistred_User(results.getInt(2));
            address.setAddress(results.getString(3));
            address.setCity(results.getString(4));
            address.setProvince(results.getString(5));
            address.setCivic_number(results.getInt(6));
            address.setCAP(results.getInt(7));
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return address;
    }


    /**
     * Retrieve all the Shipping Addresses of an user
     * @param id_user
     * @return addresses
     */
    public HashSet<AddressBean> retrieveByUsername(int id_user) throws SQLException
    {
        HashSet<AddressBean> addresses = new HashSet<>();

        try {
            connection = connectionPool.InitializeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query="SELECT * FROM shipping_addresses WHERE Registred_User = ?";

        try {

            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setInt(1, id_user);

            results=preparedStatement.executeQuery();

            while(results.next())
            {
                AddressBean address = new AddressBean();

                address.setId_address(results.getInt(1));
                address.setRegistred_User(results.getInt(2));
                address.setAddress(results.getString(3));
                address.setCity(results.getString(4));
                address.setProvince(results.getString(5));
                address.setCivic_number(results.getInt(6));
                address.setCAP(results.getInt(7));

                addresses.add(address);
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

        return addresses;
    }



    private ConnectionPool connectionPool = new ConnectionPool();
    private Connection connection;
    String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results;

}
