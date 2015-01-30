/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Student;
import Entity.Teacher;
import SessionBeans.StudentsSBLocal;
import SessionBeans.TeachersSBLocal;
import java.util.ArrayList;
import java.util.List;
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
    @EJB
    private StudentsSBLocal studentsSB;
    
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
    
    /**
     *
     * @param studentlist
     * @param login
     * @param password
     * @return
     */
    @GET
    @Path("{studentlist}/{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<servicesDTO.Student> checkLogin(@PathParam("studentlist") String studentlist, @PathParam("login") String login, @PathParam("password") String password) {
        List<Student> tmpList = studentsSB.getStudents(login, password);
        List<servicesDTO.Student> items = new ArrayList<>();
        for(Student item : tmpList){
            servicesDTO.Student tmp = new servicesDTO.Student(item.getFirstName(), item.getMiddleName(), item.getLastName(), item.getPhone(), item.getEmail(), item.getLogin(), item.getPassword(), item.getStudyGroupidStudyGroup().getIdStudyGroup());
            items.add(tmp);
        }
        return  items;
    }
}
