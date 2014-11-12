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
class SchooyearDAOjpa {
    private final EntityManager em;
    
    public SchooyearDAOjpa(EntityManager em){
        super();
        this.em = em;
    }
    
    public Schoolyear saveSchoolyear(Schoolyear s) {
        if(s.getIdSchoolYear() != null){
            em.merge(s);
        } else {
            em.persist(s);
        }
        em.flush();
        return s;
    }

    public List<Schoolyear> getAllSchoolYears() {
        return em.createNamedQuery("Schoolyear.findAll").getResultList();
    }

    public Schoolyear getSchoolyear(int id) {
        return em.find(Schoolyear.class, id);
    }

    public void deleteSchooYear(Schoolyear s) {
        em.remove(em.find(Schoolyear.class, s.getIdSchoolYear()));
    }

    public void deleteSchooYear(int id) {
        em.remove(em.find(Schoolyear.class, id));
    }
}
