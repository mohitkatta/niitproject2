package com.niit.socialmediacontroller.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.socialmediabackend.dao.NotificationDao;
import com.niit.socialmediabackend.models.ErrorClass;
import com.niit.socialmediabackend.models.Notification;
import com.niit.socialmediabackend.models.User;

@RestController
public class NotificationController {
	@Autowired
	private NotificationDao notificationDao;

	public NotificationController() {
		System.out.println("NotificationController beanis created");
	}

	@RequestMapping(value = "/getnotifications", method = RequestMethod.GET)
	public ResponseEntity<?> getNotificationsNotViewed(HttpSession session) {
		User email = (User) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access....");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);

		}
		List<Notification> notificationsNotViewed = notificationDao.getNotificationsNotViewed(email.getUser_mailaddress());
		return new ResponseEntity<List<Notification>>(notificationsNotViewed, HttpStatus.OK);
	}

	@RequestMapping(value = "/getnotification/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getNotifications(@PathVariable("id") int id, HttpSession session) {
		User email = (User) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access....");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);

		}
		Notification notification =notificationDao.getNotifications(email.getUser_mailaddress(), id);
		return new ResponseEntity<Notification>(notification, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateNotification/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNotification(HttpSession session, @PathVariable int id) {
		User email = (User) session.getAttribute("loggedInUser");
		if (email == null) {
			System.out.println("unauthorised");
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			Notification notification = notificationDao.updateNotification(id);
			System.out.println(notification.getEmail());
			return new ResponseEntity<Notification>(notification, HttpStatus.OK);
		}
	}
}