package com.niit.socialmediacontroller.controller;

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


import com.niit.socialmediabackend.dao.UserDao;
import com.niit.socialmediabackend.models.User;

@Controller
public class UserController {
	@Autowired
	UserDao userDAO;
	
	
	@RequestMapping(value= "/usersignup", method=RequestMethod.POST)
	public ResponseEntity<User> signUp(@RequestBody User u, ModelMap model)
	{
		userDAO.addUser(u);
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	
	@RequestMapping(value="/usersignin",method=RequestMethod.POST)
	public ResponseEntity<User> signIn(@RequestBody User u,HttpSession session)
	{
		System.out.println(u.getUser_mailaddress());
		User user=userDAO.getUser(u.getUser_mailaddress(),u.getUser_password());
		session.setAttribute("loggedInUser",user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody User u) {
		
		userDAO.updateUser(u);
		
		 return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<User> userlogout(HttpSession session) {
		User u=(User) session.getAttribute("loggedInUser");
		session.removeAttribute("loggedInUser");
		session.invalidate();
		 return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	

}
	


