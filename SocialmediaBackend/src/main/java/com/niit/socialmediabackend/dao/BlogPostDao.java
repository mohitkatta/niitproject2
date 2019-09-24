package com.niit.socialmediabackend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.socialmediabackend.models.BlogComment;
import com.niit.socialmediabackend.models.BlogPost;

@Repository("blogpostDao")
public interface BlogPostDao {
	void addBlogPost(BlogPost blogPost);

	List<BlogPost> listofBlogs(int approved);

	BlogPost getBlog(int id);

	void approve(BlogPost blog);

	void reject(BlogPost blog, String rejectionReason);

	void addBlogComment(BlogComment blogComment);

	List<BlogComment> getAllBlogComment(int blogPostId);

}