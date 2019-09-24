package com.niit.socialmediabackend;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.socialmediabackend.dao.FriendsDao;
import com.niit.socialmediabackend.models.Friends;
import com.niit.socialmediabackend.models.User;


public class FriendsDaoTesting {
	static FriendsDao friendsDAO;

	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		
		context.scan("com.niit");
		context.refresh();
		
		friendsDAO=(FriendsDao)context.getBean("friendsDAO");
	}
	@Ignore
	@Test
	public void addUserTest()
	{
		User user=new User();
		user.setUser_name("mohit");
		user.setUser_mailaddress("mohit@gmail.com");
		user.setUser_phonenumber(1);
		user.setUser_password("123");
		user.setRole("ROLE_USER");
		User user1=new User();
		user1.setUser_name("ani");
		user1.setUser_mailaddress("ani@gmail.com");
		user1.setUser_phonenumber(3);
		user1.setUser_password("123");
		user1.setRole("ROLE_USER");
		Friends friend=new Friends();
		friend.setFromuser(user);
		friend.setTouser(user1);
		friend.setStatus('a');
				
		assertTrue("Probem in Adding the friends",friendsDAO.addFriend(friend));
	}
	
	@Ignore
			@Test
			public void updateFriendTest()
			{	
				Friends friend=friendsDAO.getFriend("mohit@gmail.com","ani@gmail.com");
				
				friend.setStatus('f');
				assertTrue("Problem in Updating ",friendsDAO.updateFriend(friend));
	}
			@Ignore
			@Test
			public void deleteFriendTest()
			{
				Friends friend=friendsDAO.getFriend("mohit@gmail.com","ani@gmail.com");
				assertTrue("Problem in deleting ",friendsDAO.deleteFriend(friend));
			}
			//@Ignore
			@Test
			public void listUsersTest()
			{
				List<Friends> listUsers=friendsDAO.listFriends("mohit@gmail.com");
				
				assertTrue("Problem in Listing",listUsers.size()>0);
				
				for(Friends fr:listUsers)
				{
					
					System.out.print("User emailID:"+fr.getFromuser().getUser_mailaddress());
					System.out.print("User FirstName:"+fr.getFromuser().getUser_name());
					System.out.println("User phonenumber:"+fr.getFromuser().getUser_phonenumber());
					
				}
			}

}
