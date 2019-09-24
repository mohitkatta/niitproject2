package com.niit.socialmediabackend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.socialmediabackend.models.Notification;

@Repository("notificationDao")
public interface NotificationDao {
	List<Notification> getNotificationsNotViewed(String email);

	Notification getNotifications(String email, int id);

	Notification updateNotification(int id);

}