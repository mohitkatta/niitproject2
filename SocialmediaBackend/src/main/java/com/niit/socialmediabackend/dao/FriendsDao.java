package com.niit.socialmediabackend.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.socialmediabackend.models.Friends;
import com.niit.socialmediabackend.models.User;
@Repository("friendsDAO")
public interface FriendsDao {
	boolean addFriend(Friends user);
	Friends getFriend(String s,String s2);
	boolean updateFriend(Friends u);
	boolean deleteFriend(Friends f);
	Friends findFriend(int friendid);
	List<Friends> listFriends(String email);
	List<User> suggestedfriends(String email);
	List<Friends> pendingrequests(String email);
}
