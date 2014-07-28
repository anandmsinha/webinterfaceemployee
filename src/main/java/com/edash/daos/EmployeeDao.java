package com.edash.daos;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.edash.entities.EmployeeEntity;

public class EmployeeDao {
	
	private static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	
	@SuppressWarnings("unchecked")
	public static List<EmployeeEntity> getAllEmployess() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Query q = pm.newQuery(EmployeeEntity.class);
		
		List<EmployeeEntity> l = null;
		
		try {		
			tx.begin();
			l = (List<EmployeeEntity>) q.execute();
			pm.makeTransientAll(l);
			tx.commit();
		} finally {			
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return l;
	}
	
	public static boolean addEmployee(EmployeeEntity e) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            pm.makePersistent(e);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
                return false;
            }
            pm.close();
        }

        return true;
    }
	
	public static boolean updateEmployee(EmployeeEntity o, int id) {
    	PersistenceManager pmManager = pmf.getPersistenceManager();
    	Transaction txTransaction = pmManager.currentTransaction();
    	
    	try {
    		txTransaction.begin();
    		
    		EmployeeEntity empolyeeEntity = (EmployeeEntity) pmManager.getObjectById(EmployeeEntity.class, id);
    		empolyeeEntity.setName(o.getName());
    		empolyeeEntity.setEmail(o.getEmail());
    		empolyeeEntity.setAdress(o.getAdress());
    		empolyeeEntity.setAge(o.getAge());
    		
    		txTransaction.commit();
    	} catch (Exception e) {
    		return false;
    	} finally {
    		if (txTransaction.isActive()) {
    			txTransaction.rollback();
    			return false;
    		}
    		pmManager.close();
    	}
    	return true;
    }
	
	public static boolean deleteById(long id) {
        PersistenceManager pManager = pmf.getPersistenceManager();
        Transaction txTransaction = pManager.currentTransaction();

        try {
            txTransaction.begin();
            EmployeeEntity empolyeeEntity = (EmployeeEntity) pManager.getObjectById(EmployeeEntity.class, id);
            pManager.deletePersistent(empolyeeEntity);
            txTransaction.commit();
        } finally {
            if (txTransaction.isActive()) {
                txTransaction.rollback();
                return false;
            }
            pManager.close();
        }
        return true;
    }
	
	public static boolean addMultiple(Collection<EmployeeEntity> es) {
    	PersistenceManager pmManager = pmf.getPersistenceManager();
    	Transaction txTransaction = pmManager.currentTransaction();
    	
    	try {  		
    		txTransaction.begin();
    		pmManager.makePersistentAll(es);
    		txTransaction.commit();
    	} finally {
    		if (txTransaction.isActive()) {
    			txTransaction.rollback();
    			return false;
    		}
    		pmManager.close();
    	}
    	return true;
    }
}
