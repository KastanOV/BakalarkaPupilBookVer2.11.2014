/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author KastanNotas
 */
@Path("/test")
public class test {

    
    @GET
    public String message() {
        return "Hi REST!";
    }
}
