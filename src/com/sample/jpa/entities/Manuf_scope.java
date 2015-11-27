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
		name = "manuf_scope",
		uniqueConstraints=@UniqueConstraint(columnNames={"ms_id"})
)
public class Manuf_scope implements Serializable {

	 private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private java.lang.Integer id;
	
	@Column(name = "ms_id", unique = true, nullable = false)
	private java.lang.Integer ms_id;

	@Column(name = "is_ordered", nullable = false)
	private java.lang.Integer is_ordered;
	
	@Column(name = "deadline", nullable = false)
	private String deadline;
	
	
	public java.lang.Integer getId()			{ return id; }	
	public java.lang.Integer getMs_id()			{ return ms_id; }
	public java.lang.Integer getIs_ordered()	{ return is_ordered; }
	public String getDeadline()					{ return deadline; }
	
	public void setId(java.lang.Integer new_id)				{ this.id = new_id; }
	public void setMs_id(java.lang.Integer new_ms_id) 		{ this.ms_id = new_ms_id; }
	public void setIs_ordered(java.lang.Integer new_i_o) 	{ this.is_ordered = new_i_o; }
	public void setDeadline(String new_deadline) 			{ this.deadline = new_deadline; }
	
	public Manuf_scope() {
		id = null;
		ms_id = null;
		is_ordered = null;
		deadline = null;
	}
	
	public String toString() {
		return id + "\t" + ms_id + "\t" + is_ordered + "\t" + deadline;
	}
}
