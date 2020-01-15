package com.champloo.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.champloo.bean.*;

public interface UserModel 
{
	public boolean registerUser(UserBean newUser) throws SQLException;
	
	public UserBean getUserByEmail(String user_email) throws SQLException;
	
	public UserBean getUserByUsername(String username) throws SQLException;
	
	public ArrayList<UserBean> getAllUsers() throws SQLException;
	
	public boolean updateUser(UserBean user) throws SQLException;
	
	public boolean deleteUser(UserBean user) throws SQLException;
	
	public boolean blockUser(String username) throws SQLException;
	
	public boolean login(String username, String password) throws SQLException;
}
