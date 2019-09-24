package com.niit.socialmediacontroller.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.socialmediabackend.dao.ProfilePictureDao;
import com.niit.socialmediabackend.models.ErrorClass;
import com.niit.socialmediabackend.models.ProfilePicture;
import com.niit.socialmediabackend.models.User;

@RestController
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilepictureDao;
	@Autowired
	private SessionFactory sessionFactory;

	public ProfilePictureController() {
		System.out.println("ProfilePictureController bean is created");
	}

	@RequestMapping(value = "/uploadprofilepic", method = RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image, HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			ErrorClass error = new ErrorClass(4, "Unauthrozied access.. Please login");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED); // 2nd
																					// callback
																					// function
		}
		ProfilePicture profilePicture = new ProfilePicture();
		profilePicture.setEmail(user.getUser_mailaddress());
		profilePicture.setImage(image.getBytes());
		profilepictureDao.uploadProfilePicture(profilePicture);// insert or
																// update
		return new ResponseEntity<ProfilePicture>(profilePicture, HttpStatus.OK);
	}

	@RequestMapping(value = "/getimage/{email:.+}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImage(@PathVariable String email, HttpSession session) {
		User auth = (User) session.getAttribute("loggedInUser");
		if (auth == null) {
			return null;
		}
		System.out.println(email+" in profile pic");
		ProfilePicture profilePicture = profilepictureDao.getProfilePicture(email);

		if (profilePicture == null)
			return null;
		System.out.println("Image is " + profilePicture.getImage() + " " + email);
		return profilePicture.getImage();
	}
}