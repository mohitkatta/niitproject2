package com.niit.socialmediabackend.dao;

import org.springframework.stereotype.Repository;

import com.niit.socialmediabackend.models.ProfilePicture;

@Repository("profilepictureDao")
public interface ProfilePictureDao {
	void uploadProfilePicture(ProfilePicture profilePicture);

	ProfilePicture getProfilePicture(String email);

}
