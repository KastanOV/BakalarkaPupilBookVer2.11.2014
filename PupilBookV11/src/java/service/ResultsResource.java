/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Results;
import SessionBeans.TeachersSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author KastanNotas
 */
@Path("Results")
public class ResultsResource {
    @EJB
    TeachersSessionBeanLocal sb;
    
    @Context
    private UriInfo context;
    
    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<servicesDTO.Results> getResults(@PathParam("login") String login, @PathParam("password") String password){
        List<Results> res = sb.getResults(login, password);
        List<servicesDTO.Results> items = new ArrayList<>();
        for(Results item : res){
            servicesDTO.Results newItem = new servicesDTO.Results(item.getIdResults(),item.getDescription(),(int)item.getScore(),item.getDate(),item.getStudySubjectidStudySubject().getIdStudySubject(),item.getStudentLogin().getLogin(), item.getTeacherLogin().getLogin(), null);
            items.add(newItem);
        }
        return items;
    }
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(servicesDTO.Results res) {
        
    }

}
