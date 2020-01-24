package com.champloo.model;

import com.champloo.bean.AddressBean;
import com.champloo.bean.PaymentMethodBean;
import com.champloo.storage.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class AddressDAO implements AddressModel
{
	
	public AddressDAO()
	{
		//parametri astratti, aggiungere reali successivamente
		
				try {
					//FINIRE A DISCUTERNE CON DAVID/ ALESSANDRO
                    connectionPool = ConnectionPool.create("jdbc:mysql://@localhost:3306/champloo_store_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "rootroot");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

    /**
     * Insert a new Shipping Address
     * @param newAddress
     * @return address_added
     */
    public boolean insertAddress(AddressBean newAddress) throws SQLException
    {
        int address_added = 0;
        connection = connectionPool.getConnection();

        query="INSERT INTO shipping_addresses(Registred_User, address, city, province, civic_number, CAP) VALUES (?,?,?,?,?,?)";

        try {
            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setInt(1, newAddress.getRegistred_User());
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
            	connectionPool.releaseConnection(connection);
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

        connection = connectionPool.getConnection();

        query="DELETE FROM shipping_addresses WHERE id_address = '"+id_address+"'";

        try {
			statement = connection.createStatement();
            address_deleted = statement.executeUpdate(query);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
            	connectionPool.releaseConnection(connection);
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

        connection = connectionPool.getConnection();

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return address;
    }


    /**
     * Retrieve all the Shipping Addresses of an user
     * @param id_user
     * @return addresses
     */
    public ArrayList<AddressBean> retrieveByUserID(int id_user) throws SQLException
    {
        ArrayList<AddressBean> addresses = new ArrayList<AddressBean>();

        connection = connectionPool.getConnection();

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
            	connectionPool.releaseConnection(connection);
            }
        }

        return addresses;
    }

    private static ConnectionPool connectionPool;
    private Connection connection;
    String query;
    PreparedStatement preparedStatement;		// parametric queries
    Statement statement;						// normal queries
    ResultSet results;					
}
