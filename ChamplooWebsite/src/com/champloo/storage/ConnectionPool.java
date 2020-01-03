/*
    ConnectionPool by DeviDee
*/

package com.champloo.storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionPool
{
    private static final int MAX_CONNECTIONS = 200;

    private String url;
    private String user;
    private String password;
    private List<Connection> connections;
    private List<Connection> usedConnections;

    public ConnectionPool(String url, String user, String password, List pool)
    {
        this.url = url;
        this.user = user;
        this.password = password;
        connections = pool;
    }
    
    public static ConnectionPool create(String url, String user, String password) throws SQLException
    {
        List<Connection> pool= new ArrayList<Connection>(MAX_CONNECTIONS);
        for(int i = 0; i < MAX_CONNECTIONS; i++)
        {
            pool.add(createConnection(url,user,password));
        }

        return new ConnectionPool(url,user,password,pool);
    }

    public Connection getConnection()
    {
        Connection conn = connections.remove(connections.size() - 1);
        usedConnections.add(conn);
        return conn;
    }

    public boolean releaseConnection(Connection conn)
    {
        connections.add(conn);
        return usedConnections.remove(conn);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
    }

    public Map<String,Integer> getSize()
    {
        Map<String, Integer> connectionsCounter = new HashMap<String, Integer>();
        connectionsCounter.put("freeConnections", connections.size());
        connectionsCounter.put("usedConnections", usedConnections.size());
        return connectionsCounter;
    }


}