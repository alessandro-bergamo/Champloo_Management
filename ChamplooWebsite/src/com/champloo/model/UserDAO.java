package com.champloo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.champloo.bean.*;
import com.champloo.storage.ConnectionPool;

public class UserDAO implements UserModel {
	//m
	/**
	 * Constructor for the object
	 */
	public UserDAO() {
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
	 * Insert a new user into the database
	 * @param newUser UserBean representing the user to be inserted
	 * @return boolean result of the operation
	 */
	public boolean registerUser(UserBean newUser) {
		try {
			connection = connectionPool.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSetUserName = statement.executeQuery("select * from registred_users where username = " + "'" + newUser.getUsername() + "'");
			resultSetUserName.first();
			int rowUsername = resultSetUserName.getRow();
			Statement statement2 = connection.createStatement();
			ResultSet resultSetEmail = statement2.executeQuery("select * from registred_users where email = " + "'" + newUser.getEmail() + "'");
			resultSetEmail.first();
			int rowEmail = resultSetEmail.getRow();
			
			//prima di inserire l'utente controllo che non ci sia nessun utente con lo stesso username o stessa email
			if(rowUsername == 0 && rowEmail == 0) {
				PreparedStatement insertQuery = connection.prepareStatement("insert into registred_users(firstname, surname, username, email, password_user, registration_date, type_user) values(?,?,?,?,?,?,?);");
				insertQuery.setString(1, newUser.getFirstName());
				insertQuery.setString(2, newUser.getSurname());
				insertQuery.setString(3, newUser.getUsername());
				insertQuery.setString(4, newUser.getEmail());
				insertQuery.setString(5, newUser.getPassword());
				insertQuery.setDate(6, convert(newUser.getRegistration_date()));
				insertQuery.setInt(7, newUser.getType());
				insertQuery.executeUpdate();
				return true;
			} else 
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return true;
	}
	
	/**
	 * Retrieve the user with the specified email
	 * @param user_email the email of the user
	 * @return retrivied user. It is empty if no email match is found, else return a not empty user
	 */
	public UserBean getUserByEmail(String user_email) {
		
		Statement statement;
		UserBean userBean = new UserBean();
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from registred_users where email = " + "'" + user_email + "'");
			resultSetUser.first();
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
		} finally {
			connectionPool.releaseConnection(connection);
		}
		
		return userBean;
	}
	
	/**
	 * Retrieve the user with the specified username
	 * @param username username of the user 
	 * @return retrivied user. It is empty if no username match is found, else return a not empty user
	 */
	public UserBean getUserByUsername(String username) {
		Statement statement;
		UserBean userBean = new UserBean();
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from registred_users where username = " + "'" + username + "'");
			resultSetUser.first();
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
		} finally {
			connectionPool.releaseConnection(connection);
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
			connection = connectionPool.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSetUsers = statement.executeQuery("select * from registred_users");
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
		} finally {
			connectionPool.releaseConnection(connection);
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
			connection = connectionPool.getConnection();
			
			Statement statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from registred_users where username = '"+user.getUsername()+"'");
			
			if(resultSetUser.first()) 
			{
				statement.executeUpdate(this.updateUserFields("firstname", user.getUsername(), user.getFirstName()));
				statement.executeUpdate(this.updateUserFields("surname", user.getUsername(), user.getSurname()));
			} else
				return false;
			//statement.executeUpdate(this.updateUserFields("password_user", user.getUsername(), user.getPassword()));
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(connection);
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
			connection = connectionPool.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from registred_users where username = " + "'" + user.getUsername() + "'");
			resultSetUser.first();
			if(resultSetUser.getRow() == 0) {
				return false;
			}
			
			Statement stmt = connection.createStatement();
			String SQL = "DELETE FROM registred_users WHERE username = '"+user.getUsername()+"'";
			stmt.executeUpdate(SQL);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return true;
	}
	
	/**
	 * Block the specified user
	 * @param username user's username to be blocked
	 * @return boolean result of the operation
	 */
	public boolean blockUser(String username) {
		try {
			connection = connectionPool.getConnection();
			Statement statement = connection.createStatement();
			String SQL = "update registred_users set type_user ='" + UserBean.BANNED_USER + "' where username ='" + username + "'"; 
			statement.executeUpdate(SQL);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return true;
	}
	
	/**
	 * allows user to log in
	 * @param email email to be logged
	 * @param password password to be logged associated to email
	 * @return boolean result of the operation
	 */
	public boolean login(String email, String password) {
		try {
			connection = connectionPool.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from registred_users where email = " + "'" + email + "'" + "and password_user=" + "'"+ password + "'" + "and type_user != 99");
			if(!resultSetUser.first()) {
				return false;
			} else
				return true;
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(connection);
		}
		
		return true;
	}

	/**
	 * allows user to change password
	 * @param userBean user to be updated
	 * @param newPassword new password
	 * @return boolean result of the operation
	 */
	public boolean changePassword(UserBean userBean, String newPassword) {
		try {
			connection = connectionPool.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSetUser = statement.executeQuery("select * from registred_users where username = " + "'" + userBean.getUsername() + "'");

			if(resultSetUser.first()) {
				Statement updateStatement = connection.createStatement();
				statement.executeUpdate(this.updateUserFields("password_user", userBean.getUsername(), newPassword));
				return true;
			} else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(connection);
		}

		return true;
	}
	
	//metodo di servizio interno alla classe. Rappresenta la stringa SQL per l'update dei field
	private static String updateUserFields(String set, String username, String value) {

        return "update registred_users set " +  set + "='" + value + "' where username ='" + username + "'";  

    }
	
	//metodo di servizio interno alla classe. Converte uno java.util.Date in java.sql.Date( necessario per quando si va a registrare l'utente in registerUser)
	private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

	private static ConnectionPool connectionPool;
	private Connection connection;
}

