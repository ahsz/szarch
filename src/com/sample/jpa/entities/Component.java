package com.sample.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name = "component",
		uniqueConstraints=@UniqueConstraint(columnNames={"name"})		
)	
public class Component implements Serializable {

	 private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private java.lang.Integer id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "is_complex", nullable = false)
	private java.lang.Integer is_complex;
	
	@Column(name = "purch_time", nullable = false)
	private java.lang.Integer purch_time;
	
	@Column(name = "price", nullable = false)
	private java.lang.Integer price;
	
	public java.lang.Integer getId()			{ return id; }	
	public String getName() 					{ return name; }
	public java.lang.Integer getIs_complex() 	{ return is_complex; }
	public java.lang.Integer getPurch_time()	{ return purch_time; }
	public java.lang.Integer getPrice()			{ return price; }
	
	
	public void setId(java.lang.Integer new_id)				{ this.id = new_id; }
	public void setName(String new_name) 					{ this.name = new_name; }
	public void setIs_compex(java.lang.Integer new_i_c) 	{ this.is_complex = new_i_c; }
	public void setPurch_time(java.lang.Integer new_p_t) 	{ this.purch_time = new_p_t; }
	public void setPrice(java.lang.Integer new_price) 		{ this.price = new_price; }
	
	public Component() {
		id = null;
		name = null;
		is_complex = null;
		purch_time = null;
		price = null;
	}
	
	public String toString() {
		return id + "\t" + name + "\t" + is_complex + "\t" + purch_time + "\t" + price;
	}
}
