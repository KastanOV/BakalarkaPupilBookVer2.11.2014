/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Studysubject;
import SessionBeans.TeachersSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


/**
 * REST Web Service
 *
 * @author Topr
 */
@Path("studysubjects")
public class StudySubjectsResource {

    @EJB
    TeachersSessionBeanLocal sb;
    
    @Context
    private UriInfo context;

    @GET
    @Consumes({"application/xml", "application/json"})
    public List<servicesDTO.StudySubject> getSheduleItems(){
        List<Studysubject> tmpList = sb.getStudySubjects();
        List<servicesDTO.StudySubject> items = new ArrayList<>();
        for(Studysubject item : tmpList){
            servicesDTO.StudySubject tmp = new servicesDTO.StudySubject(item.getIdStudySubject(), item.getName(), item.getShortName());
            items.add(tmp);
        }
        
        return  items;
        
    }
}
