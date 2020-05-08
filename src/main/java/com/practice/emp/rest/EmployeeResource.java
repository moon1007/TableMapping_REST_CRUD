package com.practice.emp.rest;

import static com.practice.emp.utils.MyConstants.ADMIN_ROLE;
import static com.practice.emp.utils.MyConstants.USER_ROLE;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.security.enterprise.SecurityContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.practice.emp.ejb.SomeBean;
import com.practice.emp.models.EmployeePojo;

@Path("/employee")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {
 
    @EJB
    private SomeBean employeeEJB;

    @Context
    protected ServletContext servletContext;

    @Inject
    protected SecurityContext sc;

    public EmployeeResource() {
    }

    /**
     * Returns all employees
     * @return response
     */
    @GET
    @PermitAll
    //@RolesAllowed(ADMIN_ROLE)
    public Response returnAllEmployee() {
        Response response = null;
        List<EmployeePojo> getAllEmployee = employeeEJB.findAllEmployees();

        try {
            response = Response.ok().entity(getAllEmployee).build();
        }catch(Exception e) {
            response = Response.status(401).entity("Failed to retrieve employees").build();
            //response = Response.status(Status.UNAUTHORIZED).build();
        }

        return response;
    }

    /**
     * Checks connection status (No auth)
     * @return response
     */
    @GET
    @Path("/testNoAuthConnection")
    @PermitAll
    //@RolesAllowed(ADMIN_ROLE)
    public Response returnConnectionTestStatusNoAuth() {

        Response response = null;


        try {
            response = Response.status(200).build();
        }catch(Exception e) {
            response = Response.status(404).entity("Something Went Wrong").build();

        }

        return response;
    }

    /**
     * checks connection status (with admin)
     * @return response
     */

    @GET
    @Path("/testAdminAuthorizationConnection")
    @RolesAllowed(ADMIN_ROLE)
    public Response returnConnectionTestStatusWithAdmin() {
        Response response = null;


        if (sc.isCallerInRole(ADMIN_ROLE)) {
            try {
                response = Response.status(200).build();
            }catch (Exception e) {
                response = Response.status(404).entity("Something Went Wrong").build();
            }
        }else {
            response = Response.status(403).build();
        }

        return response;
    }

    /**
     * checks connection (User 1)
     * @return
     */
    @GET
    @Path("/testUser1AuthorizationConnection")
    @RolesAllowed(USER_ROLE)
    public Response returnConnectionTestStatusWithUser1() {
        Response response = null;


        if (sc.isCallerInRole(USER_ROLE)) {
            try {
                response = Response.status(200).build();
            }catch (Exception e) {
                response = Response.status(404).entity("Something Went Wrong").build();
            }
        }else {
            response = Response.status(403).build();
        }

        return response;
    }


    /**
     * Adds employee
     * @param employee
     * @return
     */
    @POST
    @RolesAllowed(USER_ROLE)
    public Response addEmployee(EmployeePojo employee) {
        Response response = null;

        if (sc.isCallerInRole(USER_ROLE)) {
            try {
                EmployeePojo newEmp = employeeEJB.persistEmployee(employee);
                response = Response.ok().entity(newEmp).build();
            }catch (Exception e) {
                response = Response.status(401).entity("Failed to create an employee").build();
            }
        }else {
            response = Response.status(403).entity("You are unauthorized").build();
        }

        return response;
    }

 
    /**
     * Updates employee
     * @param id
     * @param employee
     * @return response
     * @throws Exception
     */
    @PUT
    @Path("/{Id}")
    @RolesAllowed(ADMIN_ROLE)
    public Response updateEmployee(@PathParam("Id") int id, EmployeePojo employee) throws Exception {
        Response response = null;
        employee.setId(id);

        if(sc.isCallerInRole(ADMIN_ROLE)) {
            try{
                response = Response.ok().entity(employeeEJB.mergeEmployee(employee)).build();
            }catch (Exception e){
                response = Response.status(401).entity("Failed to update an employee record").build();
            }
        }else {
            response = Response.status(403).entity("You are unauthorized").build();
        }

        return response;
    }
  
    /**
     * Deletes message
     * @param id
     * @return response
     * @throws Exception
     */
    @DELETE
    @Path("/{Id}")
    @RolesAllowed(ADMIN_ROLE)
    public Response deleteMessage(@PathParam("Id") int id) throws Exception {
        Response response = null;
        if(sc.isCallerInRole(ADMIN_ROLE)) {
            try{
                employeeEJB.removeEmployee(id);
                response = Response.ok().entity("Deleted").build();
            }catch (Exception e){
                response = Response.status(401).entity("Failed to delete an employee record").build();
            }
        }else {
            response = Response.status(403).entity("You are unauthorized").build();
        }
        return response;
    }
 
    /**
     * gets employee
     * @param id
     * @return response
     */
    @GET
    @Path("/{Id}")
    @RolesAllowed(USER_ROLE)
    public Response getEmployee(@PathParam("Id") int id) {
        Response response = null;
        if(sc.isCallerInRole(USER_ROLE)) {
            try{
                response = Response.ok().entity(employeeEJB.findEmployeeByPrimaryKey(id)).build();
            }catch (Exception e){
                response = Response.status(401).entity("Failed to retrieve the employee list").build();
            }
        }else {
            response = Response.status(403).entity("You are unauthorized").build();
        }
        return response;
    }
   
}