package com.niit.socialmediacontroller.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.socialmediabackend.dao.BlogPostLikesDao;
import com.niit.socialmediabackend.models.BlogPost;
import com.niit.socialmediabackend.models.BlogPostLikes;
import com.niit.socialmediabackend.models.ErrorClass;
import com.niit.socialmediabackend.models.User;

@RestController
public class BlogPostLikesController {
	@Autowired
	private BlogPostLikesDao blogPostLikesDao;

	@RequestMapping(value = "/hasuserlikedblog/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<?> hasUserLikedBlog(@PathVariable int blogId, HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorized access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		BlogPostLikes blogPostLikes = blogPostLikesDao.hasUserLikedBlog(blogId, user.getUser_mailaddress());
		return new ResponseEntity<BlogPostLikes>(blogPostLikes, HttpStatus.OK);
	}

	@RequestMapping(value = "/updatelikes/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLikes(@PathVariable int id, HttpSession session) {
		User user= (User) session.getAttribute("loggedInUser");
		if (user == null) {
			System.out.println(user.getRole());
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		BlogPost blogPost = blogPostLikesDao.updateLikes(id, user.getUser_mailaddress());
		System.out.println(user.getUser_mailaddress() + " has liked the blog id " + id);
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
	}

}