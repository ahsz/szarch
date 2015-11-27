package com.sample.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import com.sample.jpa.entities.Role;
import com.sample.jpa.entities.User;

@Stateless
@Remote(UserService.class) 	
public class RoleServiceImpl implements RoleService {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;
	
	@Override
	public int addRole(Role role) {
		if (role.getName() == null ||  role.getId() != null)
			return -1;
		
		try {
			em.persist(role);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
			
		if (em.find(Role.class, role.getId()).getName() == role.getName())
			return em.find(Role.class, role.getId()).getId();
		
		return 0;
	}

	@Override
	public String echo(String s) {
		return "Role_44 "+s;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRole(Role role) {

		@SuppressWarnings("unchecked")
		TypedQuery<Role> query = em.createQuery("from Role", Role.class);
		List<Role> rol_list = query.getResultList();
		
		return rol_list;	
	}

	@Override
	public int updRole(Role role) {

		if (role.getName() == null || role.getId() == null )
			return -1;
		
		try {
			em.merge(role);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
		if (em.find(Role.class, role.getId()).getName() == role.getName())
			return em.find(Role.class, role.getId()).getId();
		
		return 0;
	}

	@Override
	public int remRole(Role role) {
		if (role.getId() == null || role.getName() == null)
			return -1;

		try {	
			em.remove(em.contains(role) ? role : em.merge(role));
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		if (em.contains(role))
			return 0;
		else
			return 1;
	}

}
