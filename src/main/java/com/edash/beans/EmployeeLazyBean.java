package com.edash.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.LazyDataModel;

import com.edash.entities.EmployeeEntity;
import com.edash.models.EmployeeDataModel;

@ManagedBean
@RequestScoped
public class EmployeeLazyBean implements Serializable {

	private static final long serialVersionUID = 7055035792764327368L;
	
	private LazyDataModel<EmployeeEntity> empLazyModel;
	
	private EmployeeEntity selectedEmployeeEntity;
	
	@PostConstruct
	public void init() {
		empLazyModel = new EmployeeDataModel();
	}
	
	public LazyDataModel<EmployeeEntity> getEmpLazyModel() {
		return empLazyModel;
	}

	public void setEmpLazyModel(LazyDataModel<EmployeeEntity> empLazyModel) {
		this.empLazyModel = empLazyModel;
	}

	public EmployeeEntity getSelectedEmployeeEntity() {
		return selectedEmployeeEntity;
	}

	public void setSelectedEmployeeEntity(EmployeeEntity selectedEmployeeEntity) {
		this.selectedEmployeeEntity = selectedEmployeeEntity;
	}
	
}
