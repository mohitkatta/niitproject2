package com.niit.socialmediabackend;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.socialmediabackend.dao.UserDao;
import com.niit.socialmediabackend.models.User;


	public class UserDaoTesting 
	{
		
		static UserDao userDAO;

		@BeforeClass
		public static void executeFirst()
		{
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
			
			context.scan("com.niit");
			context.refresh();
			
			userDAO=(UserDao)context.getBean("userDAO");
		}
		
		//@Ignore
		@Test
		public void addUserTest()
		{
			User user=new User();
			user.setUser_name("ani");
			user.setUser_mailaddress("ani@gmail.com");
			user.setUser_phonenumber(3);
			user.setUser_password("123");
			user.setRole("ROLE_USER");
			
			
			
			assertTrue("Probem in Adding the Category",userDAO.addUser(user));
		}
		
//		@Ignore
//				@Test
//				public void updateUserTest()
//				{	
//					User user=userDAO.getUser("abc@gamil.com");
//					//user.setUser_name("Jennie");
//					user.setUser_phonenumber(1541451);
//					assertTrue("Problem in Updating ",userDAO.updateUser(user));
//				}
//				@Ignore
//				@Test
//				public void deleteUserTest()
//				{
//					User user=userDAO.getUser("");
//					assertTrue("Problem in deleting ",userDAO.deleteUser("abc@gamil.com"));
//				}
				@Ignore
				@Test
				public void listUsersTest()
				{
					List<User> listUsers=userDAO.listUsers();
					
					assertTrue("Problem in Listing",listUsers.size()>0);
					
					for(User user:listUsers)
					{
						System.out.print("User emailID:"+user.getUser_mailaddress());
						System.out.print("User FirstName:"+user.getUser_name());
						System.out.println("User phonenumber:"+user.getUser_phonenumber());
						System.out.println("User password:"+user.getUser_password());
					}
				}
				
}
	


