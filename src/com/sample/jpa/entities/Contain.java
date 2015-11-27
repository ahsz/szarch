package com.sample.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
		name = "contain"
)
public class Contain implements Serializable {
	@Id
	@Column(name = "product_id", unique = true, nullable = false)
	private java.lang.Integer product_id;
	
	@Id
	@Column(name = "component_id", unique = true, nullable = false)
	private java.lang.Integer component_id;

	@Column(name = "number", nullable = false)
	private java.lang.Integer number;
	
	
	public java.lang.Integer getProduct_id()		{ return product_id; }	
	public java.lang.Integer getComponent_id() 		{ return component_id; }
	public java.lang.Integer getNumber()			{ return number; }
	
	public void setProduct_id(java.lang.Integer new_p_id)		{ this.product_id = new_p_id; }
	public void setComponent_id(java.lang.Integer new_c_id) 	{ this.component_id = new_c_id; }
	public void setNumber(java.lang.Integer new_number) 		{ this.number = new_number; }

	
	public Contain() {
		product_id= null;
		component_id = null;
		number = null;
	}
	
	public String toString() {
		return product_id + "\t" + component_id + "\t" + number;
	}
}
