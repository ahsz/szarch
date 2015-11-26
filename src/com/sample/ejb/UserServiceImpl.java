package com.sample.ejb;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Remote(UserService.class) 
public class UserServiceImpl implements UserService {

	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;

	@Override
	public void addUser(String emp) {

		em.persist(emp);

	}
	
	
    @Override
    public String echo(String s) {
 
        return "Hello "+s;
    }
 
	
	@SuppressWarnings("unchecked")
	@Override
	public List getUser() {



List x=em.createQuery("select id, name, password, role_id from User ").getResultList();
			return x;//56d

		


	}
	

}