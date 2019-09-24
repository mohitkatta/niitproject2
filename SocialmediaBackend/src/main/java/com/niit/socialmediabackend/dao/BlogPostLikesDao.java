package com.niit.socialmediabackend.dao;

import org.springframework.stereotype.Repository;

import com.niit.socialmediabackend.models.BlogPost;
import com.niit.socialmediabackend.models.BlogPostLikes;

@Repository("blogpostlikesDao")
public interface BlogPostLikesDao {
	BlogPostLikes hasUserLikedBlog(int blogId, String email);

	BlogPost updateLikes(int id, String email);
}
