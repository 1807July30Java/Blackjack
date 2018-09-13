package com.revature.dao;

import java.util.List;
import com.revature.beans.User;

public interface UserDao {

	public List<User> getUsers();

	public User getUserById(int id);

	public int addUser(User u);

	public void updateUser(User u);

	public void deleteUser(User u);
}
