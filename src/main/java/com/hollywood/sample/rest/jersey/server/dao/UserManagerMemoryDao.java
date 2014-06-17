package com.hollywood.sample.rest.jersey.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hollywood.sample.rest.jersey.server.model.User;

public class UserManagerMemoryDao implements UserManagerDao {
	
	public static final Logger log = LoggerFactory.getLogger( UserManagerMemoryDao.class );
	
	// singleton use until i configure spring
	private static UserManagerMemoryDao instance;
	public static UserManagerMemoryDao getInstance() {
		if ( instance != null )
		{
			return instance;
		}
		else
		{
			instance = new UserManagerMemoryDao();
			return instance;
		}
	};
	
	private int nextUserId = 0;

	List<User> users = new ArrayList<User>();

	public User fetchUserById(Integer id)
	{
		log.debug("Fetching user with id=" + id);
		for (User user : users)
		{
			log.debug("Checking user " + user);
			if (user.getId().equals(id))
			{
				return user;
			}
		}

		throw new RuntimeException("User Not Found: " + id);
	}

	public List<User> fetchAllUsers()
	{
		return users;
	}

	public void insertUser(User user)
	{
		user.setId(nextUserId++);
		users.add(user);
		log.info("User added " + user);
		log.debug("Current users:");
		for ( User u : users )
		{
			log.debug(u.toString());
		}
	}

	public void updateUser(User user)
	{
		User editUser = fetchUserById(user.getId());

		editUser.setFirstName(user.getFirstName());
		editUser.setLastName(user.getLastName());
		editUser.setId(user.getId());
	}

	public void deleteUser(User user)
	{
		User delUser = fetchUserById(user.getId());
		users.remove(delUser);
	}
}
