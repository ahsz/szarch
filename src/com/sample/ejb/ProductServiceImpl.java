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
import com.sample.jpa.entities.Product;
import com.sample.jpa.entities.User;

@Stateless
@Remote(ProductService.class) 
public class ProductServiceImpl implements ProductService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name = "Anyagbeszer")
	private EntityManager em;
	
	@Override
	public int addProduct(Product product) {
		if (product.getName() == null ||  product.getId() != null)
			return -1;
		
		try {
			em.persist(product);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
			
		if (em.find(Product.class, product.getId()).getName() == product.getName())
			return em.find(Product.class, product.getId()).getId();
		
		return 0;
	}

	@Override
	public String echo(String s) {
		return "Product_44 "+s;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProduct(Product product) {

		@SuppressWarnings("unchecked")
		TypedQuery<Product> query = em.createQuery("from Product", Product.class);
		List<Product> prd_list = query.getResultList();
		
		return prd_list;	
	}

	@Override
	public int updProduct(Product product) {

		if (product.getName() == null || product.getId() == null )
			return -1;
		
		try {
			em.merge(product);
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}
		if (em.find(Product.class, product.getId()).getName() == product.getName())
			return em.find(Product.class, product.getId()).getId();
		
		return 0;
	}

	@Override
	public int remProduct(Product product) {
		if (product.getId() == null || product.getName() == null)
			return -1;

		try {	
			em.remove(em.contains(product) ? product : em.merge(product));
		} catch (ConstraintViolationException e) {
			return -2;
		} catch (IllegalArgumentException e) {
			return -3;   
		} catch (TransactionRequiredException e){
			return -4;
		} catch (PersistenceException e) {
			return -5;
		}

		if (em.contains(product))
			return 0;
		else
			return 1;
	}

	@Override
	public Product getProduct(String name){
		Product prod=new Product();
		List<Product> prod_list=new ArrayList<Product>();
		prod_list= getProduct(prod);
		
		String[] prodNames= new String[prod_list.size()];
		for(int i=0;i<prod_list.size();i++){
			if(prod_list.get(i).getName().equals(name))
				prod=prod_list.get(i);
		}
		
		
		return prod;
	}
	
	
	@Override
	public Product getProduct(int Id){
		Product prod=new Product();
		List<Product> prod_list=new ArrayList<Product>();
		prod_list= getProduct(prod);
		
		String[] prodNames= new String[prod_list.size()];
		for(int i=0;i<prod_list.size();i++){
			if(prod_list.get(i).getId()==Id)
				prod=prod_list.get(i);
		}
		
		
		return prod;
	}
	
	@Override
	public String[] getAllProductNames(){
		Product prod=new Product();
		
		
		List<Product> prod_list=new ArrayList<Product>();
		prod_list= getProduct(prod);
		
		String[] prodNames= new String[prod_list.size()];
		for(int i=0;i<prod_list.size();i++){
			prodNames[i]=prod_list.get(i).getName();
		}
		
		
		
		return prodNames;
	}
}
