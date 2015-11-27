package com.sample.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import com.sample.jpa.entities.User;


@Stateless
@Remote(UserService.class) 
public class UserServiceImpl implements UserService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;

	
	@Override
	public int addUser(User user) {
		if ((user.getName() == null || user.getPassword() == null || user.getRole_id() == null) ||  user.getId() != null)
			return -1;
		
		try {
			em.persist(user);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
			
		if (em.find(User.class, user.getId()).getName() == user.getName())
			return em.find(User.class, user.getId()).getId();
		
		return 0;
	}
	
	
    @Override
    public String echo(String s) {
        return "User_44 "+s;
    }
 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser(User user) {

		@SuppressWarnings("unchecked")
		TypedQuery<User> query = em.createQuery("from User", User.class);
		List<User> usr_list = query.getResultList();
		
		return usr_list;	
	}
	
	@Override
	public int updUser(User user){
		
		if ((user.getName() == null && user.getPassword() == null && user.getRole_id() == null) || user.getId() == null )
			return -1;
		
		try {
			em.merge(user);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
		if (em.find(User.class, user.getId()).getName() == user.getName())
			return em.find(User.class, user.getId()).getId();
		
		return 0;
	}
	
	@Override
	public int remUser(User user){
		
		if (user.getId() == null || user.getName() == null || user.getPassword() == null || user.getRole_id() == null)
			return -1;


		try {	
			em.remove(em.contains(user) ? user : em.merge(user));
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		if (em.contains(user))
			return 0;
		else
			return 1;
	}
	
	@Override
	public String[] getAllUserNames(){
		User user=new User();
		
		
		List<User> usr_list=new ArrayList<User>();
		usr_list= getUser(user);
		
		String[] userNames= new String[usr_list.size()];
		for(int i=0;i<usr_list.size();i++){
			userNames[i]=usr_list.get(i).getName();
		}
		
		
		
		return userNames;
		
	}
	@Override
	public User getUser(int Id){
		User user=new User();
		List<User> usr_list=new ArrayList<User>();
		usr_list= getUser(user);
		
		String[] userNames= new String[usr_list.size()];
		for(int i=0;i<usr_list.size();i++){
			if(usr_list.get(i).getId()==Id)
				user=usr_list.get(i);
		}
		
		
		return user;
	}
	
}
