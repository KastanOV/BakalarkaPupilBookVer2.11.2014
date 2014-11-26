/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entity.Studygroup;
import SessionBeans.TeachersSessionBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Topr
 */

@Path("studygroups")
public class StudygroupResource {
    
    @EJB
    TeachersSessionBeanLocal sb;
    
    @Context
    private UriInfo context;

    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<Studygroup> getSheduleItems(@PathParam("login") String login, @PathParam("password") String password){
        return sb.getStudyGroups(login, password);
    }
}
