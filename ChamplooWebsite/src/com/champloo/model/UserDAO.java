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
		this.connection = connection;
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
				insertQuery.setDate(7, convert(newUser.getRegistration_date()));
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
				resultSetUser.first();
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
		ArrayList<UserBean> arrayList = new ArrayList<UserBean>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSetUsers = statement.executeQuery("select * from RegisteredUsers");
			while(resultSetUsers.next()) {
				UserBean userBean = new UserBean();
				userBean.setID(resultSetUsers.getInt(1));
				userBean.setFirstName(resultSetUsers.getString(2));
				userBean.setSurname(resultSetUsers.getString(3));
				userBean.setUsername(resultSetUsers.getString(4));
				userBean.setPassword(resultSetUsers.getString(5));
				userBean.setEmail(resultSetUsers.getString(6));
				userBean.setRegistration_date(resultSetUsers.getDate(7));
				userBean.setType(resultSetUsers.getInt(8));
				
				arrayList.add(userBean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	/**
	 * Update the specified user in the database
	 * @param user to be updated
	 * @return boolean result of the operation
	 */
	public boolean updateUser(UserBean user) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from RegisteredUsers where username = " + "'" + user.getUsername() + "'");
			if(resultSetUser.getRow() == 0) {
				return false;
			}
			
			Statement updateStatement = connection.createStatement();
			statement.executeUpdate(this.updateUserFields("firstname", user.getUsername(), user.getFirstName()));
			statement.executeUpdate(this.updateUserFields("surname", user.getUsername(), user.getSurname()));
			statement.executeUpdate(this.updateUserFields("password_user", user.getUsername(), user.getPassword()));
			statement.executeUpdate(this.updateUserFields("email", user.getUsername(), user.getEmail()));
			statement.executeUpdate(this.updateUserFields("password_user", user.getUsername(), user.getPassword()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Delete the specified user in the database
	 * @param user to be deleted
	 * @return boolean result of the operation
	 */
	public boolean deleteUser(UserBean user) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from RegisteredUsers where username = " + "'" + user.getUsername() + "'");
			if(resultSetUser.getRow() == 0) {
				return false;
			}
			
			Statement stmt = connection.createStatement();
			String SQL = "DELETE FROM RegisteredUsers WHERE username = '"+user.getFirstName()+"'";
			stmt.executeUpdate(SQL);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
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
	
	//metodo di servizio interno alla classe. Rappresenta la stringa SQL per l'update dei field
	private static String updateUserFields(String set, String username, String value) {

        return "update RegisteredUsers set " +  set + "='" + value + "' where username ='" + username + "'";  

    }
	
	//metodo di servizio interno alla classe. Converte uno java.util.Date in java.sql.Date( necessario per quando si va a registrare l'utente in registerUser)
	private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

}

