/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Schoolyear;
import Entity.Studygroup;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author KastanNotas
 */
public class StudygroupDAOjpa {
    
    EntityManager em;
    
    public StudygroupDAOjpa(EntityManager em){
        super();
        this.em = em;
    }
    public Studygroup saveStudygroup(Studygroup s) {
        if(s.getIdStudyGroup() != null){
            em.merge(s);
        } else {
            em.persist(s);
        }
        em.flush();
        return s;
    }
    
    public List<Studygroup> getAllStudygroup() {
        return em.createNamedQuery("Studygroup.findAll").getResultList();
    }

     public Studygroup getStudygroup(int StudygroupId) {
        return em.find(Studygroup.class, StudygroupId);
    }
}
