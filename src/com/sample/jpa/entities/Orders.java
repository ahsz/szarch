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
		name = "orders"
)
public class Orders implements Serializable {
	@Id
	@Column(name = "manuf_scope_id", unique = true, nullable = false)
	private java.lang.Integer manuf_scope_id;
	
	@Id
	@Column(name = "product_id", unique = true, nullable = false)
	private java.lang.Integer product_id;

	@Column(name = "number", nullable = false)
	private java.lang.Integer number;
	
	
	public java.lang.Integer getManuf_scope_id()		{ return manuf_scope_id; }	
	public java.lang.Integer getProduct_id() 			{ return product_id; }
	public java.lang.Integer getNumber()				{ return number; }
	
	public void setManuf_scope_id(java.lang.Integer new_ms_id)		{ this.manuf_scope_id = new_ms_id; }
	public void setProduct_id(java.lang.Integer new_p_id) 			{ this.product_id = new_p_id; }
	public void setNumber(java.lang.Integer new_number) 			{ this.number = new_number; }

	
	public Orders() {
		manuf_scope_id = null;
		product_id = null;
		number = null;
	}
	
	public String toString() {
		return manuf_scope_id + "\t" + product_id + "\t" + number;
	}

}
