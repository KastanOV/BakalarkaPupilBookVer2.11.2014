/*
 * Copyright (C) 2015 Topr
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package service;

import Entity.Parent;
import Entity.Student;
import Entity.Teacher;
import Entity.Users;
import SessionBeans.loginSessionBeanLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Topr
 */
@Path("Login")
public class LoginResource {
    @EJB
    private loginSessionBeanLocal loginSB;
    
    @Context
    private UriInfo context;
    
    @GET
    @Path("{login}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public servicesDTO.Student checkLogin(@PathParam("login") String login, @PathParam("password") String password) {
        Users tmpstudent = loginSB.doLogin(login, password);
        if(tmpstudent != null){
            Integer sgID = null;
            if (tmpstudent.getStudyGroupidStudyGroup() != null){
                sgID = tmpstudent.getStudyGroupidStudyGroup().getIdStudyGroup();
            }
            servicesDTO.Student tmp = new servicesDTO.Student();
            tmp.setFirstName(tmpstudent.getFirstName());
            tmp.setMiddleName(tmpstudent.getMiddleName());
            tmp.setLastName(tmpstudent.getLastName());
            tmp.setPhone(tmpstudent.getPhone());
            tmp.setEmail(tmpstudent.getEmail());
            tmp.setLogin(tmpstudent.getLogin());
            tmp.setPassword(tmpstudent.getPassword());
            tmp.setStudyGroupID(sgID);
            if(tmpstudent instanceof Student) tmp.setRole("S");
            else if (tmpstudent instanceof Parent) tmp.setRole("P");
            else if (tmpstudent instanceof Teacher) tmp.setRole("T");
            return tmp;
        }
        return null;
    }
    
}
