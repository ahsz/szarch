package com.sample.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
		name = "complex"
)
public class Complex implements Serializable {
	@Id
	@Column(name = "cmpnt_container_id", unique = true, nullable = false)
	private java.lang.Integer cmpnt_container_id;
	
	@Id
	@Column(name = "cmpnt_contained_id", unique = true, nullable = false)
	private java.lang.Integer cmpnt_contained_id;

	@Column(name = "number", nullable = false)
	private java.lang.Integer number;
	
	
	public java.lang.Integer getCmpnt_container_id()	{ return cmpnt_container_id; }	
	public java.lang.Integer getCmpnt_contained_id() 	{ return cmpnt_contained_id; }
	public java.lang.Integer getNumber()				{ return number; }
	
	public void setCmpnt_container_id(java.lang.Integer new_cr_id)		{ this.cmpnt_container_id = new_cr_id; }
	public void setCmpnt_contained_id(java.lang.Integer new_cd_id) 		{ this.cmpnt_contained_id = new_cd_id; }
	public void setNumber(java.lang.Integer new_number) 				{ this.number = new_number; }

	
	public Complex() {
		cmpnt_container_id = null;
		cmpnt_contained_id = null;
		number = null;
	}
	
	public String toString() {
		return cmpnt_container_id + "\t" + cmpnt_contained_id + "\t" + number;
	}
}
