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
    
    @Override
    public List<Studygroup> getStudyGroups(String login, String password){
        //DISTINCT studygroup.idStudyGroup, studygroup.Name
        if(checkTeacher(login, password)){
            return em.createNativeQuery("SELECT DISTINCT studygroup.idStudyGroup, studygroup.Name FROM SheduleItem"
                    + " left join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup"
                    + " join schoolyear on schoolyear.idSchoolYear = studygroup.SchoolYear_idSchoolYear"
                    + " WHERE Users_Login = ?login AND schoolyear.isactualyear = true", Studygroup.class)
                    .setParameter("login", login)
                    .getResultList();
        }else {
            return null;
        }
    }
    
    private boolean checkTeacher(String login, String password){
        long tmp = (long)em.createNativeQuery("SELECT count(*) FROM Users u WHERE u.login = ?login AND u.password = ?password AND Role = 'T'")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
        if(tmp > 0){
            return true;
        }else return false;
    }
}
