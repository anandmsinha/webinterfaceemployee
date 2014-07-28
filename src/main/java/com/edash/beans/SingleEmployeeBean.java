package com.edash.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.edash.daos.EmployeeDao;
import com.edash.entities.EmployeeEntity;

@ManagedBean(name = "sEmpBean", eager = true)
@RequestScoped
public class SingleEmployeeBean {
	
	private String name;
	
	private String email;
	
	private int age;
	
	private String adress;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SingleEmployeeBean() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public String submit() {
		EmployeeEntity e = new EmployeeEntity();
		e.setAdress(this.adress);
		e.setAge(this.age);
		e.setEmail(this.email);
		e.setName(this.name);
		
		if (EmployeeDao.addEmployee(e)) {
			return "welcome.xhtml?faces-redirect=true";
		}
		
		this.status = "SOme error has occured";
		return "welcome.xhtml";
	}
	
}
