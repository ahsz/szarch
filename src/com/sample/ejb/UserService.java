package com.sample.ejb;

import java.util.List;

//import com.jpa.entities.Employee;

public interface UserService {

	public void addUser(String emp);
    public String echo(String s);
	public List getUser();
}