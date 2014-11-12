/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class StudyGroupSB implements StudyGroupSBLocal {

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
        em.refresh(em.find(Schoolyear.class, s.getSchoolYearidSchoolYear().getIdSchoolYear()));
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
        
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Collection<Studygroup> getStudyGroupFromYear(int id) {
        em.clear();
        Schoolyear s = em.find(Schoolyear.class, id);
        em.refresh(s);
        Collection<Studygroup> studygroupCollection = s.getStudygroupCollection();
        return studygroupCollection;
    }
}
