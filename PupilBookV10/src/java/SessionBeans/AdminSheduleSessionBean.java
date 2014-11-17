/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Studysubject;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class AdminSheduleSessionBean implements AdminSheduleSessionBeanLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Collection<Studysubject> getAllStudySubjects(){
        return em.createNamedQuery("Studysubject.findAll").getResultList();
    }
    @Override
    public Studysubject insertNewStudySubject(Studysubject s){
        em.persist(s);
        em.flush();
        return s;
    }
    @Override
    public Studysubject saveStudySubject(Studysubject s){
        em.merge(s);
        em.flush();
        return s;
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
