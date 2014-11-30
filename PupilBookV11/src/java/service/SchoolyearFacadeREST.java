/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Schoolyear;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Topr
 */
@Stateless
@Path("entity.schoolyear")
public class SchoolyearFacadeREST extends AbstractFacade<Schoolyear> {
    @PersistenceContext(unitName = "PupilBookV11PU")
    private EntityManager em;

    public SchoolyearFacadeREST() {
        super(Schoolyear.class);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Schoolyear find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    @GET
    
    @Produces({"application/xml", "application/json"})
    public Schoolyear findActual() {
        return (Schoolyear) em.createNamedQuery("Schoolyear.findByIsactualyear")
                .setParameter("isactualyear", true)
                .getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
