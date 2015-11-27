package com.sample.ejb;

import java.util.List;


import com.sample.jpa.entities.User;


public interface UserService {

	public int addUser(User user);
    public String echo(String s);
	public List<User> getUser(User user);
	public int updUser(User user);
	public int remUser(User user);
}
