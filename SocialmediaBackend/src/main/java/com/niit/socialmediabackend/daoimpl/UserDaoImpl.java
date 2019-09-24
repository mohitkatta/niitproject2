package com.niit.socialmediabackend.daoimpl;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.socialmediabackend.dao.UserDao;
import com.niit.socialmediabackend.models.User;

@Repository("userDAO") 
@Transactional 
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;
		public UserDaoImpl(){
			System.out.println("UserDaoImpl bean is created..");
		}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		 Session session= sessionFactory.getCurrentSession();
	       
	       session.save(user);//Generate insert query..
		return true;
	}

	@Override
	public User getUser(String email,String password) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		System.out.println("logging in");
		try
		{
		User user=(User)session.get(User.class, email);
		if((user.getUser_password()).equals(password))
		//SQL query select * from user where id=?
		return user;
		else
			return null;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		Session s=sessionFactory.getCurrentSession();
		try {
		s.update(u);
		return true;
		}
		catch(Exception e1)
		{
			return false;
		}
	}

//	@Override
//	public boolean deleteUser(String em) {
//		// TODO Auto-generated method stub
//		User u=getUser(em);
//		Session s=sessionFactory.getCurrentSession();
//		try
//		{
//			s.delete(u);
//			return true;
//		}
//		catch(Exception e) {
//			return false;
//		}
//	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		String s4="from User";
		Query e=sessionFactory.getCurrentSession().createQuery(s4);
		List<User> ulist=e.list();
		return ulist;
	}

	public User checkEmail(String email) {
		Session session=sessionFactory.getCurrentSession();
		
		User user=(User)session.get(User.class, email);
		return user;
	}

	
	
}
