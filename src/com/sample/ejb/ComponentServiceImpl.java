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

import com.sample.jpa.entities.Component;
import com.sample.jpa.entities.User;

@Stateless
@Remote(ComponentService.class) 
public class ComponentServiceImpl implements Serializable, ComponentService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;
	
	@Override
	public int addComponent(Component component) {
		if ((component.getName() == null || component.getIs_complex() == null || component.getPurch_time() == null || component.getPrice() == null) ||  component.getId() != null)
			return -1;
		
		try {
			em.persist(component);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
			
		if (em.find(Component.class, component.getId()).getName() == component.getName())
			return em.find(Component.class, component.getId()).getId();
		
		return 0;
	}

	@Override
	public String echo(String s) {
        return "Component_44 "+s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Component> getComponent(Component component) {

		@SuppressWarnings("unchecked")
		TypedQuery<Component> query = em.createQuery("from Component", Component.class);
		List<Component> cmp_list = query.getResultList();
		
		return cmp_list;
	}

	@Override
	public int updComponent(Component component) {

		if ((component.getName() == null && component.getIs_complex() == null && component.getPurch_time() == null && component.getPrice() == null) || component.getId() == null )
			return -1;
		
		try {
			em.merge(component);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
		if (em.find(Component.class, component.getId()).getName() == component.getName())
			return em.find(Component.class, component.getId()).getId();
		
		return 0;
	}

	@Override
	public int remComponent(Component component) {
		if (component.getId() == null || component.getName() == null || component.getIs_complex() == null || component.getPurch_time() == null || component.getPrice() == null)
			return -1;


		try {	
			em.remove(em.contains(component) ? component : em.merge(component));
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		if (em.contains(component))
			return 0;
		else
			return 1;
	}
	
	@Override
	public String[] getAllComponentNames(){
		Component comp=new Component();
		
		
		List<Component> comp_list=new ArrayList<Component>();
		comp_list= getComponent(comp);
		
		String[] compNames= new String[comp_list.size()];
		for(int i=0;i<comp_list.size();i++){
			compNames[i]=comp_list.get(i).getName();
		}
		
		
		
		return compNames;
		
	}
	
	@Override
	public Component getComponent(String name){
		Component comp=new Component();
		List<Component> comp_list=new ArrayList<Component>();
		comp_list= getComponent(comp);
		
		String[] compNames= new String[comp_list.size()];
		for(int i=0;i<comp_list.size();i++){
			if(comp_list.get(i).getName().equals(name))
				comp=comp_list.get(i);
		}
		
		
		return comp;
	}

}
