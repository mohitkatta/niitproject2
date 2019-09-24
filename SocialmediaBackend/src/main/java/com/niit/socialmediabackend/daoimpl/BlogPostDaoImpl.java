package com.niit.socialmediabackend.daoimpl;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.socialmediabackend.dao.BlogPostDao;
import com.niit.socialmediabackend.models.BlogComment;
import com.niit.socialmediabackend.models.BlogPost;
import com.niit.socialmediabackend.models.Notification;

@Repository("blogpostDao")

@Transactional
public class BlogPostDaoImpl implements BlogPostDao {
	@Autowired
	private SessionFactory sessionFactory;

	public BlogPostDaoImpl() {

		System.out.println("BlogPostDaoImpl bean is created");
	}

	public void addBlogPost(BlogPost blogPost) {
		Session session = sessionFactory.getCurrentSession();
		session.save(blogPost);
	}

	public List<BlogPost> listofBlogs(int approved) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BlogPost where approved=" + approved);
		List<BlogPost> blogs = query.list();
		for (BlogPost b : blogs) {
			System.out.println(b.getBlogTitle());
		}
		return blogs;
	}

	public BlogPost getBlog(int id) {
		Session session = sessionFactory.getCurrentSession();
		BlogPost blogPost = (BlogPost) session.get(BlogPost.class, id);
		return blogPost;
	}

	public void approve(BlogPost blog) {
		Session session = sessionFactory.getCurrentSession();
		blog.setApproved(true);
		session.update(blog);
		Notification notification = new Notification();
		notification.setBlogTittle(blog.getBlogTitle());
		notification.setApprovalStatus("Approved");
		notification.setEmail(blog.getPostedBy().getUser_mailaddress());
		session.save(notification);
	}

	public void reject(BlogPost blog, String rejectionReason) {
		Session session = sessionFactory.getCurrentSession();
		Notification notification = new Notification();
		notification.setBlogTittle(blog.getBlogTitle());
		notification.setApprovalStatus("Rejected");
		notification.setEmail(blog.getPostedBy().getUser_mailaddress());
		notification.setRejectionReason(rejectionReason);
		session.save(notification);
		session.delete(blog);
	}

	public void addBlogComment(BlogComment blogComment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(blogComment);

	}

	public List<BlogComment> getAllBlogComment(int blogPostId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BlogComment where blogPost.id=?");
		query.setInteger(0, blogPostId);
		List<BlogComment> blogComments = query.list();
		return blogComments;

	}

}