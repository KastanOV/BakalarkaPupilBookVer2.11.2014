/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Results;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Teacher;
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
public class ResultsSB implements ResultsSBLocal {
    
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
    public servicesDTO.Results saveUploadedResult(servicesDTO.Results res) {
        return getFactory().getResultsDAO().saveUploadedResult(res);
    }
    
    @Override
    public List<Results> getStudentResults(String login, int StudySubjectID){
        return getFactory().getResultsDAO().getStudentResults(login, StudySubjectID);
    }
    
    @Override
    public void insertNewResult(Results res){
        getFactory().getResultsDAO().insertNewResult(res);
    }

    @Override
    public List<String> getAutoCompleteStrings(Studysubject sg, Teacher te) {
        int YearID = getFactory().getSchoolYearDAO().getActualSchoolYear().getIdSchoolYear();
        List<String> tmp = (List<String>) em.createNativeQuery("select distinct Description from results where StudySubject_idStudySubject = ?studySubject AND Teacher_Login = ?Teacher_login AND SchoolYear_idSchoolYear = ?SchoolYear")
                .setParameter("studySubject", sg.getIdStudySubject())
                .setParameter("Teacher_login", te.getLogin())
                .setParameter("SchoolYear", YearID)
                .getResultList();
        return tmp;
    }
}
