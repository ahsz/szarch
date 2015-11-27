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

import com.sample.jpa.entities.Manuf_scope;

@Stateless
@Remote(UserService.class) 	
public class Manuf_scopeServiceImpl implements Manuf_scopeService {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;
	@Override
	
	
	public int addManuf_scope(Manuf_scope manuf_scope) {
		if ((manuf_scope.getMs_id() == null || manuf_scope.getIs_ordered() == null || manuf_scope.getDeadline() == null) ||  manuf_scope.getId() != null)
			return -1;
		
		try {
			em.persist(manuf_scope);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
			
		if (em.find(Manuf_scope.class, manuf_scope.getId()).getMs_id() == manuf_scope.getMs_id())
			return em.find(Manuf_scope.class, manuf_scope.getId()).getId();
		
		return 0;
	}

	@Override
	public String echo(String s) {
        return "Manuf_scope_44 "+s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manuf_scope> getManuf_scope(Manuf_scope manuf_scope) {

		@SuppressWarnings("unchecked")
		TypedQuery<Manuf_scope> query = em.createQuery("from Manuf_scope", Manuf_scope.class);
		List<Manuf_scope> mfs_list = query.getResultList();
		
		return mfs_list;
	}

	@Override
	public int updManuf_scope(Manuf_scope manuf_scope) {

		if ((manuf_scope.getMs_id() == null && manuf_scope.getIs_ordered() == null && manuf_scope.getDeadline() == null) || manuf_scope.getId() == null )
			return -1;
		
		try {
			em.merge(manuf_scope);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
		if (em.find(Manuf_scope.class, manuf_scope.getId()).getMs_id() == manuf_scope.getMs_id())
			return em.find(Manuf_scope.class, manuf_scope.getId()).getId();
		
		return 0;
	}

	@Override
	public int remManuf_scope(Manuf_scope manuf_scope) {
		if (manuf_scope.getId() == null || manuf_scope.getMs_id() == null || manuf_scope.getIs_ordered() == null || manuf_scope.getDeadline() == null )
			return -1;


		try {	
			em.remove(em.contains(manuf_scope) ? manuf_scope : em.merge(manuf_scope));
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		if (em.contains(manuf_scope))
			return 0;
		else
			return 1;
	}

}
