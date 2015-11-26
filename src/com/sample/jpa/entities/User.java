package com.sample.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id")
	private int employeeId;
	
	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;
	
	@Column(name = "role_id")
	private int  role_id;
	
	public String getName() {
		return name;
	}
	
	
	/*
	@TableGenerator(name = "employee_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "employee_gen")
	
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_gen")
	private int employeeId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private double salary;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}*/

}