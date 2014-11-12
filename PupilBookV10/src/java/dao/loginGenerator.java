/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Students;
import javax.persistence.EntityManager;

/**
 *
 * @author KastanNotas
 */
public class loginGenerator {

    private DAOFactory factory;
    public loginGenerator(Students s, DAOFactory factory) {
        this.factory = factory;
        
    }
    
    
}
