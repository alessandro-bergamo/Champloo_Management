package com.champloo.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.champloo.bean.*;
import com.champloo.model.*;

public class UserDAO implements UserModel {
	private Connection connection;
	
	public UserDAO() {
		// ***INIZIALIZZARE CONNESSIONE***
	}
	
	public boolean registerUser(UserBean newUser) {
		// TODO Auto-generated method stub
		return false;
	}

	public UserBean getUserByEmail(String user_email) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserBean getUserBySurname(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<UserBean> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateUser(UserBean user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteUser(UserBean user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean blockUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}

