/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Results;
import SessionBeans.ResultsSBLocal;
import SessionBeans.TeachersSBLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author KastanNotas
 */
@Path("Results")
public class ResultsResource {
    @EJB
    TeachersSBLocal TeachersSB;
    @EJB
    ResultsSBLocal resultsSB;
    
    @Context
    private UriInfo context;
    
    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<servicesDTO.Results> getResults(@PathParam("login") String login, @PathParam("password") String password){
        List<Results> res = TeachersSB.getResultsTeacher(login, password);
        List<servicesDTO.Results> items = new ArrayList<>();
        for(Results item : res){
            servicesDTO.Results newItem = new servicesDTO.Results(item.getIdResults(),item.getDescription(),(int)item.getScore(),item.getDate().toString(),item.getStudySubjectidStudySubject().getIdStudySubject(),item.getStudentLogin().getLogin(), item.getTeacherLogin().getLogin(), null);
            items.add(newItem);
        }
        return items;
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public String create(servicesDTO.Results res) {
        res = resultsSB.saveUploadedResult(res);

        return String.valueOf(res.getId());
    }

}
