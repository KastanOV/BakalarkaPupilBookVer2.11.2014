/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Results;
import Entity.Schoolyear;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
import java.sql.Date;
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
}
