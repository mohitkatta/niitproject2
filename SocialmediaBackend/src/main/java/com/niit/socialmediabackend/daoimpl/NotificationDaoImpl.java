package com.niit.socialmediabackend.daoimpl;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.socialmediabackend.dao.NotificationDao;
import com.niit.socialmediabackend.models.Notification;

@Repository("notificationDao")
@EnableTransactionManagement
@Transactional
public class NotificationDaoImpl implements NotificationDao {
	@Autowired
	private SessionFactory sessionFactory;

	public NotificationDaoImpl() {

		System.out.println("NotificationDaoImpl bean is created");
	}

	public List<Notification> getNotificationsNotViewed(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notification where email=? and viewed=0");
		query.setString(0, email);
		List<Notification> notificationsNotViewed = query.list();
		return notificationsNotViewed;
	}

	public Notification getNotifications(String email, int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notification where email=? and id=?");
		query.setString(0, email);
		query.setInteger(1, id);
		Notification notification = (Notification) query.list().get(0);
		return notification;
	}

	public Notification updateNotification(int id) {
		Session session = sessionFactory.getCurrentSession();
		Notification notification = (Notification) session.get(Notification.class, id);
		notification.setViewed(true);
		session.update(notification);
		
		return notification;
	}

}