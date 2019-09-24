package com.niit.socialmediacontroller.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.socialmediabackend.dao.FriendsDao;
import com.niit.socialmediabackend.dao.UserDao;
import com.niit.socialmediabackend.models.Friends;
import com.niit.socialmediabackend.models.User;


@Controller
public class FriendsController {
	
	@Autowired
	FriendsDao friendsDao;
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/suggestedFriends",method=RequestMethod.GET)
	public ResponseEntity<List<User>> suggestfriends(HttpSession session)
	{
		User u=(User) session.getAttribute("loggedInUser");
		List<User> suggestedfriends=friendsDao.suggestedfriends(u.getUser_mailaddress());
		return new ResponseEntity<List<User>>(suggestedfriends,HttpStatus.OK);
		}
	
	@RequestMapping("/friendsignin")
	public ResponseEntity<Friends> signIn(@RequestBody Friends user, ModelMap model)
	{
		friendsDao.addFriend(user);
		
		return new ResponseEntity<Friends>(user,HttpStatus.OK);
		}
	
	
	@RequestMapping(value = "/registerfriend", method = RequestMethod.POST)
    public ResponseEntity<Friends> addFriend(@RequestBody String email,HttpSession session) {
		Friends friend=new Friends();
		User u=(User) session.getAttribute("loggedInUser");
		friend.setFromuser(u);
		User touser=userDao.checkEmail(email);
		friend.setTouser(touser);
		friend.setStatus('p');
		friendsDao.addFriend(friend);
		
		 return new ResponseEntity<Friends>(friend,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/updatefriend", method = RequestMethod.POST)
    public ResponseEntity<Friends> updateUser(@RequestBody Friends u, ModelMap model) {
		
		friendsDao.updateFriend(u);
		
		 return new ResponseEntity<Friends>(u,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletefriend", method = RequestMethod.POST)
    public ResponseEntity<Friends> adduser(@RequestBody Friends friend,ModelMap model) {
		
		friendsDao.deleteFriend(friend);
		
		 return new ResponseEntity<Friends>(friend,HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<List<Friends>> pending_requests(HttpSession session)
	{
		User u=(User) session.getAttribute("loggedInUser");
		List<Friends> pendingrequests=friendsDao.pendingrequests(u.getUser_mailaddress());
		return new ResponseEntity<List<Friends>>(pendingrequests,HttpStatus.OK);
		}

	
	@RequestMapping(value = "/acceptrequest/{status}", method = RequestMethod.PUT)
	public ResponseEntity<Friends> accept_requests(@PathVariable("status") char status,@RequestBody Friends friend,HttpSession session)
	{
		User u=(User) session.getAttribute("loggedInUser");
		
		friend.setStatus(status);
		friendsDao.updateFriend(friend);
		return new ResponseEntity<Friends>(friend,HttpStatus.OK);
		}
//	sample
	@RequestMapping(value = "/friendslist", method = RequestMethod.GET)
	public ResponseEntity<List<Friends>> friends_list(HttpSession session)
	{
		User u=(User) session.getAttribute("loggedInUser");
		List<Friends> friendslist=friendsDao.listFriends(u.getUser_mailaddress());
		
		return new ResponseEntity <List<Friends>>(friendslist,HttpStatus.OK);
		}

}


