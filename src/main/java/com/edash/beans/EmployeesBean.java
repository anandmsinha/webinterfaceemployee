package com.edash.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.edash.clients.EmployeesClient;
import com.edash.entities.EmployeeEntity;

@ManagedBean(name = "empBean", eager = true)
@RequestScoped
public class EmployeesBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<EmployeeEntity> esEmployeeEntities = new ArrayList<EmployeeEntity>();
	
	private EmployeeEntity selectedRow;
	
	private String name;
	
	private String email;
	
	private int age;
	
	private String adress;
	
	private String status;
	
	

	public EmployeesBean() {
		esEmployeeEntities.addAll(EmployeesClient.getAllEmployeeEntities());
	}
	
	public List<EmployeeEntity> getEsEmployeeEntities() {
		return esEmployeeEntities;
	}

	public void setEsEmployeeEntities(List<EmployeeEntity> esEmployeeEntities) {
		this.esEmployeeEntities = esEmployeeEntities;
	}
	
	public EmployeeEntity getSelectedRow() {
		return selectedRow;
	}
	
	public void setSelectedRow(EmployeeEntity selectedRow) {
		this.selectedRow = selectedRow;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String submit() {
		EmployeeEntity e = new EmployeeEntity();
		e.setAdress(this.adress);
		e.setAge(this.age);
		e.setEmail(this.email);
		e.setName(this.name);
		
		if (EmployeesClient.addEmplyeeEntity(e)) {
			return "welcome.xhtml?faces-redirect=true";
		}
		
		this.status = "Some error has occured";
		return "add.xhtml";
	}
	
	public void onRowEdit(RowEditEvent event) {
		EmployeeEntity e = (EmployeeEntity) event.getObject();
		
		FacesMessage msg;
		
		if (EmployeesClient.updateEmployeeEntity(e, (int) e.getId())) {
			msg = new FacesMessage("Employee edited");
		} else {
			msg = new FacesMessage("update failed");
		}
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void deleteEmp() {
		System.out.println("fdfdf");
		FacesMessage msg;
		if (EmployeesClient.deleteEmployeeEntity(selectedRow.getId())) {
			msg = new FacesMessage("successfully deleted");
		} else {
			msg = new FacesMessage("deletion failed");
		} 
		try {
			esEmployeeEntities.remove(selectedRow);
		} catch(Exception e){
			System.out.println("error------------------------------");
			e.printStackTrace();
		}
		this.selectedRow = null;
		//this.esEmployeeEntities = null;
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}