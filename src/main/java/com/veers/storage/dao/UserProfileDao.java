package com.veers.storage.dao;

import java.util.List;

import com.veers.storage.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
