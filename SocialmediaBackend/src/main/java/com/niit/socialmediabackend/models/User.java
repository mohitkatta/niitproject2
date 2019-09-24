package com.niit.socialmediabackend.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User
{	
	@Id
	private String user_mailaddress;
	private String user_name;
	private int user_phonenumber;
	private String user_password;
	private String role;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_phonenumber() {
		return user_phonenumber;
	}
	public void setUser_phonenumber(int user_phonenumber) {
		this.user_phonenumber = user_phonenumber;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_mailaddress() {
		return user_mailaddress;
	}
	public void setUser_mailaddress(String user_mailaddress) {
		this.user_mailaddress = user_mailaddress;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
