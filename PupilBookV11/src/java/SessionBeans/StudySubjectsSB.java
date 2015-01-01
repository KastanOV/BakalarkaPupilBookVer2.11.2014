/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Studygroup;
import Entity.Studysubject;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
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
    
    private DAOFactory factory;
    
    private DAOFactory getFactory(){
        if(factory == null){
            factory = new DAOFactoryJPA(em);
        }
        return factory;
    }
    
    @Override
    public Collection<Studysubject> getAllStudySubjects(){
        return getFactory().getStudySubjectsDAO().getAllStudySubjects();
    }
    @Override
    public Studysubject insertNewStudySubject(Studysubject s){
        
        return getFactory().getStudySubjectsDAO().insertNewStudySubject(s);
    }
    @Override
    public Studysubject saveStudySubject(Studysubject s){
        
        return getFactory().getStudySubjectsDAO().saveStudySubject(s);
    }
    @Override
    public Studysubject getStudysubject(int id) {
        return getFactory().getStudySubjectsDAO().getStudysubject(id);
    }
    @Override
    public List<Studysubject> getStudySubjects(){
        return  getFactory().getStudySubjectsDAO().getStudySubjects();
    }
    @Override
    public List<Studysubject> getStudySubjects(Studygroup group, String login){
        return getFactory().getStudySubjectsDAO().getStudySubjects(group, login);
        
    }
    
}
