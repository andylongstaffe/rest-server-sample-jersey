package com.hollywood.sample.rest.jersey.server.service;

import java.util.Arrays;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hollywood.sample.rest.jersey.server.dao.UserManagerDao;
import com.hollywood.sample.rest.jersey.server.dao.UserManagerMemoryDao;
import com.hollywood.sample.rest.jersey.server.model.UserRequest;
import com.hollywood.sample.rest.jersey.server.model.UserResponse;
import com.hollywood.sample.rest.jersey.server.webservice.UserManager;

@Path("/")
public class UserManagerService implements UserManager {

	private UserManagerDao userDao = UserManagerMemoryDao.getInstance();

	public UserManagerDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserManagerDao userDao) {
		this.userDao = userDao;
	}

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	@Override
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponse fetchUserById(@PathParam("id") Integer id) {
		UserResponse response = new UserResponse();

		try {
			response.setUsers(Arrays.asList(getUserDao().fetchUserById(id)));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@GET
	@Path("/users/")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponse fetchAllUsers() {
		UserResponse response = new UserResponse();

		try {
			response.setUsers(getUserDao().fetchAllUsers());
		} catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@Override
	@PUT
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponse insertUser(UserRequest request) {
		UserResponse response = new UserResponse();

		try {
			getUserDao().insertUser(request.getUser());
			response.setUsers(Arrays.asList(request.getUser()));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@PUT
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponse updateUser(@PathParam("id") Integer id,
			UserRequest request) {
		UserResponse response = new UserResponse();

		try {
			if (!id.equals(request.getUser().getId())) {
				throw new IllegalAccessException(
						"Id on path does not match the user id");
			}

			getUserDao().updateUser(request.getUser());
		} catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@DELETE
	@Path("/deleteUser/")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponse deleteUser(UserRequest request) {
		UserResponse response = new UserResponse();

		try {
			getUserDao().deleteUser(request.getUser());
		} catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

}
