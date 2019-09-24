package com.niit.socialmediabackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Friends {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int friendid;
	@ManyToOne
	User fromuser;
	@ManyToOne
	User touser;
	char status;
	public int getFriendid() {
		return friendid;
	}
	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}
	
	public User getFromuser() {
		return fromuser;
	}
	public void setFromuser(User fromuser) {
		this.fromuser = fromuser;
	}
	public User getTouser() {
		return touser;
	}
	public void setTouser(User touser) {
		this.touser = touser;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
