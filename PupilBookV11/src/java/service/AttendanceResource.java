/*
 * Copyright (C) 2015 Jaroslav
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

import SessionBeans.AttendanceSBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import servicesDTO.AttendanceDTO;

/**
 *
 * @author Jaroslav
 */
@Path("attendance")
public class AttendanceResource {
    
    @EJB
    private AttendanceSBLocal SB;
    
    @Context
    private UriInfo context;
    
    @GET
    @Path("{login}/{password}")
    @Consumes({"application/xml", "application/json"})
    public List<AttendanceDTO> getAttendanceTeacher(@PathParam("login") String login, @PathParam("password") String password){
        return SB.getAttendanceService(login);
    }
    
    @GET
    @Path("{login}/{password}/{Student}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<AttendanceDTO> getAttendanceStudent(@PathParam("login") String login, @PathParam("password") String password, @PathParam("Student") String Student){
        return SB.getAttendanceServiceStudent(login);
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public String create(servicesDTO.AttendanceDTO att) {
        return String.valueOf(SB.UploadAttendance(att));
    }
}

