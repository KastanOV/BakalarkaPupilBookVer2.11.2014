/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
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
    
    private DAOFactory factory;
    
    private DAOFactory getFactory(){
        if(factory == null){
            factory = new DAOFactoryJPA(em);
        }
        return factory;
    }
    
    @Override
    public Studygroup saveStudygroup(Studygroup s) {
        return getFactory().getStudyGroupDAO().saveStudygroup(s);
    }
    @Override
    public List<Studygroup> getAllStudygroup() {
        return getFactory().getStudyGroupDAO().getAllStudygroup();
    }
    @Override
    public Studygroup getStudygroup(int StudygroupId) {
        return getFactory().getStudyGroupDAO().getStudygroup(StudygroupId);
    }
    @Override
    public void deleteStudygroup(Studygroup p) {
        getFactory().getStudyGroupDAO().deleteStudygroup(p);
    }
    @Override
    public void deleteStudygroup(int StudygroupId) {
        getFactory().getStudyGroupDAO().deleteStudygroup(StudygroupId);
    }

    @Override
    public List<Studygroup> getEditedStudyGroup(Schoolyear s){
        return getFactory().getStudyGroupDAO().getEditedStudyGroup(s);
    }
    
    @Override
    public List<Studygroup> getStudyGroups(String login, String password){
        return getFactory().getStudyGroupDAO().getStudyGroups(login, password);
    }
}
