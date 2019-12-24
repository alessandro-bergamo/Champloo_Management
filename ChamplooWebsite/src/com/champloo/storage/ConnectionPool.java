package com.champloo.storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionPool
{

    public ConnectionPool()
    {
        connection = null;
    }

    public Connection InitializeConnection() throws Exception
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://root:root@localhost:3306/champloo_store_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            connection=DriverManager.getConnection(url);

            return connection;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }


    public Connection getConnection()
    {
        return connection;
    }


    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }



    private Connection connection;

}
