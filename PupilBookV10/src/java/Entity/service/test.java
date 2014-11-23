/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;



import SessionBeans.WebServicesDaoLocal;
import WebServicesEntities.ServiceTransactionObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author KastanNotas
 */
@Stateless
@Path("/test")
public class test {
    @EJB
    WebServicesDaoLocal sb;

    
    @GET
    public String message() {
        return "Hi REST!";
    }
    @GET
    @Produces({"application/xml", "application/json"})
    public ServiceTransactionObject getData(){
        return sb.getAlldata("CHL000");
    }

    
}
