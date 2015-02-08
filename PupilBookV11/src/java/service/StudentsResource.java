/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Student;
import SessionBeans.StudentsSBLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import servicesDTO.ResultsStudentsMobile;



/**
 * REST Web Service
 *
 * @author Topr
 */
@Path("Students")
public class StudentsResource {
    @EJB
    private StudentsSBLocal studentsSB;
    
    @Context
    private UriInfo context;
    
    @OPTIONS
    @Path("{login}/{password}/{options}")
    public Response getOptions() {
    return Response.ok()
      .header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
  }
    
    @GET
    @Path("{login}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public servicesDTO.Student checkLogin(@PathParam("login") String login, @PathParam("password") String password) {
        Student tmpstudent = studentsSB.studentLogin(login, password);
        if(tmpstudent != null){
            servicesDTO.Student tmp = new servicesDTO.Student(tmpstudent.getFirstName(), tmpstudent.getMiddleName(), tmpstudent.getLastName(), tmpstudent.getPhone(), tmpstudent.getEmail(), tmpstudent.getLogin(), tmpstudent.getPassword(), tmpstudent.getStudyGroupidStudyGroup().getIdStudyGroup());
            return tmp;
        }
        return null;
    }
    
    @GET
    @Path("{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<servicesDTO.StudySubject> getResultsStudentmobile(@PathParam("login") String login){
        return studentsSB.getStudySubjects(login);
    }

    @GET
    @Path("{login}/{password}/{options}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<servicesDTO.ResultsStudentsMobile> getResultsByStudent(@PathParam("login") String login, @PathParam("password") String password, @PathParam("options") String options){
        List<servicesDTO.ResultsStudentsMobile> retlist = new ArrayList<>();
        for(servicesDTO.StudySubject item : studentsSB.getStudySubjects(login)){
            servicesDTO.ResultsStudentsMobile newItem = new ResultsStudentsMobile();
            newItem.setName(item.getName());
            newItem.setResults(studentsSB.getResultsByStudentStudySubject(login, password,item.getId()));
            retlist.add(newItem);
        }
        return retlist;
    }
}
