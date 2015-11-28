package com.sample.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import com.sample.jpa.entities.Contain;
import com.sample.jpa.entities.Product;

@Stateless
@Remote(ContainService.class) 
public class ContainServiceImpl implements Serializable, ContainService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;

	@Override
	public int addContain(Contain contain) {
		if (contain.getProduct_id() == null || contain.getComponent_id() == null || contain.getNumber() == null)
			return -1;
		
		try {
			em.persist(contain);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		
		return 0;
	}

	@Override
	public String echo(String s) {
	    return "Contain_44 "+s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contain> getContain(Contain contain) {
		@SuppressWarnings("unchecked")
		TypedQuery<Contain> query = em.createQuery("from Contain", Contain.class);
		List<Contain> cnt_list = query.getResultList();
		
		return cnt_list;
	}

	@Override
	public int updContain(Contain contain) {

		if (contain.getProduct_id() == null || contain.getComponent_id() == null || contain.getNumber() == null)
			return -1;
		
		try {
			em.merge(contain);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		return 0;
	}

	@Override
	public int remContain(Contain contain) {

		if (contain.getProduct_id() == null || contain.getComponent_id() == null || contain.getNumber() == null)
			return -1;

		try {	
			em.remove(em.contains(contain) ? contain : em.merge(contain));
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		if (em.contains(contain))
			return 0;
		else
			return 1;
	}
	
	@Override
	public List<Contain> getContainToDelete(int Id){
		Contain cont=new Contain();
		
		
		List<Contain> cont_list=new ArrayList<Contain>();
		cont_list= getContain(cont);
		
		List<Contain> prodToDelete= new ArrayList<Contain>();
		int delCounter=0;
		for(int i=0;i<cont_list.size();i++){
			if(cont_list.get(i).getProduct_id()==Id){
				prodToDelete.add(delCounter, cont_list.get(i));
				delCounter++;
			}
			
			
		}
		
		return prodToDelete;
		
	}


}
