/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Schoolyear;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author KastanNotas
 */
public class SchooyearDAOjpa {
    private final EntityManager em;
    
    public SchooyearDAOjpa(EntityManager em){
        super();
        this.em = em;
    }
}
