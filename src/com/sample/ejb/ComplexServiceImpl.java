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

import com.sample.jpa.entities.Complex;
import com.sample.jpa.entities.Component;
import com.sample.jpa.entities.Orders;
import com.sample.jpa.entities.User;

@Stateless
@Remote(ComplexService.class) 
public class ComplexServiceImpl implements Serializable, ComplexService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;
	
	@Override
	public int addComplex(Complex complex) {
		if (complex.getCmpnt_container_id() == null || complex.getCmpnt_contained_id() == null || complex.getNumber() == null)
			return -1;
		
		try {
			em.persist(complex);
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
	    return "Complex_44 "+s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complex> getComplex(Complex complex) {
		@SuppressWarnings("unchecked")
		TypedQuery<Complex> query = em.createQuery("from Complex", Complex.class);
		List<Complex> clx_list = query.getResultList();
		
		return clx_list;
	}

	@Override
	public int updComplex(Complex complex) {

		if (complex.getCmpnt_container_id() == null || complex.getCmpnt_contained_id() == null || complex.getNumber() == null)
			return -1;
		
		try {
			em.merge(complex);
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
	public int remComplex(Complex complex) {

		if (complex.getCmpnt_container_id() == null || complex.getCmpnt_contained_id() == null || complex.getNumber() == null)
			return -1;

		try {	
			em.remove(em.contains(complex) ? complex : em.merge(complex));
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		if (em.contains(complex))
			return 0;
		else
			return 1;
	}

	@Override
	public List<Complex> getComplexToDelete(int Id){
		Complex comp=new Complex();
		
		
		List<Complex> comp_list=new ArrayList<Complex>();
		comp_list= getComplex(comp);
		
		List<Complex> compToDelete= new ArrayList<Complex>();
		int delCounter=0;
		for(int i=0;i<comp_list.size();i++){
			if(comp_list.get(i).getCmpnt_container_id()==Id){
				compToDelete.add(delCounter, comp_list.get(i));
				delCounter++;
			}
			
			
		}
		
		return compToDelete;
		
	}
	
}
