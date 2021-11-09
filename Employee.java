package com.java.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE7")
public class Employee {

	
	@Id
	private int empNumber;
	
	private String empName;
	private Float empSalary;
	
	@ManyToOne
	@JoinColumn(name="deptno") // FK for emp table
	private Department dept;
	
	public Employee(int empNumber, String empName, Float empSalary, Department dept) {
		super();
		this.empNumber = empNumber;
		this.empName = empName;
		this.empSalary = empSalary;
		this.dept = dept;
	}


	public Employee() {
		// TODO Auto-generated constructor stub
	}


	public int getEmpNumber() {
		return empNumber;
	}




	public void setEmpNumber(int empNumber) {
		this.empNumber = empNumber;
	}




	public String getEmpName() {
		return empName;
	}




	public void setEmpName(String empName) {
		this.empName = empName;
	}




	public Float getEmpSalary() {
		return empSalary;
	}




	public void setEmpSalary(Float empSalary) {
		this.empSalary = empSalary;
	}




	public Department getDept() {
		return dept;
	}




	public void setDept(Department dept) {
		this.dept = dept;
	}





}
