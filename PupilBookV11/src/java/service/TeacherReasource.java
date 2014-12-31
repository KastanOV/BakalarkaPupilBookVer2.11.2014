/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Teacher;
import SessionBeans.TeachersSBLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


/**
 * REST Web Service
 *
 * @author Topr
 */

@Path("teachers")
public class TeacherReasource {
    @EJB
    TeachersSBLocal sb;
    
    @Context
    private UriInfo context;

    
    public TeacherReasource() {
    }
    
    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public Teacher checkLogin(@PathParam("login") String login, @PathParam("password") String password) {
        return sb.checkLogin(login, password);
    }

}
