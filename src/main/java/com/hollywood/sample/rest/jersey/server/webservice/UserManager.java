package com.hollywood.sample.rest.jersey.server.webservice;

import com.hollywood.sample.rest.jersey.server.model.UserRequest;
import com.hollywood.sample.rest.jersey.server.model.UserResponse;

public interface UserManager {

	public UserResponse fetchUserById(Integer id);
	
	public UserResponse fetchAllUsers();
	
	public UserResponse insertUser(UserRequest rquest);
	
	public UserResponse updateUser(Integer id, UserRequest request);
	
	public UserResponse deleteUser(UserRequest request);
	
}
