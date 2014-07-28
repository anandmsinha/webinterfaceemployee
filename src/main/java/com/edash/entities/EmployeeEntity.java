package com.edash.entities;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;

@PersistenceCapable(table = "employee", detachable = "true")
public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = 2082206030815368354L;
	
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, primaryKey = "true")
    private long id;

    @Column(name = "full_name", jdbcType = "VARCHAR", length = 100, allowsNull = "false")
    private String name;

    @Unique
    @Column(name = "email_adress", jdbcType = "VARCHAR", length = 100, allowsNull = "false")
    private String email;

    @Column(name = "age", jdbcType = "INTEGER", allowsNull = "false")
    private int age;

    @Column(name = "adress", jdbcType = "LONGVARCHAR", allowsNull = "false")
    private String adress;
    
    public EmployeeEntity() {
    	
    }
    
    public EmployeeEntity(String n, String e, int age) {
    	this.name = n;
        this.email = e;
        this.age = age;
        this.adress = "default adress";
    }
    
    public EmployeeEntity(String n, String e, int age, String adress) {
    	this.name = n;
        this.email = e;
        this.age = age;
        this.adress = adress;
    }
	
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Employee id - " + this.id + " \nName - " + this.name + " Email = " + this.email
               + " Age - " + this.age + " Adress - " + this.adress;
    }
}
