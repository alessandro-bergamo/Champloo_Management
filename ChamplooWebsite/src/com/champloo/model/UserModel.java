package com.champloo.model;

import java.util.ArrayList;

import com.champloo.bean.*;

public interface UserModel {
	public boolean registerUser(UserBean newUser);
	public UserBean getUserByEmail(String user_email);
	public UserBean getUserByUsername(String username);
	public ArrayList<UserBean> getAllUsers();
	public boolean updateUser(UserBean user);
	public boolean deleteUser(UserBean user);
	public boolean blockUser(String username);
}
