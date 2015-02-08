/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Sheduleitem;
import SessionBeans.SheduleItemsSBLocal;
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
import servicesDTO.SheduleItem;


/**
 * REST Web Service
 *
 * @author Topr
 */
@Path("sheduleitems")
public class SheduleitemsResource {

    @EJB
    private SheduleItemsSBLocal sheduleItemsSB;
    
    @Context
    private UriInfo context;

    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<servicesDTO.SheduleItem> getSheduleItems(@PathParam("login") String login, @PathParam("password") String password){
        List<Sheduleitem> tmpList = sheduleItemsSB.getSheduleItems(login, password);
        List<servicesDTO.SheduleItem> items = new ArrayList<>();
        for(Sheduleitem item : tmpList){
            SheduleItem tmp = new servicesDTO.SheduleItem(item.getIdSheduleItem(), item.getDay(), item.getHour(), item.getStudyGroupidStudyGroup().getIdStudyGroup(), item.getStudySubjectidStudySubject().getIdStudySubject(), item.getUsersLogin().getLogin());
            items.add(tmp);
        }
        return  items;
    }
    
    @GET
    @Path("{studygroup}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<servicesDTO.SheduleItemForStudents> getSheduleItems(@PathParam("studygroup") Integer studygroupid){
        List<Sheduleitem> tmpList = sheduleItemsSB.getSheduleItems(studygroupid);
        List<servicesDTO.SheduleItemForStudents> items = new ArrayList<>();
        for(Sheduleitem item : tmpList){
            Integer sstmp = null;
            String ssNameTmp = "volná hodina";
            String lgtmp = null;
            String teacherNameTmp = "";
            if(item.getStudySubjectidStudySubject() != null){
                sstmp = item.getStudySubjectidStudySubject().getIdStudySubject();
                ssNameTmp = item.getStudySubjectidStudySubject().getName();
            }
            if(item.getUsersLogin() != null){
                lgtmp = item.getUsersLogin().getLogin();
                teacherNameTmp = item.getUsersLogin().getLastName();
            }
            servicesDTO.SheduleItemForStudents tmp = new servicesDTO.SheduleItemForStudents(ssNameTmp,teacherNameTmp,item.getIdSheduleItem(), item.getDay(), item.getHour(), item.getStudyGroupidStudyGroup().getIdStudyGroup(), sstmp, lgtmp);
            items.add(tmp);
        }
        return  items;
    }
    
    
}
