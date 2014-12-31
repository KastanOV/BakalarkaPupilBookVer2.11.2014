/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
@Stateless
public class StudyGroupsSB implements StudyGroupsSBLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Studygroup saveStudygroup(Studygroup s) {
        if(s.getIdStudyGroup() != null){
            em.merge(s);
        } else {
            em.persist(s);
        }
        em.flush();
        return s;
    }
    @Override
    public List<Studygroup> getAllStudygroup() {
        return em.createNamedQuery("Studygroup.findAll").getResultList();
    }
    @Override
    public Studygroup getStudygroup(int StudygroupId) {
        return em.find(Studygroup.class, StudygroupId);
    }
    @Override
    public void deleteStudygroup(Studygroup p) {
        em.remove(em.find(Studygroup.class, p.getIdStudyGroup()));
    }
    @Override
    public void deleteStudygroup(int StudygroupId) {
        em.remove(em.find(Studygroup.class, StudygroupId));
    }

    @Override
    public List<Studygroup> getEditedStudyGroup(Schoolyear s){
        return em.createNamedQuery("Studygroup.findBySchoolyear")
                .setParameter("SchoolYearID", s)
                .getResultList();
    }
}
