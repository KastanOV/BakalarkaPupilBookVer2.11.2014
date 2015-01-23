/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Studygroup;
import Entity.Studysubject;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
@Stateless
public class StudySubjectsSB implements StudySubjectsSBLocal {
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
    @Override
    public Studysubject getStudysubject(int id) {
        return em.find(Studysubject.class, id);
    }
    @Override
    public List<Studysubject> getStudySubjects(){
        return  em.createNativeQuery("SELECT * FROM studysubject", Studysubject.class).getResultList();
    }
    @Override
    public List<Studysubject> getStudySubjects(Studygroup group, String login){
        return em.createNativeQuery("select distinct studysubject.idStudySubject, studysubject.Name, studysubject.ShortName from sheduleitem "
	+ " join studysubject on studysubject.idStudySubject = sheduleitem.StudySubject_idStudySubject "
        + " join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup "
        + " where studygroup.idStudyGroup =  ?group and sheduleitem.Users_Login = ?login", Studysubject.class)
                .setParameter("group", group.getIdStudyGroup())
                .setParameter("login", login)
                .getResultList();
        
    }
    
}
