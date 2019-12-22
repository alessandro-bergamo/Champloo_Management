package com.champloo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.champloo.bean.*;
import com.champloo.model.*;

public class UserDAO implements UserModel {
	private Connection connection;
	
	/**
	 * Constructor for the object
	 * @param connection connection already initialized
	 */
	public UserDAO(Connection connection) {
		// ***INIZIALIZZARE CONNESSIONE***
	}
	
	/**
	 * Insert a new user into the database
	 * @param newUser UserBean representing the user to be inserted
	 * @return boolean result of the operation
	 */
	public boolean registerUser(UserBean newUser) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSetUserName = statement.executeQuery("select * from RegisteredUsers where username = " + "'" + newUser.getUsername() + "'");
			ResultSet resultSetEmail = statement.executeQuery("select * from RegisteredUsers where email = " + "'" + newUser.getEmail() + "'");
			
			//prima di inserire l'utente controllo che non ci sia nessun utente con lo stesso username o stessa email
			if(resultSetUserName.getRow() == 0 && resultSetEmail.getRow() == 0) {
				PreparedStatement insertQuery = connection.prepareStatement("insert into RegisteredUsers values(?,?,?,?,?,?,?);");
				insertQuery.setString(2, newUser.getFirstName());
				insertQuery.setString(3, newUser.getSurname());
				insertQuery.setString(4, newUser.getUsername());
				insertQuery.setString(5, newUser.getPassword());
				insertQuery.setString(6, newUser.getEmail());
				insertQuery.setDate(7, newUser.getRegistration_date());
				insertQuery.setInt(8, newUser.getType());
				insertQuery.execute();
			} else 
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Retrieve the user with the specified email
	 * @param user_email the email of the user
	 * @return retrivied user
	 */
	public UserBean getUserByEmail(String user_email) {
		Statement statement;
		UserBean userBean = new UserBean();
		try {
			statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from RegisteredUsers where email = " + "'" + user_email + "'");
			if(resultSetUser.getRow() == 0) {
				return userBean;
			} else {
				userBean.setID(resultSetUser.getInt(1));
				userBean.setFirstName(resultSetUser.getString(2));
				userBean.setSurname(resultSetUser.getString(3));
				userBean.setUsername(resultSetUser.getString(4));
				userBean.setPassword(resultSetUser.getString(5));
				userBean.setEmail(user_email);
				userBean.setRegistration_date(resultSetUser.getDate(7));
				userBean.setType(resultSetUser.getInt(8));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userBean;
	}
	
	/**
	 * Retrieve the user with the specified username
	 * @param username username of the user 
	 * @return retrivied user
	 */
	public UserBean getUserByUsername(String username) {
		Statement statement;
		UserBean userBean = new UserBean();
		try {
			statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from RegisteredUsers where username = " + "'" + username + "'");
			if(resultSetUser.getRow() == 0) {
				return userBean;
			} else {
				userBean.setID(resultSetUser.getInt(1));
				userBean.setFirstName(resultSetUser.getString(2));
				userBean.setSurname(resultSetUser.getString(3));
				userBean.setUsername(username);
				userBean.setPassword(resultSetUser.getString(5));
				userBean.setEmail(resultSetUser.getString(6));
				userBean.setRegistration_date(resultSetUser.getDate(7));
				userBean.setType(resultSetUser.getInt(8));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userBean;
	}
	
	/**
	 * Retrieve all the users in the database
	 *@return all users in the database 
	 */
	public ArrayList<UserBean> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Update the specified user in the database
	 * @param user to be updated
	 * @return boolean result of the operation
	 */
	public boolean updateUser(UserBean user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Delete the specified user in the database
	 * @param user to be deleted
	 * @return boolean result of the operation
	 */
	public boolean deleteUser(UserBean user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Block the specified user
	 * @param user to be blocked
	 * @return boolean result of the operation
	 */
	public boolean blockUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}

