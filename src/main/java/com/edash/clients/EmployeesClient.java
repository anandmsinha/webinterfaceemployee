package com.edash.clients;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.edash.entities.EmployeeEntity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class EmployeesClient {
	
	public static java.util.List<EmployeeEntity> getAllEmployeeEntities() {
		
		try {
			ClientConfig rConfig = new DefaultClientConfig();
			rConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			
			Client client = Client.create(rConfig);
			WebResource wb = client.resource("http://anand-k53sv:8080/DbTest/rest/ejson/all");
			
			ClientResponse cResponse = wb.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
			
			if (cResponse.getStatus() != 200) {
				return null;
			}
			
			List<EmployeeEntity> l = cResponse.getEntity(new GenericType<List<EmployeeEntity>> () {
				
			});
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean addEmplyeeEntity(EmployeeEntity e) {
		
		try {
			ClientConfig rConfig = new DefaultClientConfig();
			rConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			
			Client client = Client.create(rConfig);
			WebResource wb = client.resource("http://anand-k53sv:8080/DbTest/rest/ejson/insert");
			
			ClientResponse cr = wb.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, e);
			
			if (cr.getStatus() != 200) {
				return false;
			}
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean updateEmployeeEntity(EmployeeEntity e, int id) {
		
		try {
			ClientConfig cc = new DefaultClientConfig();
			cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			
			Client client = Client.create(cc);
			WebResource wb = client.resource("http://anand-k53sv:8080/DbTest/rest/ejson/update/" + id);
			
			ClientResponse cr = wb.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, e);
			
			if (cr.getStatus() != 200) {
				return false;
			}
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static boolean deleteEmployeeEntity(long id) {
		
		try {
			ClientConfig cc = new DefaultClientConfig();
			cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			
			Client client = Client.create(cc);
			WebResource wb = client.resource("http://anand-k53sv:8080/DbTest/rest/ejson/delete/" + id);
			
			ClientResponse cr = wb.type(MediaType.APPLICATION_JSON).post(ClientResponse.class);
			
			if (cr.getStatus() != 200) {
				return false;
			}
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
}
