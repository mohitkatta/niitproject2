package com.niit.socialmediacontroller.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.socialmediabackend.dao.BlogPostDao;
import com.niit.socialmediabackend.dao.UserDao;
import com.niit.socialmediabackend.models.BlogComment;
import com.niit.socialmediabackend.models.BlogPost;
import com.niit.socialmediabackend.models.ErrorClass;
import com.niit.socialmediabackend.models.User;

@RestController
public class BlogPostController {
	@Autowired
	private BlogPostDao blogPostDao;
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/addBlog", method = RequestMethod.POST)
	public ResponseEntity<?> addBlogPost(@RequestBody BlogPost blogPost, HttpSession session) {
		System.out.println(blogPost.getBlogContent());
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		blogPost.setPostedOn(new Date());
		User postedBy =(User) session.getAttribute("loggedInUser");
		blogPost.setPostedBy(postedBy);
		try {
			blogPostDao.addBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);

		} catch (Exception e) {
			ErrorClass error = new ErrorClass(6, "Unable to post blog.." + e.getMessage());
			return new ResponseEntity<ErrorClass>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getblogs/{approved}", method = RequestMethod.GET)

	public ResponseEntity<?> getAllBlogs(@PathVariable int approved, HttpSession session) {
		User email = (User) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		if (approved == 0) {
			User user = (User) session.getAttribute("loggedInUser");
			if (!user.getRole().equals("ADMIN")) {
				ErrorClass error = new ErrorClass(7, "Access denied");
				return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
			}
		}
		List<BlogPost> blogs = blogPostDao.listofBlogs(approved);// 0 or 1
		return new ResponseEntity<List<BlogPost>>(blogs, HttpStatus.OK);
	}

	@RequestMapping(value = "/getblog/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlog(@PathVariable int id, HttpSession session) {

		User email = (User) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);

		}
		BlogPost blogPost = blogPostDao.getBlog(id);
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);

	}

	@RequestMapping(value = "/approve", method = RequestMethod.PUT)
	public ResponseEntity<?> approve(@RequestBody BlogPost blog, HttpSession session) {
		User email = (User) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		User user = (User) session.getAttribute("loggedInUser");
		if (!user.getRole().equals("ADMIN")) {
			ErrorClass error = new ErrorClass(7, "Access denied");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}

		blogPostDao.approve(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/reject/{rejectionReason}", method = RequestMethod.PUT)
	public ResponseEntity<?> reject(@RequestBody BlogPost blog, @PathVariable String rejectionReason,
			HttpSession session) {
		User email = (User) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		User user = (User) session.getAttribute("loggedInUser");
		if (!user.getRole().equals("ADMIN")) {
			ErrorClass error = new ErrorClass(7, "AccessDEnied");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		blogPostDao.reject(blog, rejectionReason);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/addcomment/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@PathVariable int id, @RequestBody BlogComment blogComment,
			HttpSession session) {

		User email = (User) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}

		User commentedBy = (User) session.getAttribute("loggedInUser");
		blogComment.setCommentedon(new Date());
		blogComment.setCommentedBy(commentedBy);
		BlogPost bp = blogPostDao.getBlog(id);
		blogComment.setBlogPost(bp);
		try {
			blogPostDao.addBlogComment(blogComment);
		} catch (Exception e) {
			ErrorClass error = new ErrorClass(6, "Unable to post the comment" + e.getMessage());
			return new ResponseEntity<ErrorClass>(error, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}

	@RequestMapping(value = "/blogcomments/{blogPostId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllComments(@PathVariable int blogPostId, HttpSession session) {
		User email = (User) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments = blogPostDao.getAllBlogComment(blogPostId);
		return new ResponseEntity<List<BlogComment>>(blogComments, HttpStatus.OK);
	}
}