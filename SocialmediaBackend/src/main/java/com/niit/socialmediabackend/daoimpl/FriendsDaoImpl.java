package com.niit.socialmediabackend.daoimpl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.socialmediabackend.dao.FriendsDao;
import com.niit.socialmediabackend.models.Friends;
import com.niit.socialmediabackend.models.User;
@Repository("friendsDAO") 
@Transactional 
public class FriendsDaoImpl implements FriendsDao
{
	@Autowired
	private SessionFactory sessionFactory;
		public FriendsDaoImpl(){
			System.out.println("FriendsDaoImpl bean is created..");
		}
	public boolean addFriend(Friends friend) {
		// TODO Auto-generated method stub
		 Session session= sessionFactory.getCurrentSession();
	       
	       session.save(friend);//Generate insert query..
		return true;
	}

	@Override
	public Friends getFriend(String fromemail,String toemail) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		try
		{
			String s1="from Friends where  fromuser.user_mailaddress='"+fromemail+"' and touser.user_mailaddress='"+toemail+"'";
			Query q=session.createQuery(s1);
			List<Friends> flist=q.list();
		//Friends friend=(Friends)session.get(Friends.class, s);
		//SQL query select * from user where id=?
			
		return flist.get(0);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public boolean updateFriend(Friends friend) {
		// TODO Auto-generated method stub
		Session s=sessionFactory.getCurrentSession();
		try {
		s.update(friend);
		return true;
		}
		catch(Exception e1)
		{
			return false;
		}
	}

	@Override
	public boolean deleteFriend(Friends f) {
		// TODO Auto-generated method stub
		//User u=getFriend(em);
		Session s=sessionFactory.getCurrentSession();
		try
		{
			s.delete(f);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public List<Friends> listFriends(String email) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("select f.touser from Friends f where f.fromuser.user_mailaddress=? and f.status=?");
		query1.setString(0, email);
		query1.setCharacter(1, 'A');
		List<Friends> friendsList1 = query1.list();
		Query query2 = session.createQuery("select f.fromuser from Friends f where f.touser.user_mailaddress=? and f.status=?");
		query2.setString(0, email);
		query2.setCharacter(1, 'A');
		List<Friends> friendsList2 = query2.list();
		friendsList1.addAll(friendsList2);
		return friendsList1;
		
		
	}
	
	@Override
	public List<User> suggestedfriends(String email) {
		String s4="select * from user where user_mailaddress in " +
	"(select user_mailaddress from user where user_mailaddress!=? " +
				"minus(select touser_user_mailaddress from friends where fromuser_user_mailaddress=? " + 
				"union select fromuser_user_mailaddress from friends where touser_user_mailaddress=?))";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(s4);
		query.setString(0, email);
		query.setString(1, email);
		query.setString(2, email);
		List<User> suggestedUsers = query.list();
		return suggestedUsers;
		
	}
	@Override
	public List<Friends> pendingrequests(String email) {
		// TODO Auto-generated method stub
		String s4="from Friends where touser.user_mailaddress='"+email+"' and status='p'";
		Query e=sessionFactory.getCurrentSession().createQuery(s4);
		List<Friends> flist=e.list();
		return flist;
	}
	@Override
	public Friends findFriend(int friendid) {
		Session session=sessionFactory.getCurrentSession();
		Friends fr=(Friends)session.get(Friends.class,friendid);
		return fr;
	}
	
	
	
}
