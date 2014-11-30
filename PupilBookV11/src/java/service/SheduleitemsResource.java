/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Sheduleitem;
import SessionBeans.TeachersSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import servicesDTO.SheduleItem;


/**
 * REST Web Service
 *
 * @author Topr
 */
@Path("sheduleitems")
public class SheduleitemsResource {

    @EJB
    TeachersSessionBeanLocal sb;
    
    @Context
    private UriInfo context;

    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<servicesDTO.SheduleItem> getSheduleItems(@PathParam("login") String login, @PathParam("password") String password){
        List<Sheduleitem> tmpList = sb.getSheduleItems(login, password);
        List<servicesDTO.SheduleItem> items = new ArrayList<>();
        for(Sheduleitem item : tmpList){
            SheduleItem tmp = new servicesDTO.SheduleItem(item.getIdSheduleItem(), item.getDay(), item.getHour(), item.getStudyGroupidStudyGroup().getIdStudyGroup(), item.getStudySubjectidStudySubject().getIdStudySubject(), item.getUsersLogin().getLogin());
            items.add(tmp);
        }
        return  items;
    }
}
