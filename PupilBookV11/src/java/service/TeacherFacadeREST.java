/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Teacher;
import SessionBeans.TeachersSessionBeanLocal;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


/**
 * REST Web Service
 *
 * @author Topr
 */

@Path("teachers")
public class TeacherFacadeREST {
    @EJB
    TeachersSessionBeanLocal sb;
    
    @Context
    private UriInfo context;

    
    public TeacherFacadeREST() {
    }
    
    @GET
    @Path("{login}/{password}")
    @Produces({"application/xml", "application/json"})
    public Teacher tryLogin(@PathParam("login") String login, @PathParam("password") String password) {
        return sb.checkLogin(login, password);
    }
    
    
}
