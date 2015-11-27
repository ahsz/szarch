package com.sample.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;



@Entity
@Table(
		name = "user",
		uniqueConstraints=@UniqueConstraint(columnNames={"name"})		
)
		
public class User implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private java.lang.Integer id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role_id", nullable = false)
	private java.lang.Integer  role_id;
	
	
	public java.lang.Integer getId()			{ return id; }	
	public String getName() 					{ return name; }
	public String getPassword() 				{ return password; }
	public java.lang.Integer getRole_id()		{ return role_id; }
	
	public void setId(java.lang.Integer new_id)				{ this.id = new_id; }
	public void setName(String new_name) 					{ this.name = new_name; }
	public void setPassword(String new_pw) 					{ this.password = new_pw; }
	public void setRole_id(java.lang.Integer new_role_id) 	{ this.role_id = new_role_id; }
	
	public User() {
		id = null;
		name = null;
		password = null;
		role_id = null;
	}
	
	public String toString() {
		return id + "\t" + name + "\t" + password + "\t" + role_id;
	}

}
