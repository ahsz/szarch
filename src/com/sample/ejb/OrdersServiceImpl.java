package com.sample.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import com.sample.jpa.entities.Orders;
import com.sample.jpa.entities.User;

@Stateless
@Remote(UserService.class) 
public class OrdersServiceImpl implements OrdersService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;

	@Override
	public int addOrders(Orders orders) {
		if (orders.getManuf_scope_id() == null || orders.getProduct_id() == null || orders.getNumber() == null)
			return -1;
		
		try {
			em.persist(orders);
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
	    return "Orders_44 "+s;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrders(Orders orders) {

		@SuppressWarnings("unchecked")
		TypedQuery<Orders> query = em.createQuery("from Orders", Orders.class);
		List<Orders> ord_list = query.getResultList();
		
		return ord_list;
	}

	@Override
	public int updOrders(Orders orders) {

		if (orders.getManuf_scope_id() == null || orders.getProduct_id() == null || orders.getNumber() == null)
			return -1;
		
		try {
			em.merge(orders);
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
	public int remOrders(Orders orders) {

		if (orders.getManuf_scope_id() == null || orders.getProduct_id() == null || orders.getNumber() == null)
			return -1;


		try {	
			em.remove(em.contains(orders) ? orders : em.merge(orders));
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		if (em.contains(orders))
			return 0;
		else
			return 1;
	}

}
