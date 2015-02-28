/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Topr
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(filter.RESTCorsRequestFilter.class);
        resources.add(filter.RESTCorsResponseFilter.class);
        resources.add(service.AttendanceResource.class);
        resources.add(service.InformationsResource.class);
        resources.add(service.LoginResource.class);
        resources.add(service.ResultsResource.class);
        resources.add(service.SchoolyearFacadeREST.class);
        resources.add(service.SheduleitemsResource.class);
        resources.add(service.StudentsResource.class);
        resources.add(service.StudySubjectsResource.class);
        resources.add(service.StudygroupResource.class);
        resources.add(service.TeacherReasource.class);
    }
    
}
