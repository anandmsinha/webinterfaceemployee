package com.edash.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.edash.daos.EmployeeDao;
import com.edash.entities.EmployeeEntity;

@Path("/ejson")
public class EmployeeService {
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployess() {
		java.util.List<EmployeeEntity> l = EmployeeDao.getAllEmployess();
		return Response.ok()
				.entity(new GenericEntity<java.util.List<EmployeeEntity>>(l) {
				}).build();
	}
	
	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertEmployee(EmployeeEntity e) {
		if (EmployeeDao.addEmployee(e)) {
			return Response.ok().entity("Added successfully").build();
		} else {
			return Response.status(400).entity("Database error").build();
		}
	}
	
	@POST
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(@PathParam("id") int id, EmployeeEntity e) {
		
		if (EmployeeDao.updateEmployee(e,  id)) {
			return Response.ok().entity("Employee updated succesfully.").build();
		}
		
		return Response.status(400).entity("Update process failed").build();
	}
	
	@POST
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEmployee(@PathParam("id") int id) {
		if (EmployeeDao.deleteById(id)) {
			return Response.ok().entity("Successfully deleted entry").build();
		}
		
		return Response.ok().entity("Deletion process failed").build();
	}
}
