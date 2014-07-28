package com.edash.models;

import java.util.List;
import java.util.Map;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.edash.entities.EmployeeEntity;

public class EmployeeDataModel extends LazyDataModel<EmployeeEntity> {

	private static final long serialVersionUID = 7939727189195547988L;
	
	private List<EmployeeEntity> employess;
	
	
	@Override
	public List<EmployeeEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Query q = pm.newQuery(EmployeeEntity.class);
		q.setRange(first, first + pageSize);
		List<EmployeeEntity> l = (List<EmployeeEntity>) q.execute();
		//Query p = pm.newQuery("select count(*) from com.edash.entities.EmployeeEntity");
		//this.setRowCount((int) ((List) p.execute()).iterator().next());
		//q.closeAll();
		pm.close();
		return l;
	}
	
}
