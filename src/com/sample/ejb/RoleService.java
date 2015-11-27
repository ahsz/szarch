package com.sample.ejb;

import java.util.List;

import com.sample.jpa.entities.Role;

public interface RoleService {

	public int addRole(Role role);
    public String echo(String s);
	public List<Role> getRole(Role role);
	public int updRole(Role role);
	public int remRole(Role role);
	
}





