/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Student;
import SessionBeans.TeachersSessionBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


/**
 * REST Web Service
 *
 * @author Topr
 */
@Path("StudentsList")
public class StudentsResource {
    @EJB
    TeachersSessionBeanLocal sb;
    
    @Context
    private UriInfo context;

    
    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<Student> checkLogin(@PathParam("login") String login, @PathParam("password") String password) {
        return sb.getStudents(login, password);
    }
    
}
