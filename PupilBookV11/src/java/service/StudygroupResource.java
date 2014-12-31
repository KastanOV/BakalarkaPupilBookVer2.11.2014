/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entity.Studygroup;
import SessionBeans.StudyGroupsSBLocal;
import java.util.ArrayList;
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
    private StudyGroupsSBLocal studyGroupsSB;
    
    @Context
    private UriInfo context;

    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<servicesDTO.StudyGroup> getSheduleItems(@PathParam("login") String login, @PathParam("password") String password){
        List<Studygroup> tmpList = studyGroupsSB.getStudyGroups(login, password);
        List<servicesDTO.StudyGroup> items = new ArrayList<>();
        for(Studygroup item : tmpList){
            servicesDTO.StudyGroup tmp = new servicesDTO.StudyGroup(item.getIdStudyGroup(), item.getName());
            items.add(tmp);
        }
        return items;
    }
}
