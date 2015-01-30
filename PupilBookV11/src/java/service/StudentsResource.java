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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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

    
    @GET
    @Path("{login}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public servicesDTO.Student checkLogin(@PathParam("login") String login, @PathParam("password") String password) {
        Student tmpstudent = studentsSB.studentLogin(login, password);
        //List<servicesDTO.Student> tmpList = new ArrayList<>();
        if(tmpstudent != null){
            servicesDTO.Student tmp = new servicesDTO.Student(tmpstudent.getFirstName(), tmpstudent.getMiddleName(), tmpstudent.getLastName(), tmpstudent.getPhone(), tmpstudent.getEmail(), tmpstudent.getLogin(), tmpstudent.getPassword(), tmpstudent.getStudyGroupidStudyGroup().getIdStudyGroup());
            //tmpList.add(tmp);
            
            return tmp;
        }
        return null;
    }
    
}
