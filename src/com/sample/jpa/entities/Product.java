package com.sample.jpa.entities;

import java.io.Serializable;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sample.ejb.UserService;

@Entity
@Table(
		name = "product",
		uniqueConstraints=@UniqueConstraint(columnNames={"name"})		
)
public class Product implements Serializable {

	 private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private java.lang.Integer id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	
	public java.lang.Integer getId()			{ return id; }	
	public String getName() 					{ return name; }
	
	public void setId(java.lang.Integer new_id)				{ this.id = new_id; }
	public void setName(String new_name) 					{ this.name = new_name; }

	public Product() {
		id = null;
		name = null;
	}
	
	public String toString() {
		return id + "\t" + name;
	}
}
