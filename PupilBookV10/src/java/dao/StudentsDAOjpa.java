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
public class StudentsDAOjpa {
    private final EntityManager em;
    
    public StudentsDAOjpa(EntityManager em){
        super();
        this.em = em;
    }
    
    public Students saveStudent(Students s){
        if(s.getLogin() != null){
            em.merge(s);
        } else {
            //TODO generovani loginu
            em.persist(s);
        }
        em.flush();
        return s;
    }
}
