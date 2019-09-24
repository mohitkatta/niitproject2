package com.niit.socialmediabackend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.socialmediabackend.models.User;
@Repository("userDAO")
public interface UserDao {
	boolean addUser(User user);
	User getUser(String email,String password);
	boolean updateUser(User u);
	//boolean deleteUser(String em);
	User checkEmail(String email);
	List<User> listUsers();

}
